package tim12.si.app.godisnji_odmori.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZaposlenikController 
{
	private Session session;
	private ZaposlenikVM zvm;
	private SektorController sc;
	/**
	 * 
	 * @param zaposlenik
	 */	
	
	public ZaposlenikController(){};

	public ZaposlenikController(Session session)
	{
		this.session = session;
		sc = new SektorController();
	};
	
	//dodaje zaposlenika u bazu
	public void DodajZaposlenika(ZaposlenikVM zaposlenikVM) 
	{
		Zaposlenik zaposlenik = pretvoriUZaposlenika(zaposlenikVM);
		upisiUBazu(zaposlenik);
	}
	
	//edituje podatke o zaposleniku
	public void ModificirajZaposlenika(ZaposlenikVM zaposlenikVM, long id)
	{
	    Zaposlenik zaposlenik = dajZaposlenikaPoId(id);
		zaposlenik.setIme(zaposlenikVM.ime);
		zaposlenik.setPrezime(zaposlenikVM.prezime);
		zaposlenik.setAdresa_stanovanja(zaposlenikVM.adresaStanovanja);
		zaposlenik.setBroj_dana_godisnjeg(zaposlenikVM.brojDanaGodisnje);
		zaposlenik.setDatum_rodjenja(zaposlenikVM.datumRodjenja);
		zaposlenik.setEmail(zaposlenikVM.email);
		zaposlenik.setSektor_id(zaposlenikVM.sektor);
		zaposlenik.setTelefon(zaposlenikVM.telefon);
		modificirajZaposlenikBaza(zaposlenik);
	}

	

	public ZaposlenikBrDana DajZaposlenikViewModel(String username) throws ZaposlenikNotFound
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana(s.naziv, z.ime, "+
				"z.prezime, count(p.prisustvo_id), z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to WHERE z1.username = :username  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to.id_odsustva AND to.id_odsustva = 1)) "
				+ "FROM Zaposlenik z, Sektor s, Prisustvo p "
				+ "WHERE z.username = :username AND s.sektor_id = z.sektor_id AND z.zaposlenik_id = p.zaposlenik_id";
		Query q = session.createQuery(hql);
		q.setString("username", username);
		
		List l = q.list();
		t.commit();
		
		if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");
			
		ZaposlenikBrDana vm = (ZaposlenikBrDana) l.get(0);
		
		return vm;
	}
	public ZaposlenikBrDana DajZaposlenikViewModelZaZahtjev(String username) throws ZaposlenikNotFound
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana(s.naziv, z.ime,z.zaposlenik_id, "+
				"z.prezime, count(p.prisustvo_id), z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to WHERE z1.username = :username  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to.id_odsustva AND to.id_odsustva = 1),"
				+ "(Select count(o2.odsustvo_id) FROM Zaposlenik z2, Odsustvo o2, TipOdsustva to1 WHERE z2.username = :username  AND z2.zaposlenik_id = o2.zaposlenik_id AND o2.tip = to1.id_odsustva AND to1.id_odsustva = 2), "
				+ "(Select count(o3.odsustvo_id) FROM Zaposlenik z3, Odsustvo o3, TipOdsustva to3 WHERE z3.username = :username  AND z3.zaposlenik_id = o3.zaposlenik_id AND o3.tip = to3.id_odsustva AND to3.id_odsustva = 3),"
				+ "(Select count(o4.odsustvo_id) FROM Zaposlenik z4, Odsustvo o4, TipOdsustva to4 WHERE z4.username = :username  AND z4.zaposlenik_id = o4.zaposlenik_id AND o4.tip = to4.id_odsustva AND to4.id_odsustva = 1)) "
				+ "FROM Zaposlenik z, Sektor s, Prisustvo p "
				+ "WHERE z.username = :username AND s.sektor_id = z.sektor_id AND z.zaposlenik_id = p.zaposlenik_id";
		
		Query q = session.createQuery(hql);
		q.setString("username", username);
		
		List l = q.list();
		t.commit();
		
		if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");
			
		ZaposlenikBrDana vm = (ZaposlenikBrDana) l.get(0);
		
		
		return vm;
	}
	
	public ZaposlenikAccountVM DajZaposlenikAccVM(String username, String password) throws ZaposlenikNotFound
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikAccountVM(z.username, z.password, z.privilegija) "+
				"FROM Zaposlenik z WHERE z.username = :username AND z.password = :password";
		Query q = session.createQuery(hql);
		q.setString("username", username);
		q.setString("password", password);
		
		List l = q.list();
		t.commit();
		if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");
		ZaposlenikAccountVM vm = (ZaposlenikAccountVM) l.get(0);
		return vm;
	}
	
	public ZaposlenikVM DajInformacijeZaTabelu (String username) throws ZaposlenikNotFound
	{
		Transaction t=session.beginTransaction();
		//id, ime, prezime, sektor, broj dana godišnjeg
		String neki="Select new tim12.si.app.godisnji_odmori.Model.Zaposlenik(z.id, z.ime,z.prezime,z.sektor_id, z.broj_dana_godisnjeg)"+
		"FROM Zaposlenik z WHERE z.username = :username";
		
		Query q=session.createQuery(neki);
		q.setString("username",username);
		
		List l=q.list();
		t.commit();
		
		if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");
			
		ZaposlenikVM vm = (ZaposlenikVM) l.get(0);
		return vm;
	}
	
	//Informacije o logovanom korisniku
	public ZaposlenikVM DajZaposlenikoveInformacije(String username) throws ZaposlenikNotFound
	{
		
		Transaction t = session.beginTransaction();
		//ime, prezime, datum rodjenja,broj telefona i adresa
		//,z.ime,z.prezime,z.email,z.datum_rodjenja,z.telefon,z.adresa_stanovanja
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM(z.ime,z.prezime,z.email,z.datum_rodjenja,z.telefon,z.adresa_stanovanja)"+
		"FROM Zaposlenik z WHERE z.username = :username";
		
		Query q = session.createQuery(hql);
		q.setString("username", username);
		
		List l = q.list();
		t.commit();
		
		
		
		
		if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");
			
		ZaposlenikVM vm = (ZaposlenikVM) l.get(0);
		
		
		return vm;
	}
	
	
	public Boolean promjeniSifru (String username, String password){
	
		Zaposlenik z = dajZaposlenikaBaza(username);
		z.setPassword(password);
		promjeniSifruBaza(z);
		
		return true;
	}
	

	
	public Zaposlenik pretvoriUZaposlenika (ZaposlenikVM zaposlenikVM){
		Zaposlenik zaposlenik = new Zaposlenik();
		zaposlenik.setIme(zaposlenikVM.ime);
		zaposlenik.setPrezime(zaposlenikVM.prezime);
		zaposlenik.setAdresa_stanovanja(zaposlenikVM.adresaStanovanja);
		zaposlenik.setBroj_dana_godisnjeg(zaposlenikVM.brojDanaGodisnje);
		zaposlenik.setDatum_rodjenja(zaposlenikVM.datumRodjenja);
		zaposlenik.setEmail(zaposlenikVM.email);
		zaposlenik.setSektor_id(zaposlenikVM.sektor);
		zaposlenik.setTelefon(zaposlenikVM.telefon);
		return zaposlenik;
	}
	
	// =======================================================================
	// 									DAL
	// =======================================================================
	
	public String dajPrezimeZaposlenikaBaza (String username){
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		
		return z.getPrezime();
	}
	
	public Zaposlenik dajZaposlenikaBaza (String username){
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		
		return z;
	}
	
	public void upisiUBazu(Zaposlenik zaposlenik){
		Transaction t = session.beginTransaction(); 
		session.save(zaposlenik);
   	    t.commit();
	}

	public Zaposlenik dajZaposlenikaPoId (long id){
		
		Criteria criteria = session.createCriteria(Sektor.class);
		criteria.add(Restrictions.eq("zaposlenik_id", (long)id));
		return  (Zaposlenik) criteria.uniqueResult();
		
	}
	
	public void modificirajZaposlenikBaza (Zaposlenik zaposlenik){
		
		Transaction t = session.beginTransaction();
	  	session.saveOrUpdate(zaposlenik);
	  	t.commit();
	}
	
	public void obrisiZaposlenikaBaza (Zaposlenik zaposlenik){
		
		Transaction t = session.beginTransaction(); 
		session.delete(zaposlenik);
   	    t.commit();
		
	}
	
	public String dajNazivSektoraZaposlenikaBaza(String username){
			
		long idSektora;
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		idSektora = (int)z.getSektor_id();
		
		return sc.dajNazivSektoraPoIdBaza(idSektora);
	}
	
	public List<Zaposlenik> dajSveZaposlenikeBaza(){
		return session.createCriteria(Zaposlenik.class).list();
	}
	
	public Boolean uporediSifruBaza(String username,String sifra){
		
	
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		
		return(z.getPassword().equals(sifra));
		
	}
	
	public void promjeniSifruBaza (Zaposlenik z) {
		
		
		Transaction t = session.beginTransaction();
	  	session.saveOrUpdate(z);
	  	t.commit();
		
		
	}
}
	

