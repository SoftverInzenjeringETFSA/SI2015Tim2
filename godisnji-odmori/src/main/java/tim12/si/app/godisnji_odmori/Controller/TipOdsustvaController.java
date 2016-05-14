package tim12.si.app.godisnji_odmori.Controller;

import tim12.si.app.godisnji_odmori.ViewModel.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tim12.si.app.godisnji_odmori.Model.*;

public class TipOdsustvaController {
	
	Session sess = null;
	
	public TipOdsustvaController(Session sess) {
		// TODO Auto-generated constructor stub
		this.sess = sess;
	}

	/**
	 * 
	 * @param tipOdsustva
	 */
	public int dodajTipOdsustva(TipOdsustvaVM tipOdsustva) {
		TipOdsustvaVM t= new TipOdsustvaVM();
		t.naziv=tipOdsustva.naziv;
		t.opis=tipOdsustva.opis;
		return t.hashCode();
	}

	/**
	 * 
	 * @param tipOdsustvaID
	 */
	public void obrisiTipOdsustva(long tipOdsustvaID) {
		TipOdsustvaVM tovm=new TipOdsustvaVM();
		/*for (long a : baza) {
		 if(a == tipOdsustvaID)
			 tovm= new TipOdsustvaVM();
		}*/
	}

	/**
	 * 
	 * @param tipOdsustva
	 */
	public void modificirajTipOdsustva(long IDTipa, String noviNaziv, String noviOpis) {
		TipOdsustvaVM m= new TipOdsustvaVM();
		for(int i =0 ; i <3 ; i++)
		{
		
		}
	}
	
	public List<TipOdsustva> dajSveTipove(){
		// TODO - implement TipOdsustvaController.modificirajTipOdsustva
				throw new UnsupportedOperationException();
	}
	
	
	// =======================================================================
			// 									DAL
			// =======================================================================
			
		
		
	public String dajImeTipaOdsustva (Long id){
		
		Criteria criteria = sess.createCriteria(TipOdsustva.class);
		criteria.add(Restrictions.eq("id_odsustva", id));
		TipOdsustva z = (TipOdsustva) criteria.uniqueResult(); 
		
		return z.getNaziv();
	}




}
