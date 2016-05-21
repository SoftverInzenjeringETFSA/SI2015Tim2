package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;


import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.HibernateUtil;
import tim12.si.app.godisnji_odmori.Model.*;


public class SektorController {
	
	private int brojsektora;
	public Session session = HibernateUtil.getSessionFactory().openSession();
	public Transaction t;
	private ZaposlenikController zc;
	
	public SektorController() {
		
		
	}
	
	
	
	public void dodajSektor(SektorVM sektorVM) {
		
		 Sektor sektor = pretvoriUSektor(sektorVM);
		 upisiUBazuSektorBaza(sektor);
	}


	public void modificirajSektor(SektorVM sektorVM, int id) {
		
	          
			Sektor sektor = dajSektorPoIdBaza(id);
	        sektor.setNaziv(sektorVM.naziv);
	        sektor.setGodina_osnivanja(sektorVM.godina_osnivanja);
	        sektor.setMax_broj_odsutnih(sektorVM.maxBrojOdsutnih);
	        sektor.setOpis(sektorVM.opis);
	       	modificirajSektorBaza(sektor);
        

	}

	
	public void dodajZaposlenikeUSektor(long sektorID, List<ZaposlenikVM> listaZaposlenika) {
		// TODO - implement SektorController.dodajZaposlenikeUSektor
		throw new UnsupportedOperationException();
	}

	
	public String dajNazivSektora(int sektorID) {
		// TODO - implement SektorController.dajNazivSektora
		throw new UnsupportedOperationException();
	}

	public int dajBrojSektora() {
		
		return dajSveSektoreBaza().size();
		
	}
	
	
	public String[] dajSveSektore() {
		
		List<Sektor> list = dajSveSektoreBaza();
			   
	    int brojac = 0;
	    final String [] listaSektora =  new String[list.size()];
	    
		for(Iterator<Sektor> i = list.iterator(); i.hasNext(); ) {
	        Sektor item = i.next();
	        listaSektora[brojac]=(item.getNaziv());	 
	        brojac++;
	        }
		
	   return listaSektora;
	
	}
	
	public Sektor dajSektorPoNazivu (String naziv){
		
			return dajSektorPoNazivuBaza(naziv);
	}
	
	public void obrisiSektor (String naziv){
		
		zc = new ZaposlenikController(session);
		
		Sektor sektor = dajSektorPoNazivuBaza(naziv);
		obrisiSektorBaza(sektor);
		zc.ispisiZaposlenikeIzSektora(sektor.getSektor_id());
	}
	
	public Sektor pretvoriUSektor(SektorVM sektorVM) {
	
		Sektor sektor= new Sektor();
		sektor.setNaziv(sektorVM.naziv);
		sektor.setGodina_osnivanja(sektorVM.godina_osnivanja);
		sektor.setBroj_uposlenih(sektorVM.brojUposlenih);
		sektor.setMax_broj_odsutnih(sektorVM.maxBrojOdsutnih);
		sektor.setOpis(sektorVM.opis);
		
		return sektor;
	}
	
	
	// =======================================================================
	// 									DAL
	// =======================================================================
	
	
	
	public void upisiUBazuSektorBaza (Sektor sektor) {
		Transaction t = session.beginTransaction(); 
		session.save(sektor);
   	    t.commit();
	}
	
	public void modificirajSektorBaza (Sektor sektor){
		
		Transaction t = session.beginTransaction();
	  	session.saveOrUpdate(sektor);
	  	t.commit();
	}
	
	public Sektor dajSektorPoNazivuBaza (String naziv){
		
		Criteria criteria = session.createCriteria(Sektor.class);
		criteria.add(Restrictions.eq("naziv", naziv));
		return  (Sektor) criteria.uniqueResult(); 
	
	}
	
	public Sektor dajSektorPoIdBaza (int id){
		
		Criteria criteria = session.createCriteria(Sektor.class);
		criteria.add(Restrictions.eq("sektor_id", (long)id));
		return  (Sektor) criteria.uniqueResult();
		
	}
	
	public String dajNazivSektoraPoIdBaza (long idSektora){
		
		
		Criteria criteria = session.createCriteria(Sektor.class);
		criteria.add(Restrictions.eq("sektor_id", (long)idSektora));
		Sektor s=  (Sektor) criteria.uniqueResult();
		return s.getNaziv();
		
	}
	
	
	public List<Sektor> dajSveSektoreBaza (){
		
		return session.createCriteria(Sektor.class).list();
		
	}
	
	public void obrisiSektorBaza (Sektor sektor){
		
		Transaction t = session.beginTransaction(); 
		session.delete(sektor);
   	    t.commit();
		
	}
	
	
	
	
	
	


}
