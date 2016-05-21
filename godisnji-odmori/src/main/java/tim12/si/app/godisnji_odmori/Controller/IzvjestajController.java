package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.View.*;

public class IzvjestajController {
	
	private Session session;
	
	public IzvjestajController(Session session)
	{
		this.session=session;
	}
	
	
	/** 
	 * @param godina
	 */
	public List<IzvjestajVM> dajGodisnjiIzvjestaj(int godina) {
		IzvjestajVM ivm= new IzvjestajVM();
		List<IzvjestajVM> lista = new ArrayList<IzvjestajVM>();	
		
		/*Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana(z.ime, z.prezime, "
				+ "(Select count(p1.prisustvo_id) From Prisustvo p1, Zaposlenik z1 WHere p1.zaposlenik_id = z1.zaposlenik_id AND z1.zaposlenik_id = z.zaposlenik_id), "
				+ "(Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1 WHERE o1.zaposlenik_id = z1.zaposlenik_id AND z1.zaposlenik_id = z.zaposlenik_id), "
				+ "z.broj_dana_godisnjeg - (Select count(o1.odsustvo_id) FROM Zaposlenik z1, Odsustvo o1, TipOdsustva to1 WHERE z1.zaposlenik_id = z.zaposlenik_id  AND z1.zaposlenik_id = o1.zaposlenik_id AND o1.tip = to1.id_odsustva AND to1.id_odsustva = 1)) "
				+ " FROM Zaposlenik z, Sektor s WHERE z.sektor_id = s.sektor_id  AND s.naziv = :sektor";
		Query q = session.createQuery(hql);
		q.setString("sektor", sektor);
		
		List l = q.list();
		t.commit();
		ArrayList<ZaposlenikBrDana> zvm = new ArrayList<ZaposlenikBrDana>();
		for (int i=0; i<l.size(); i++)
				zvm.add((ZaposlenikBrDana) l.get(i));
		return zvm;*/
		
		
		
		
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
