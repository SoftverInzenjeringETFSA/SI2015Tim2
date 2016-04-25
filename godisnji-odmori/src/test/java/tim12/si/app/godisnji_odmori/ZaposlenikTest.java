package tim12.si.app.godisnji_odmori;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
public class ZaposlenikTest {

	ZaposlenikController zaposlenikController;
	ZaposlenikVM zaposlenikVM;
	Zaposlenik zaposlenik;
	@Before 
	public void postavi()
	{
		zaposlenikController = new ZaposlenikController();
		zaposlenikVM= new ZaposlenikVM();
		
	}
	@Test
	public void TestDodajZaposlenika() throws ParseException 
	{
		String date_s = "2011-01-18";  
	    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = dt.parse(date_s);
		/*zaposlenikVM.ime = "Merseda";
		zaposlenikVM.prezime="Camdzic";
		zaposlenikVM.sektor="IT";
		zaposlenikVM.datumRodjenja=date;
		zaposlenikVM.email="merseda@gmail.com";
		zaposlenikVM.adresaStanovanja="Kosevo 4";
		zaposlenikVM.telefon="1234567";
		zaposlenikVM.brojDanaGodisnje=15;
		zaposlenikVM.privilegija=false;*/
		Zaposlenik zaposlenik= new Zaposlenik("Merseda","Camdzic",1,date,"mers@gmail.com","Kosevo 4","1234567",15,false);
		zaposlenikController.DodajZaposlenika(zaposlenik);
		assertEquals(zaposlenikController.BrojZapolsenih(),1);
	}
	/*@Test(expected = UnsupportedOperationException.class)
	public void TestBrojZaposlenihError() throws ParseException 
	{
		String date_s = "2011-01-18";  
	    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = dt.parse(date_s);

		Zaposlenik zaposlenik= new Zaposlenik("Merseda","Camdzic",1,date,"mers@gmail.com","Kosevo 4","1234567",15,false);
		//zaposlenikController.DodajZaposlenika(zaposlenik);
		assertEquals(zaposlenikController.BrojZapolsenih(),1);
	}*/
	
	@Test
	public void TestModificirajZaposlenika() throws ParseException
	{
		String date_s = "2011-01-18";  
	    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = dt.parse(date_s);
		List<Zaposlenik> listaZaposlenika= new ArrayList<Zaposlenik>();
		Zaposlenik zaposlenik= new Zaposlenik("Merseda","Camdzic",1,date,"mers@gmail.com","Kosevo 4","1234567",15,false);
		listaZaposlenika.add(zaposlenik);
		zaposlenikController.ModificirajZaposlenika(zaposlenik);
		assertEquals("Merseda",zaposlenik.getIme());
	}
	
	

}
