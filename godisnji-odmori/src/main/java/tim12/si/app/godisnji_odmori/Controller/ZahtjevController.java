package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import ba.unsa.etf.si.tim12.dal.domainmodel.Korisnik;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.ZahtjevNotFound;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZahtjevController {
	
	private Session session;
	/**
	 * 
	 * @param zahtjev
	 */
	public ZahtjevController(Session session)
	{
		this.session = session;
	}
	public long kreirajZahtjev(ZahtjevVM zahtjev) throws Exception{
		Boolean flag = false;
		Zaposlenik zp = null;
		for(int i = 0; i<Zaposlenik.listaZaposlenika.size(); i++)
		{
			if (Zaposlenik.listaZaposlenika.get(i).getIme() == zahtjev.getPodnosilacIme() &&
					Zaposlenik.listaZaposlenika.get(i).getPrezime() == zahtjev.getPodnosilacPrezime())
				{ 
					flag = true;
					zp = Zaposlenik.listaZaposlenika.get(i);
					break;				
				}
		}
		if (!flag) throw new Exception("Zaposlenik ne postoji.");
		
		Zahtjev z = new Zahtjev();
		z.setPodnosilac_id(zp.getZaposlenik_id());
		z.setPocetak_odsustva(zahtjev.getPocetakOdsustva());
		z.setZavrsetak_odsustva(zahtjev.getZavrsetakOdsustva());
		z.setTip_odsustva(zahtjev.getTipOdsustva());
		z.setObradjen(false);
		z.setOdluka(null);
		z.setOpis(zahtjev.getOpis());
		z.setSektor_id(zp.getSektor_id());
		
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
	public void odobriZahtjev(long zahtjevID, Boolean odobren) throws Exception{
		Boolean flag = false;
		int temp = -1;
		for(int i = 0; i<Zahtjev.listaZahtjeva.size(); i++)
		{
			if (Zahtjev.listaZahtjeva.get(i).getZahtjev_id() == zahtjevID)
				{ 
					flag = true;
					temp = i;
					break;				
				}
		}
		if (!flag) throw new Exception("Zahtjev ne postoji.");
		
		Zahtjev.listaZahtjeva.get(temp).setObradjen(true);
		Zahtjev.listaZahtjeva.get(temp).setOdluka(true);
	}
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
			
			
			for (int i = 0; i<dates.size(); i++){
			Transaction t1 = session.beginTransaction(); 
			Odsustvo odsustvo = new Odsustvo();
			odsustvo.setZaposlenik_id(zbr.getZaposlenik_id());
			odsustvo.setTip(1);
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

	public ArrayList<Zahtjev> dajNeobradjeneZahtjeve() {
		List<Zahtjev> neobradjeni = new ArrayList<Zahtjev>();
		
		for(int i = 0; i<Zahtjev.listaZahtjeva.size(); i++)
		{
			if (Zahtjev.listaZahtjeva.get(i).getObradjen() == false)
				neobradjeni.add(Zahtjev.listaZahtjeva.get(i));
		}
		
		return (ArrayList<Zahtjev>) neobradjeni;
	}

	public ArrayList<Zahtjev> dajSveZahtjeve() {
		return (ArrayList<Zahtjev>) Zahtjev.listaZahtjeva;
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
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM(za.pocetak_odsustva, za.zavrsetak_odsustva, za.tip_odsustva, za.opis) "
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

}
