package tim12.si.app.godisnji_odmori.Controller;

import java.util.ArrayList;
import java.util.List;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import tim12.si.app.godisnji_odmori.Model.*;

public class ZahtjevController {
	

	/**
	 * 
	 * @param zahtjev
	 */
	public long kreirajZahtjev(ZahtjevVM zahtjev) throws Exception{
		Boolean flag = false;
		Zaposlenik zp = null;
		for(int i = 0; i<Zaposlenik.listaZaposlenika.size(); i++)
		{
			if (Zaposlenik.listaZaposlenika.get(i).getIme() == zahtjev.podnosilacIme &&
					Zaposlenik.listaZaposlenika.get(i).getPrezime() == zahtjev.podnosilacPrezime)
				{ 
					flag = true;
					zp = Zaposlenik.listaZaposlenika.get(i);
					break;				
				}
		}
		if (!flag) throw new Exception("Zaposlenik ne postoji.");
		
		Zahtjev z = new Zahtjev();
		z.setPodnosilac_id(zp.getZaposlenik_id());
		z.setPocetak_odsustva(zahtjev.pocetakOdsustva);
		z.setZavrsetak_odsustva(zahtjev.zavrsetakOdsustva);
		z.setTip_odsustva(zahtjev.tipOdsustva);
		z.setObradjen(false);
		z.setOdluka(null);
		z.setOpis(zahtjev.opis);
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

}
