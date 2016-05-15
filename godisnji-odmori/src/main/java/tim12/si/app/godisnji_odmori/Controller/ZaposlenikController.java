package tim12.si.app.godisnji_odmori.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.print.DocFlavor.STRING;
import javax.swing.text.StyledEditorKit.BoldAction;

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
	private int brojZaposlenika=0;
	private PrisustvoController pC;
	/**
	 * 
	 * @param zaposlenik
	 */	
	
	public ZaposlenikController(){};

	public ZaposlenikController(Session session)
	{
		this.session = session;
		sc = new SektorController();
		pC = new PrisustvoController(session);
	};
	
	
	public Object[][] dajSveZaposlenike() {
		
		
		
		List<Zaposlenik> listaZaposlenika = dajSveZaposlenikeBaza();
		Object[][] listaZaTabele = new Object[listaZaposlenika.size()][5];
		
		for(int i =0; i< listaZaposlenika.size();i++){
			
			StringBuilder sb = new StringBuilder();
        	sb.append("");
        	sb.append(listaZaposlenika.get(i).getZaposlenik_id());
        	String strI1 = sb.toString();
        	
        	StringBuilder sb2 = new StringBuilder();
        	sb2.append("");
        	sb2.append(listaZaposlenika.get(i).getBroj_dana_godisnjeg());
        	String strI2 = sb2.toString();
        	
        	
			
				listaZaTabele[i][0]=strI1;
				listaZaTabele[i][1]=listaZaposlenika.get(i).getIme();
				listaZaTabele[i][2]=listaZaposlenika.get(i).getPrezime();
				if(listaZaposlenika.get(i).getSektor_id()==0){
					
					listaZaTabele[i][3]="Nema sektora";
				}
				else {
					listaZaTabele[i][3]=sc.dajNazivSektoraPoIdBaza(listaZaposlenika.get(i).getSektor_id());
				}
				
				listaZaTabele[i][4]=strI2;
				
			}
		
	
		
		return listaZaTabele;
		
	}
	
	public void obrisiZaposlenika (int id){
		
		Zaposlenik zaposlenik = dajZaposlenikaPoId(id);
		obrisiZaposlenikaBaza(zaposlenik);
		
	}
	
	//dodaje zaposlenika u bazu
	public List<String> DodajZaposlenika(ZaposlenikVM zaposlenikVM) 
	{	
		Random rng= new Random();
		String slova = "0123456789abcdefghijklmnopqrstuvwxyz";
		char []password=new char[8];
		for(int i =0;i<8;i++){
			
			password[i]=slova.charAt(rng.nextInt(slova.length()));
		}
			
		
		String username = generisiUsername(zaposlenikVM.ime, zaposlenikVM.prezime);
		
		Zaposlenik zaposlenik = pretvoriUZaposlenika(zaposlenikVM, username, new String(password));
		upisiUBazu(zaposlenik);
		Date danas = new Date();
		pC.evidentirajPrisustvo(dajIdPoUsernamuBaza(username),danas);
		BrojZaposlenika();
		
		List<String> lista= new ArrayList<String>();
		lista.add(username);
		lista.add(new String(password));
		
		return lista;
		
	}
	
	private String generisiUsername(String ime, String prezime)
	{
		String username;
		String original;
		username=ime.substring(0,1).toLowerCase()+prezime.toLowerCase();
		original = username;
		int brojac=1;
		Boolean dobar = false;
		while(!dobar){
			
			dobar = provjeriUsernameBaza(username);
			if(dobar==false){
				StringBuilder sb = new StringBuilder();
            	sb.append("");
            	sb.append(brojac);
            	String strI = sb.toString();
            	username = original;
				username=username+strI;
				brojac++;
			}
			
				
		}
		
		
		return username;
		
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
	
	public ZaposlenikBrDana DajZaposlenikViewModelZaZahtjev(String username) throws ZaposlenikNotFound{
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
	
	public ZaposlenikAccountVM DajZaposlenikAccVM(String username, String password) throws ZaposlenikNotFound{
		
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
	public ZaposlenikVM DajZaposlenikoveInformacije(String username) throws ZaposlenikNotFound {
		
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
	
	
	public ZaposlenikVM dajZaposlenikaZaUredjivanje (int id) throws ZaposlenikNotFound {
		
		
		Zaposlenik z = dajZaposlenikaPoId(id);
		
		ZaposlenikVM vm = new  ZaposlenikVM(z.getIme(),z.getPrezime(),z.getEmail(),z.getDatum_rodjenja(),z.getTelefon(),z.getAdresa_stanovanja(),z.getSektor_id(),z.getBroj_dana_godisnjeg(),z.getPrivilegija());
		
		
		return vm;
		
	}
	
	
	

	public Boolean promjeniSifru (String username, String password){
	
		Zaposlenik z = dajZaposlenikaBaza(username);
		z.setPassword(password);
		promjeniSifruBaza(z);
		
		return true;
	}
	

	
	public Zaposlenik pretvoriUZaposlenika (ZaposlenikVM zaposlenikVM,String username, String password){
		Zaposlenik zaposlenik = new Zaposlenik();
		zaposlenik.setIme(zaposlenikVM.ime);
		zaposlenik.setPrezime(zaposlenikVM.prezime);
		zaposlenik.setAdresa_stanovanja(zaposlenikVM.adresaStanovanja);
		zaposlenik.setBroj_dana_godisnjeg(zaposlenikVM.brojDanaGodisnje);
		zaposlenik.setDatum_rodjenja(zaposlenikVM.datumRodjenja);
		zaposlenik.setEmail(zaposlenikVM.email);
		zaposlenik.setSektor_id(zaposlenikVM.sektor);
		zaposlenik.setTelefon(zaposlenikVM.telefon);
		zaposlenik.setPrivilegija(zaposlenikVM.getPrivilegija());
		zaposlenik.setUsername(username);
		zaposlenik.setPassword(password);
		return zaposlenik;
	}
	
	
	public void ispisiZaposlenikeIzSektora (long id){
		
		List<Zaposlenik> listaZaposlenika = dajSveZaposlenikeBaza();
		for(int i = 0; i < listaZaposlenika.size();i++){
			
			if(listaZaposlenika.get(i).getSektor_id()==id){
				
				listaZaposlenika.get(i).setSektor_id(0);
				modificirajZaposlenikBaza(listaZaposlenika.get(i));
			}
			
			
		}
		
		
		
		
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
	
	public long dajIdPoUsernamuBaza (String username){
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik z = (Zaposlenik) criteria.uniqueResult(); 
		
		return z.getZaposlenik_id();
		
	}
	
	public void upisiUBazu(Zaposlenik zaposlenik){
		Transaction t = session.beginTransaction(); 
		session.save(zaposlenik);
   	    t.commit();
	}

	public Zaposlenik dajZaposlenikaPoId (long id){
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("zaposlenik_id", (long)id));
		return  (Zaposlenik) criteria.uniqueResult();
		
	}
	
	public Boolean provjeriUsernameBaza (String username){
		
		Criteria criteria = session.createCriteria(Zaposlenik.class);
		criteria.add(Restrictions.eq("username", username));
		Zaposlenik zaposlenik = (Zaposlenik)criteria.uniqueResult();
		
		return(zaposlenik==null);
		
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

	public long BrojZaposlenika() {
		// TODO Auto-generated method stub
		return brojZaposlenika+1;
	}

}
	

