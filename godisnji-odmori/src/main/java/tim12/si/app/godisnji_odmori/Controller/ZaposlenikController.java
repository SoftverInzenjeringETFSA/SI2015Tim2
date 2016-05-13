package tim12.si.app.godisnji_odmori.Controller;


import java.util.ArrayList;
import java.util.Date;
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
	/**
	 * 
	 * @param zaposlenik
	 */	

	public ZaposlenikController(Session session)
	{
		this.session = session;
	};
	//dodaje zaposlenika u listu
	public void DodajZaposlenika() 
	{
		try
			{
				//zvm = null;
				String ime= zvm.getIme();
				String prezime= zvm.prezime;
				//na osnovu imena sektora iz baze dobiti nejgov ID
				//int sectorID=zvm.sektor_id;;
				Date datumRodjenja=zvm.datumRodjenja;
				String email=zvm.email;
				String adresaStanovanja=zvm.adresaStanovanja;
				String telefon= zvm.telefon;
				int brojDanaGodisnje= zvm.brojDanaGodisnje;
				Boolean privilegija=zvm.privilegija;
				//Zaposlenik z= new Zaposlenik(ime,prezime,sectorID,datumRodjenja,email,adresaStanovanja,telefon,brojDanaGodisnje,privilegija);
				//Zaposlenik.listaZaposlenika.add(z);
		}
		catch (Exception e)
			{
				System.err.println("Dodavanje zaposlenika nije uspjelo: " + e.getMessage());
			}
	}
	public int BrojZaposlenih()
	{
		return Zaposlenik.listaZaposlenika.size();
	}

	public void ModificirajZaposlenika(Zaposlenik zaposlenik)
	{
		try
		{	
			long index=PronadjiIndexZaposlenika(zaposlenik.getZaposlenik_id());
			Zaposlenik.listaZaposlenika.remove(index);
			//modificiramo sve elemente
			zvm = null;
			String ime= zvm.getIme();
			String prezime= zvm.prezime;
			//isto kao i gore
			//int sectorID=zvm.sektor_id;;
			//nesto
			Date datumRodjenja=zvm.datumRodjenja;
			String email=zvm.email;
			String adresaStanovanja=zvm.adresaStanovanja;
			String telefon= zvm.telefon;
			int brojDanaGodisnje= zvm.brojDanaGodisnje;
			Boolean privilegija=zvm.privilegija;
			//Zaposlenik z= new Zaposlenik(ime,prezime,sectorID,datumRodjenja,email,adresaStanovanja,telefon,brojDanaGodisnje,privilegija);
			//dodaj ga na isti broj indexa
			//Zaposlenik.listaZaposlenika.add(index, z);
	}
	catch (Exception e)
		{
			System.err.println("Modificiranje zaposlenika nije uspjelo: " + e.getMessage());
		}
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
	public void ObrisiZaposlenika(int zaposlenikID)
	{
		try
		{
			Zaposlenik.listaZaposlenika.remove(zaposlenikID);
		}
		catch (Exception e)
		{
			System.err.println("Brisanje zaposlenika nije uspjelo: " + e.getMessage());
		}
		
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
				"z.prezime, count(p.prisustvo_id), z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to WHERE z1.username = :username  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to.id_odsustva AND to.naziv = 'godišnji odmor')) "
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
}
