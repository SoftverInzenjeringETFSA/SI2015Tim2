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

	ZaposlenikController zc;
	ZaposlenikVM zvm;
	Zaposlenik zaposlenik;
	@Before 
	public void postavi()
	{
		//zaposlenikController = new ZaposlenikController();
		Date d= new Date(1994,10,10); 
		zvm= new ZaposlenikVM();
		zvm.ime="Neko";
		zvm.prezime="Nekic";
		zvm.adresaStanovanja="Kosevo";
		zvm.brojDanaGodisnje=20;
		zvm.datumRodjenja=d;
		zvm.email="n@gmail.com";
		zvm.privilegija=true;
		zvm.sektor=2;
		zvm.telefon="000000";
		
	}
	@Test
	//(expected = Exception.class)
	public void ProvjeraSeteraTest() 
	{ 
			Zaposlenik z= new Zaposlenik();
			z.setIme("M123ersed");
	}
	@Test
	public void TestDodajZaposlenika() throws Exception 
	{
		zc.DodajZaposlenika(zvm);
		assertEquals(zc.BrojZaposlenika(),1);
	}
	
	

}
