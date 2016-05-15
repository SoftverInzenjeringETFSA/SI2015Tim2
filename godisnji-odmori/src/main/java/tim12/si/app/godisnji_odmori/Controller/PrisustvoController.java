package tim12.si.app.godisnji_odmori.Controller;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;

public class PrisustvoController {
	

	/**
	 * 
	 * @param zaposlenik
	 * @param datum
	 */
	Session sess = null;
	
	public PrisustvoController(Session sess) {
		this.sess= sess;
	}
	public void evidentirajPrisustvo(Long zaposlenik_id, Date datum) {
		Prisustvo prisustvo = new Prisustvo();
		prisustvo.setZaposlenik_id(zaposlenik_id);
		prisustvo.setDatum(datum);
		upisiPrisustvoBaza(prisustvo);
	}
	
	
	
	// =======================================================================
		// 									DAL
		// =======================================================================
		
		
	
	public void upisiPrisustvoBaza(Prisustvo prisustvo){
		Transaction t = sess.beginTransaction(); 
		sess.save(prisustvo);
   	    t.commit();
	}
	


}
