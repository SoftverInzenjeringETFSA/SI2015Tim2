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
	ZaposlenikVM zvm;
	Zaposlenik zaposlenik;
	@Before 
	public void postavi()
	{
		//zaposlenikController = new ZaposlenikController();
		zvm= new ZaposlenikVM();
		
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
		
	}
	
	

}
