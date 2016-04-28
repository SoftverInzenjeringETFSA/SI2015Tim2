package tim12.si.app.godisnji_odmori;


import java.util.*;
import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;
import tim12.si.app.godisnji_odmori.Controller.IzvjestajController;
import tim12.si.app.godisnji_odmori.Controller.SektorController;
import tim12.si.app.godisnji_odmori.Model.Sektor;

//model.izvjestaj
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajVM;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZaposlenikBrDanaVM;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZapVM;
import tim12.si.app.godisnji_odmori.ViewModel.SektorVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikUSektor;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;


public class SektoriTest extends TestCase {
	
	private Zaposlenik z1;
	private Zaposlenik z2;
	private ZaposlenikVM z3;
	private ZaposlenikVM z4;
	private SektorController sc;
	private Sektor s;
	private Sektor s2;
	private SektorVM sm;

	private List<Zaposlenik> l=new ArrayList<Zaposlenik> () ;
	private List<ZaposlenikVM> lista=new ArrayList<ZaposlenikVM> () ;
	
	@Before
	public void postavi() {
		
		sm.brojUposlenih=2;
		sm.maxBrojOdsutnih=1;
		sm.naziv = "Sektor1";
		sm.opis="Opis";
		
		z1 = new Zaposlenik();
		z1.setIme("Mujo");
		z1.setPrezime("Mujic");
		z1.setBroj_dana_godisnjeg(30);
		z1.setSektor_id(4);
		
		z3 = new ZaposlenikVM();
		z3.ime = "Fahrudin";
		z3.prezime= "Brbutovic";
		z3.brojDanaGodisnje = 30;
		z3.sektor = "Sektor1";
		
		z4 = new ZaposlenikVM();
		z4.ime = "Dino";
		z4.prezime= "Ahmetovic";
		z4.brojDanaGodisnje = 30;
		z4.sektor = "Sektor1";
		
		
		z2 = new Zaposlenik();
		z2.setIme("Sujo");
		z2.setPrezime("Sujic");
		z2.setBroj_dana_godisnjeg(30);
		z2.setSektor_id(4);
		
		s=new Sektor();
		s.setSektor_id(4);
		s.setOpis("Opis");
		s.setBroj_uposlenih(0);
		s.setGodina_osnivanja(2012);
		s.setMax_broj_odsutnih(3);
		s.setNaziv("Sektor1");
		
		s2=new Sektor();
		s2.setSektor_id(1);
		s2.setOpis("Opis2");
		s2.setBroj_uposlenih(0);
		s2.setGodina_osnivanja(2012);
		s2.setMax_broj_odsutnih(3);
		s2.setNaziv("Sektor2");


		
		
		sc = new SektorController();
		//sc.dodajSektor(s);
		l.add(z1);
		l.add(z2);
		
		lista.add(z3);
		lista.add(z4);
	}

	
	@Test
	public void testdajNazivSektora() {
		
		assertEquals(s.getNaziv(), "Sektor1");

          }

	@Test
	public void testdodajZaposlenikeUSektor () {

		sc.dodajZaposlenikeUSektor(s.getSektor_id(), lista );
		assertEquals(s.getBroj_uposlenih(), 2 );

          }


	public void testmodificirajSektor(){
		s.setOpis("AAA");
		s2.setOpis("AAA");
		s.setGodina_osnivanja(2000);
		s2.setGodina_osnivanja(2000);
		s.setNaziv("Sektor2");;
		s.setSektor_id(1);
		assertSame(s, s2);

          }

	public void testdodajSektor() {

		sc.dodajSektor(s);
		sc.dodajSektor(s2);

		assertEquals(sc.dajBrojSektora(), 2);

          }

	public void testobrisiSektor() {

		sc.obrisiSektor(s.getSektor_id());
		sc.obrisiSektor(s2.getSektor_id());
		assertEquals(sc.dajBrojSektora(), 0);

          }

}
