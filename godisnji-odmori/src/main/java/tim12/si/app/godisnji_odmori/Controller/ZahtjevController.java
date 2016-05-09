package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZahtjevController {
	
	private Session session;
	/**
	 * 
	 * @param zahtjev
	 */
	public ZahtjevController(Session session)
	{
		this.session = session;
	}
	public long kreirajZahtjev(ZahtjevVM zahtjev) throws Exception{
		Boolean flag = false;
		Zaposlenik zp = null;
		for(int i = 0; i<Zaposlenik.listaZaposlenika.size(); i++)
		{
			if (Zaposlenik.listaZaposlenika.get(i).getIme() == zahtjev.getPodnosilacIme() &&
					Zaposlenik.listaZaposlenika.get(i).getPrezime() == zahtjev.getPodnosilacPrezime())
				{ 
					flag = true;
					zp = Zaposlenik.listaZaposlenika.get(i);
					break;				
				}
		}
		if (!flag) throw new Exception("Zaposlenik ne postoji.");
		
		Zahtjev z = new Zahtjev();
		z.setPodnosilac_id(zp.getZaposlenik_id());
		z.setPocetak_odsustva(zahtjev.getPocetakOdsustva());
		z.setZavrsetak_odsustva(zahtjev.getZavrsetakOdsustva());
		z.setTip_odsustva(zahtjev.getTipOdsustva());
		z.setObradjen(false);
		z.setOdluka(null);
		z.setOpis(zahtjev.getOpis());
		z.setSektor_id(zp.getSektor_id());
		
		return z.getZahtjev_id();
	}

	/**
	 * 
	 * @param zahtjevID
	 */
	public void obradiZahtjev(long zahtjevID) {
		// ne kontam koja je razlika izmedju ove metode i odobriZahtjev ?
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zahtjevID
	 * @param odobren
	 */
	public void odobriZahtjev(long zahtjevID, Boolean odobren) throws Exception{
		Boolean flag = false;
		int temp = -1;
		for(int i = 0; i<Zahtjev.listaZahtjeva.size(); i++)
		{
			if (Zahtjev.listaZahtjeva.get(i).getZahtjev_id() == zahtjevID)
				{ 
					flag = true;
					temp = i;
					break;				
				}
		}
		if (!flag) throw new Exception("Zahtjev ne postoji.");
		
		Zahtjev.listaZahtjeva.get(temp).setObradjen(true);
		Zahtjev.listaZahtjeva.get(temp).setOdluka(true);
	}

	public ArrayList<Zahtjev> dajNeobradjeneZahtjeve() {
		List<Zahtjev> neobradjeni = new ArrayList<Zahtjev>();
		
		for(int i = 0; i<Zahtjev.listaZahtjeva.size(); i++)
		{
			if (Zahtjev.listaZahtjeva.get(i).getObradjen() == false)
				neobradjeni.add(Zahtjev.listaZahtjeva.get(i));
		}
		
		return (ArrayList<Zahtjev>) neobradjeni;
	}

	public ArrayList<Zahtjev> dajSveZahtjeve() {
		return (ArrayList<Zahtjev>) Zahtjev.listaZahtjeva;
	}
	
	public ArrayList<ZahtjevVM> dajSveZahtjeveIzSektora(String sektor)
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM(z.ime, z.prezime, s.naziv, za.zahtjev_id) "
				+ "FROM Zaposlenik z, Sektor s, Zahtjev za "
				+ "WHERE s.naziv = :sektor AND s.sektor_id = z.sektor_id AND z.zaposlenik_id = za.podnosilac_id AND (za.obradjen = null OR za.obradjen = 0) ";
		Query q = session.createQuery(hql);
		q.setString("sektor", sektor);
		
		List l = q.list();
		t.commit();
		/*if(l.isEmpty())
			throw new ZaposlenikNotFound("Zaposlenik s username-om: " + username + " nije pronadjen.");*/
		ArrayList<ZahtjevVM> zvm = new ArrayList<ZahtjevVM>();
		for (int i=0; i<l.size(); i++)
				zvm.add((ZahtjevVM) l.get(i));
		return zvm;
	}

}
