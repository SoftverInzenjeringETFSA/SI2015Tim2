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
	public int BrojZaposlenih()
	{
		return Zaposlenik.listaZaposlenika.size();
	}
	//edituje podatke o zaposleniku
	public void ModificirajZaposlenika(ZaposlenikVM zaposlenikVM, int id)
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

	public long PronadjiIndexZaposlenika(long zaposlenik_id)
	{
		int brojac=0;
		int index=0;
		for(Zaposlenik zaposlenik: Zaposlenik.listaZaposlenika)
		{
			if(zaposlenik.getZaposlenik_id()==zaposlenik_id)
			{
				index=brojac;
				break;
			}
			brojac++;
		}
		return index;
	}
	
	public String[] dajSveZaposlenike(){
		List<Zaposlenik> list = dajSveZaposlenikeBaza();
		int i=0;
		final String[] listaZaposlenika = new String [list.size()];
		
		for (Iterator<Zaposlenik> j = list.iterator(); j.hasNext();){
			Zaposlenik item=j.next();
			listaZaposlenika[i]=(item.getIme());
			i++;
		}
		return listaZaposlenika;		
	}
	
	public void ObrisiZaposlenika(long zaposlenikID)
	{
		Zaposlenik zaposlenik = dajZaposlenikaPoId(zaposlenikID);
		obrisiZaposlenikaBaza(zaposlenik);
	}

	public void AutentifikujKorisnika(Zaposlenik zaposlenik)
	{
		
	}
	/**
	 * 
	 * @param zaposlenik
	 */
	public void OdjaviKorisnika(Zaposlenik zaposlenik)
	{

	}
	/**
	 * 
	 * @param sektorID
	 */
	public List<Zaposlenik> DajZaposlenikePoSektoru(int sektorID)
	{
		List<Zaposlenik> listaZ=new ArrayList<Zaposlenik>();
		for(Zaposlenik z:Zaposlenik.listaZaposlenika)
		{
			if(z.getSektor_id()==sektorID)
			{
				listaZ.add(z);
			}
		}
		return listaZ;
	}
	public ZaposlenikBrDana DajZaposlenikViewModel(String username) throws ZaposlenikNotFound
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana(s.naziv, z.ime, "+
				"z.prezime, count(p.prisustvo_id), z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to WHERE z1.username = :username  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to.id_odsustva AND to.id_odsustva = 2)) "
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
				"z.prezime, count(p.prisustvo_id), z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to WHERE z1.username = :username  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to.id_odsustva AND to.naziv = 'godišnji odmor'),"
				+ "(Select count(o2.odsustvo_id) FROM Zaposlenik z2, Odsustvo o2, TipOdsustva to1 WHERE z2.username = :username  AND z2.zaposlenik_id = o2.zaposlenik_id AND o2.tip = to1.id_odsustva AND to1.naziv = 'bolovanje'), "
				+ "(Select count(o3.odsustvo_id) FROM Zaposlenik z3, Odsustvo o3, TipOdsustva to3 WHERE z3.username = :username  AND z3.zaposlenik_id = o3.zaposlenik_id AND o3.tip = to3.id_odsustva AND to3.naziv = 'neplanirano'),"
				+ "(Select count(o4.odsustvo_id) FROM Zaposlenik z4, Odsustvo o4, TipOdsustva to4 WHERE z4.username = :username  AND z4.zaposlenik_id = o4.zaposlenik_id AND o4.tip = to4.id_odsustva AND to4.naziv = 'godišnji odmor')) "
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
	
	//Informacije o logovanom korisniku
	public ZaposlenikVM DajZaposlenikoveInformacije(String username) throws ZaposlenikNotFound
	{
		
		Transaction t = session.beginTransaction();
		//ime, prezime, datum rodjenja,broj telefona i adresa
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
			
		int idSektora;
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		idSektora = (int)z.getSektor_id();
		
		return sc.dajNazivSektoraPoIdBaza(idSektora);
	}
	
	public List<Zaposlenik> dajSveZaposlenikeBaza(){
		return session.createCriteria(Zaposlenik.class).list();
	}
	
}
