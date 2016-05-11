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
	
	
	
	public void dodajSektor(Sektor sektor) {
		
		 upisiUBazuSektorBaza(sektor);
	}


	public void modificirajSektor(SektorVM sektorVM) {
		
	          
		Sektor sektorIzBaze = dajSektorPoNazivuBaza(sektorVM.naziv);
        if (sektorIzBaze!=null) 
        {
        	
        	
	       	Sektor sektor = pretvoriUSektor(sektorVM);
	       	sektor.setSektor_id(dajSektorPoNazivu(sektorVM.naziv).getSektor_id());
	       	modificirajSektorBaza(sektor);
           
        }
        
        else
        {
       	
        	 Sektor sektor = pretvoriUSektor(sektorVM);
        	 dodajSektor(sektor);
        	

         }

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
		
		Sektor sektor = dajSektorPoNazivuBaza(naziv);
		obrisiSektorBaza(sektor);
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
	  	session.update(sektor);
	  	t.commit();
	}
	
	public Sektor dajSektorPoNazivuBaza (String naziv){
		
		Criteria criteria = session.createCriteria(Sektor.class);
		criteria.add(Restrictions.eq("naziv", naziv));
		return  (Sektor) criteria.uniqueResult(); 
	
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
