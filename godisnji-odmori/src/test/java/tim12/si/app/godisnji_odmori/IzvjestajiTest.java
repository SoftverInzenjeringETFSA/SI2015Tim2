package tim12.si.app.godisnji_odmori;

import java.util.*;
import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;
import tim12.si.app.godisnji_odmori.Controller.IzvjestajController;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Model.Sektor;

//model.izvjestaj
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajVM;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZaposlenikBrDanaVM;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZapVM;

public class IzvjestajiTest extends TestCase {
	private Zaposlenik z;
	private IzvjestajController ic;
	private Sektor s;
	private IzvjestajVM i;
	private IzvjestajZapVM iz;
	
	private List<IzvjestajVM> lista;
	private List<IzvjestajZaposlenikBrDanaVM> lista2;

	@Before
	public void postavi() {
		z = new Zaposlenik();
		z.setIme("Mujo");
		z.setPrezime("Mujic");
		z.setBroj_dana_godisnjeg(30);
		z.setSektor_id(4);
		
		s=new Sektor();
		s.setSektor_id(4);

	//	ic = new IzvjestajController();
		
		i = new IzvjestajVM();
		i.ukupnoNeradni=30;
		i.ukupnoRadni=1000;
		
		iz = new IzvjestajZapVM();
		iz.neradniDani= 2;
		iz.radniDani=5;
		//iz.ukupnoNeradniDana=30;
		//iz.ukupnoRadniDana=1000;
		iz.sektor= "Sektor1";
		iz.zaposlenikIme="Fahrudin";
		iz.zaposlenikPrezime="Brbutovic";
		
		lista= new ArrayList<IzvjestajVM>();
		lista2= new ArrayList<IzvjestajZaposlenikBrDanaVM>();
				
	}
	
	@Test
	public void testdajGodisnjiIzvjestaj() {
		
		//lista = ic.dajGodisnjiIzvjestaj(1995);
		assertEquals(1, lista.size());

          }
	@Test
	public void testdajMjesecniIzvjestaj() {

		lista = ic.dajMjesecniIzvjestaj(2, 2005);
		assertEquals(1, lista.size());

          }
	@Test
	public void testdajPreostaliBrojDanaPoKorisniku() {

		lista2= ic.dajPreostaliBrojDanaPoKorisniku();
		assertEquals(lista2.size(), 1);

          }
}
