package tim12.si.app.godisnji_odmori.Controller;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
		
		
	public Boolean provjeriDaLiPostoji(Long id, Date danas)
	{
		List<Prisustvo> lista = sess.createCriteria(Prisustvo.class).add(Restrictions.eq("zaposlenik_id", id)).list();
		
		for(int i= 0; i<lista.size();i++)
		{
			if(lista.get(i).getDatum().getDate()== danas.getDate() && lista.get(i).getDatum().getMonth()==danas.getMonth() && lista.get(i).getDatum().getYear()== danas.getYear())
				return true;
		}
		
		return false;
		
		
	}
	public void upisiPrisustvoBaza(Prisustvo prisustvo){
		Transaction t = sess.beginTransaction(); 
		sess.save(prisustvo);
   	    t.commit();
	}
	


}
