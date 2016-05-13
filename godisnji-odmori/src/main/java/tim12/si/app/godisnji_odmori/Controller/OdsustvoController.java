package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;

public class OdsustvoController {
	private Session session;
	private SektorController sc;
	public OdsustvoController(Session session)
	{
		this.session = session;
		sc = new SektorController();
	}
	/**
	 * 
	 * @param odsustvo
	 */
	public void dodajOdsustvo(OdsustvoVM odsustvo) {
		// TODO - implement OdsustvoController.dodajOdsustvo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipOdsustvaID
	 */
	public List<OdsustvoVM> dajOdsustvaPoTipu(int tipOdsustvaID) {
		// TODO - implement OdsustvoController.dajOdsustvaPoTipu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposlenikID
	 */
	public List<OdsustvoVM> dajOdsutvaPoZaposleniku(int zaposlenikID) {
		// TODO - implement OdsustvoController.dajOdsutvaPoZaposleniku
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datumPocetka
	 * @param datumZavrsetka
	 */
	public List<OdsustvoVM> dajOdsustvaPoPeriodu(Date datumPocetka, Date datumZavrsetka) {
		// TODO - implement OdsustvoController.dajOdsustvaPoPeriodu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sektorID
	 */
	public List<OdsustvoVM> dajOdsustvaPoSektoru(int sektorID) {
		// TODO - implement OdsustvoController.dajOdsustvaPoSektoru
		throw new UnsupportedOperationException();
	}

	public ArrayList<Date> dajSvaOdsustva(String sektor) {
		
		System.out.println("Dosooo");
		System.out.println(sektor);

		int maxBrojDana = sc.dajSektorPoNazivuBaza(sektor).getMax_broj_odsutnih();
		ArrayList<Date> ld = new ArrayList<Date>();
		ArrayList<Date> listaDatuma = new ArrayList<Date>();
		Transaction t = session.beginTransaction();
		
		String hql = "Select o.datum "
				+ "FROM Odsustvo o, Zaposlenik z, Sektor s "
				+ "WHERE o.zaposlenik_id = z.zaposlenik_id AND z.sektor_id = s.sektor_id AND s.naziv = :sektor ";
				
		Query q = session.createQuery(hql);
		q.setString("sektor", sektor);
		
		
		List l = q.list();
		t.commit();
		
		for (int i=0; i<l.size(); i++)
				ld.add((Date) l.get(i));
		
		Map<Date,Integer> datumi = new Hashtable<Date, Integer>();
		
		for(int i=0;i<ld.size();i++){
			
			if(!datumi.containsKey(ld.get(i))){
				
				datumi.put(ld.get(i), 1);
				
			}
			else {
				
				datumi.put(ld.get(i), datumi.get(ld.get(i)) + 1);
				
			}
			
			
		}
		
		
			
		for(Date kljuc: datumi.keySet()){
			
			
				if(datumi.get(kljuc)>=maxBrojDana)
					listaDatuma.add(kljuc);
				
		}

			
		
		

		
		
		return listaDatuma;
	}
	
	
	// =======================================================================
	// 									DAL
	// =======================================================================
	
	
	
	
	


}
