package tim12.si.app.godisnji_odmori;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Model.Zahtjev;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;

public class ZahtjevControllerTest extends TestCase {
	
	private Zaposlenik z;
	private ZahtjevController zc;
	
	@Before
	public void postavi() {
		z = new Zaposlenik();
		z.setIme("Mujo");
		z.setPrezime("Mujic");
		
		zc = new ZahtjevController();
	}
	
	@Test
	public void testKreirajZahtjev() {
		
		int brojZPrije = zc.dajSveZahtjeve().size();
		
		ZahtjevVM zvm = kreirajZahtjevVM();
		
		Zahtjev z = new Zahtjev();
		try {
			z.setZahtjev_id(zc.kreirajZahtjev(zvm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(zc.dajSveZahtjeve().size(), brojZPrije+1);
		
	}
	
	@Test
	public void testObradiZahtjev() {
		
		ZahtjevVM zvm = kreirajZahtjevVM();
		
		Zahtjev z1 = new Zahtjev();
		Zahtjev z2 = new Zahtjev();
		try {
			z1.setZahtjev_id(zc.kreirajZahtjev(zvm));
			z2.setZahtjev_id(zc.kreirajZahtjev(zvm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		zc.obradiZahtjev(z1.getZahtjev_id());
		ArrayList<Zahtjev> neobradjeni = zc.dajNeobradjeneZahtjeve();
		
		assertEquals(neobradjeni.size(),1);
			
	}
	@Test
	public void testOdobriZahtjev() {
		
		ZahtjevVM zvm = kreirajZahtjevVM();
		Zahtjev z1 = new Zahtjev();
		try {
			z1.setZahtjev_id(zc.kreirajZahtjev(zvm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			zc.odobriZahtjev(z1.getZahtjev_id(), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(z1.getOdluka());
	}
	@Test
	public void testDajNeobradjeneZahtjeve() {
		
		ZahtjevVM zvm = kreirajZahtjevVM();
		
		Zahtjev z1 = new Zahtjev();
		Zahtjev z2 = new Zahtjev();
		try {
			z1.setZahtjev_id(zc.kreirajZahtjev(zvm));
			z2.setZahtjev_id(zc.kreirajZahtjev(zvm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(zc.dajNeobradjeneZahtjeve().size(), 2);
	}
	@Test
	public void testDajSveZahtjeve() {
		
		ZahtjevVM zvm = kreirajZahtjevVM();
		
		Zahtjev z1 = new Zahtjev();
		Zahtjev z2 = new Zahtjev();
		try {
			z1.setZahtjev_id(zc.kreirajZahtjev(zvm));
			z2.setZahtjev_id(zc.kreirajZahtjev(zvm));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		zc.obradiZahtjev(z1.getZahtjev_id());
		
		assertEquals(zc.dajSveZahtjeve().size(), 2);
	}
	
	public ZahtjevVM kreirajZahtjevVM()
	{
		ZahtjevVM zvm = new ZahtjevVM();
		
		zvm.podnosilacIme = z.getIme();
		zvm.podnosilacPrezime = z.getPrezime();
		
		Calendar c1 = new GregorianCalendar(2016,4,10);
		Date pocetak = c1.getTime();
		zvm.pocetakOdsustva = pocetak;
		
		Calendar c2 = new GregorianCalendar(2016,4,15);
		Date zavrsetak = c2.getTime();
		zvm.zavrsetakOdsustva = zavrsetak;
		
		zvm.tipOdsustva = "godisnji";
		zvm.obraden = false;
		zvm.odluka = false;
		zvm.opis = null;
		
		return zvm;
	}

}
