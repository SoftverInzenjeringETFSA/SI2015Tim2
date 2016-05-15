package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import ba.unsa.etf.si.tim12.dal.domainmodel.Korisnik;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Singleton;
import tim12.si.app.godisnji_odmori.ZahtjevNotFound;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.View.ZahtjevPregledManagement;

public class ZahtjevController {
	
	private Session session;
	private ZaposlenikController zc;
	private static final Logger logger = Logger.getLogger(ZahtjevController.class);
	/**
	 * 
	 * @param zahtjev
	 */
	public ZahtjevController(Session session)
	{
		this.session = session;
	}
	public long kreirajZahtjev(ZahtjevVM zahtjev) throws Exception{
		
		ZaposlenikController zc = new ZaposlenikController(session);
		
		Zaposlenik zp = zc.dajZaposlenikaBaza(Singleton.getInstance().getUsername());
		Zahtjev z = new Zahtjev();
		z.setPodnosilac_id(zp.getZaposlenik_id());
		z.setPocetak_odsustva(zahtjev.getPocetakOdsustva());
		z.setZavrsetak_odsustva(zahtjev.getZavrsetakOdsustva());
		z.setTip_odsustva(zahtjev.getTipOdsustva());
		z.setObradjen(false);
		z.setOdluka(false);
		z.setOpis(zahtjev.getOpis());
		z.setSektor_id(zp.getSektor_id());
		z.setNalaz(zahtjev.getNalaz());
		upisiZahtjevBaza(z);
		return z.getZahtjev_id();
		
	}

	/**
	 * 
	 * @param zahtjevID
	 */
	public void obradiZahtjev(long zahtjevID) {
		// ne kontam koja je razlika izmedju ove metode i odobriZahtjev ?
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zahtjevID
	 * @param odobren
	 */
	
	public int odobriZahtjev(long zahtjevID, ZaposlenikBrDana zbr, ZahtjevVM zvm)
	{
		Transaction t = session.beginTransaction();
		String hql = "FROM Zahtjev za " +
				"WHERE za.zahtjev_id = :zahtjevID";
		Query q = session.createQuery(hql);
		q.setParameter("zahtjevID", zahtjevID);
		Zahtjev z = (Zahtjev) q.uniqueResult();
		if(z == null){ //Nije pronadjen zahjtev s ovim id-om
			t.rollback();
			return 0;
		}
		else {
			
			z.setObradjen(true);
			z.setOdluka(true);
			session.update(z);
			t.commit();
			
			Transaction t2 = session.beginTransaction();
			Long tipO = zvm.getTipOdsustva();
			String hql2 = "Select to1.id_odsustva "
					+ "FROM TipOdsustva to1 "
					+ "WHERE to1.id_odsustva = :tipO ";
			Query q2 = session.createQuery(hql2);
			q2.setLong("tipO", tipO);
			List l = q2.list();
			t2.commit();
			Long tip = (Long) l.get(0);
			
			Date fromDate = zvm.getPocetakOdsustva();
			Date toDate = zvm.getZavrsetakOdsustva();
			List<Date> dates = new ArrayList<Date>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			dates.add(fromDate);
			while (cal.getTime().before(toDate)) {
			    cal.add(Calendar.DATE, 1);
			    dates.add(cal.getTime());
			}
			
			
			for (int i = 0; i<dates.size()-1; i++){
			Transaction t1 = session.beginTransaction(); 
			Odsustvo odsustvo = new Odsustvo();
			odsustvo.setZaposlenik_id(zbr.getZaposlenik_id());
			odsustvo.setOpis(zvm.getOpis());
			odsustvo.setTip(tip);
			odsustvo.setDatum(dates.get(i));
			session.save(odsustvo);
	   	    t1.commit();
			}
	   	    
	   	    
			return 1;
		}
	}
	public int odbijZahtjev(long zahtjevID, ZaposlenikBrDana zbr, ZahtjevVM zvm)
	{
		Transaction t = session.beginTransaction();
		String hql = "FROM Zahtjev za " +
				"WHERE za.zahtjev_id = :zahtjevID";
		Query q = session.createQuery(hql);
		q.setParameter("zahtjevID", zahtjevID);
		Zahtjev z = (Zahtjev) q.uniqueResult();
		if(z == null){ //Nije pronadjen zahjtev s ovim id-om
			t.rollback();
			return 0;
		}
		else {
			z.setObradjen(true);
			z.setOdluka(false);
			session.update(z);
			t.commit();
			
			return 1;
		}
	}

	
	
	public ArrayList<ZahtjevVM> dajSveZahtjeveIzSektora(String sektor)
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM(z.ime, z.prezime, s.naziv, za.zahtjev_id, z.username) "
				+ "FROM Zaposlenik z, Sektor s, Zahtjev za "
				+ "WHERE s.naziv = :sektor AND s.sektor_id = z.sektor_id AND z.zaposlenik_id = za.podnosilac_id AND (za.obradjen = null OR za.obradjen = 0) ";
		Query q = session.createQuery(hql);
		q.setString("sektor", sektor);
		
		List l = q.list();
		t.commit();
		/*if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");*/
		ArrayList<ZahtjevVM> zvm = new ArrayList<ZahtjevVM>();
		for (int i=0; i<l.size(); i++)
				zvm.add((ZahtjevVM) l.get(i));
		return zvm;
	}
	public ZahtjevVM dajZahtjev(Long id) throws ZahtjevNotFound
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM(za.pocetak_odsustva, za.zavrsetak_odsustva, za.tip_odsustva, za.opis, za.nalaz) "
				+ "FROM Zahtjev za "
				+ "WHERE za.zahtjev_id = :id ";
		Query q = session.createQuery(hql);
		q.setLong("id", id);
		
		List l = q.list();
		t.commit();
		if(l.isEmpty())
			throw new ZahtjevNotFound("Zahtjev s id-om: " + id + " nije pronadjen.");
			
		ZahtjevVM vm = (ZahtjevVM) l.get(0);
		return vm;
	}
	
	
	public Boolean provjeriMozelToliko (ZahtjevVM zvm){
		
			long izabraoBrojDana=0;
			long preostalo=0;
		try {
			
			zc= new ZaposlenikController(session);
			ZaposlenikBrDana zbr = zc.DajZaposlenikViewModelZaZahtjev(Singleton.getInstance().getUsername());
			preostalo = zbr.getPreostaloSlobodnih();
			izabraoBrojDana = daysBetween(zvm.getPocetakOdsustva().getTime(),zvm.getZavrsetakOdsustva().getTime());
			
			
			
			
		} catch (Exception e) {
			
			logger.error(e);
			
		}
		
		return (preostalo>izabraoBrojDana);
		
	}
	
	private int daysBetween(long t1, long t2) {
	    return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
	} 
	
	
	
	// =======================================================================
		// 									DAL
		// =======================================================================
		
	
	
	public void upisiZahtjevBaza(Zahtjev z){
		
		Transaction t = session.beginTransaction(); 
		session.save(z);
   	    t.commit();
	}

}
