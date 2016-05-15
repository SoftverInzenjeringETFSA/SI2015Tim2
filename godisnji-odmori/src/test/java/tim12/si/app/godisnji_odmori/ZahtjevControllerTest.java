package tim12.si.app.godisnji_odmori;

import java.util.*;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Model.Zahtjev;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

public class ZahtjevControllerTest extends TestCase {
	private Zahtjev zahtjev;
	private Zaposlenik zap;
	private Zaposlenik zap1;
	private ZahtjevController zc;
	private ZahtjevVM zvm;
	private ZaposlenikBrDana zbrd;
	List<Zaposlenik> lista= new ArrayList<Zaposlenik>();
	List<Zahtjev> listaZahtjeva= new ArrayList<Zahtjev>();
	@Before
	public void postavi()
	{	//jedan zaposlenik
		zap = new Zaposlenik();
		zap.setIme("Ajla");
		zap.setPrezime("Alic");
		zap.setAdresa_stanovanja("Neka adresa 15");
		zap.setBroj_dana_godisnjeg(30);
		Calendar c = new GregorianCalendar(1993,3,2);
		zap.setDatum_rodjenja(c.getTime());
		zap.setEmail("aalic1@gmail.com");
		zap.setSektor_id(1);
		zap.setTelefon("061/123-123");
		zap.setZaposlenik_id((long)1);
		lista.add(zap);
		
		//drugi zaposlenik
		zap1 = new Zaposlenik();
		zap1.setIme("Mer");
		zap1.setPrezime("Alic");
		zap1.setAdresa_stanovanja("Neka adresa 13");
		zap1.setBroj_dana_godisnjeg(30);
		Calendar c1 = new GregorianCalendar(1993,3,2);
		zap1.setDatum_rodjenja(c1.getTime());
		zap1.setEmail("aalic1@gmail.com");
		zap1.setSektor_id(1);
		zap1.setTelefon("061/123-123");
		zap1.setZaposlenik_id((long)2);
		lista.add(zap1);
		
		
		zahtjev.setNalaz(null);
		zahtjev.setObradjen(null);
		zahtjev.setOdluka(null);
		zahtjev.setOpis("Zahtjev za bolovanje");
		Date d= new Date(2016,5,5);
		zahtjev.setPocetak_odsustva(d);
		zahtjev.setPodnosilac_id(zap.getZaposlenik_id());
		zahtjev.setSektor_id(zap.getSektor_id());
		zahtjev.setTip_odsustva(0);
		zahtjev.setZahtjev_id(1);
		Date d1= new Date(2016,5,12);
		zahtjev.setZavrsetak_odsustva(d1);
		listaZahtjeva.add(zahtjev);
		
		zvm.setBrojRadnihDana((long)100);
		zvm.setIdZahtjeva(zahtjev.getZahtjev_id());
		zvm.setNalaz(null);
		zvm.setNazivSektora("IT");
		zvm.setObraden(null);
		zvm.setOdluka(null);
		zvm.setOpis("Opis");
		zvm.setPocetakOdsustva(d);
		zvm.setPodnosilacIme("Haso");
		zvm.setPodnosilacPrezime("Hasic");
		zvm.setTipOdsustva((long)1);
		zvm.setUsernamePodnosioca("username");
		zvm.setZavrsetakOdsustva(d1);
		
		zbrd.setDaniBolovanja((long)2);
		zbrd.setDaniNeplaniranog((long)3);
		zbrd.setIskoristeniGodisnji((long)5);
		zbrd.setNeradniDani(8);
		zbrd.setPreostaloSlobodnih((long)15);
		zbrd.setRadniDani((long)20);
		zbrd.setSektor("IT");
		zbrd.setZaposlenik_id(zap.getZaposlenik_id());
		zbrd.setZaposlenikIme(zap.getIme());
		zbrd.setZaposlenikPrezime(zap.getPrezime());
	}
	 @Test(expected = Exception.class)
	public void testkreirajZahtjev()
	{
		
		long i=0;//nije kreirao
		try{
		i=zc.kreirajZahtjev(zvm);//kreirao i= neki broj
		}
		catch (Exception e) {
			
		}
		assertNotSame(i,0);
		
	}
	@Test
	public void testodobriZahtjev()
	{	int i = 0;
		try{
			
			zahtjev.setZahtjev_id(zc.kreirajZahtjev(zvm));
			//zahtjev postoji kreiran je
			//i je jednako 1
			i=zc.odobriZahtjev(zahtjev.getZahtjev_id(), zbrd, zvm); 

		}
		catch (Exception e) {
			
		}
		assertNotSame(i,0);
		
	}

	@Test(expected = Exception.class)
	public void testodbijZahtjev()
	{
		
		Zahtjev z= new Zahtjev();
		int i=0;
		try
		{
			z.setZahtjev_id(zc.kreirajZahtjev(zvm));
			i=zc.odbijZahtjev(z.getZahtjev_id(), zbrd, zvm);//odbij zahtjev 
		}
		catch (Exception e) {
			
		}
		assertEquals(i, 1);
		
	}

	@Test
	public void testDajSveZahtjeve() {
		
		
		Zahtjev z1 = new Zahtjev();
		Zahtjev z2 = new Zahtjev();
		try {
			z1.setZahtjev_id(zc.kreirajZahtjev(zvm));//jedan zahtjev sektor IT
			z2.setZahtjev_id(zc.kreirajZahtjev(zvm));//drugi zahtjev sektor IT
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		zc.obradiZahtjev(z1.getZahtjev_id());
		zc.obradiZahtjev(z2.getZahtjev_id());
		
		assertEquals(zc.dajSveZahtjeveIzSektora("IT"), 2);
	}


}
