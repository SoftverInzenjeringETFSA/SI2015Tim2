package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.View.*;

public class IzvjestajController {
	
	private Session session;
	private SektorController sc;
	public IzvjestajController(Session session)
	{
		this.session=session;
	}
	
	
	/** 
	 * @param godina
	 */
	public List<IzvjestajVM> dajGodisnjiIzvjestaj(Sektor sektor,int godina) {
		IzvjestajVM ivm= new IzvjestajVM();
		List<IzvjestajVM> lista = new ArrayList<IzvjestajVM>();	
		
		
		String naziv=sc.dajNazivSektora((int)sektor.getSektor_id());
		Transaction t = session.beginTransaction();
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana(s.naziv, z.ime, "+
				"z.prezime, count(p.prisustvo_id)"
				+ "FROM Zaposlenik z, Sektor s, Prisustvo p "
				+ "WHERE s.naziv = :naziv AND s.sektor_id = z.sektor_id AND z.zaposlenik_id = p.zaposlenik_id AND YEAR(p.datum)=:godina";
		Query q = session.createQuery(hql);
		q.setString("sektor", naziv);
		
		List l = q.list();
		t.commit();
		for (int i=0; i<l.size(); i++)
				lista.add((IzvjestajVM) l.get(i));

		return lista;
	}

	/**
	 * @param mjesec
	 * @param godina
	 */
	
	public  ArrayList<IzvjestajZapVM> nadjiPoImenu(String sektorIme) {
		sektorIme = sektorIme.trim();
		sektorIme = sektorIme.toLowerCase();
		sektorIme = "%" + sektorIme + "%";
		Transaction t = session.beginTransaction();
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZapVM;(s.ime, s.zaposlenikIme, "+
				"s.zaposlenikPrezime, s.radniDani, s.neradniDani) FROM Sektor s WHERE s.ime like :sektor " +
				"ORDER BY s.sektor asc";
		Query query = session.createQuery(hql);
		query.setString("ime", sektorIme);
		List<IzvjestajZapVM> rezultati = query.list();
		t.commit();
		ArrayList<IzvjestajZapVM> nadjeniSektori = new ArrayList<IzvjestajZapVM>(rezultati);
		return nadjeniSektori;
	}
	
	public List<IzvjestajVM> dajMjesecniIzvjestaj(int mjesec, int godina) {
		IzvjestajVM ivm= new IzvjestajVM();
		IzvjestajZapVM izvm= new IzvjestajZapVM();
		List<IzvjestajVM> lista = new ArrayList<IzvjestajVM>();
		return lista;
	}

	public List<IzvjestajZaposlenikBrDanaVM> dajPreostaliBrojDanaPoKorisniku() {
		IzvjestajVM ivm= new IzvjestajVM();
		IzvjestajZaposlenikBrDanaVM izbrdvm= new IzvjestajZaposlenikBrDanaVM();
		List<IzvjestajZaposlenikBrDanaVM> lista = new ArrayList<IzvjestajZaposlenikBrDanaVM>();
		return lista;
	}

}
