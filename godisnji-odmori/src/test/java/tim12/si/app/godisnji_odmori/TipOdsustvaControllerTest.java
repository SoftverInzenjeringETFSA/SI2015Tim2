package tim12.si.app.godisnji_odmori;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tim12.si.app.godisnji_odmori.Controller.TipOdsustvaController;
import tim12.si.app.godisnji_odmori.Model.TipOdsustva;
import tim12.si.app.godisnji_odmori.ViewModel.TipOdsustvaVM;

public class TipOdsustvaControllerTest {
	
	TipOdsustvaController tzc;
	TipOdsustvaVM tovm;
	
	@Before 
	public void postavi()
	{
		tzc = new TipOdsustvaController();
		tovm = new TipOdsustvaVM();
	}

	@Test
	public void testDodajTipOdsustva() {
		
		tovm.naziv = "bajram";
		tovm.opis = "kurban";
		
		TipOdsustva to = new TipOdsustva();
		to.setId_odsustva(tzc.dodajTipOdsustva(tovm));
		
		assertEquals(tzc.dajSveTipove().size(),1);
	}

	@Test
	public void testObrisiTipOdsustva() {
		tovm.naziv = "bajram";
		tovm.opis = "kurban";
		
		TipOdsustva to = new TipOdsustva();
		to.setId_odsustva(tzc.dodajTipOdsustva(tovm));
		
		tzc.obrisiTipOdsustva(to.getId_odsustva());
		
		assertEquals(tzc.dajSveTipove().size(),0);
	}

	@Test
	public void testModificirajTipOdsustva() {
		tovm.naziv = "bajram";
		tovm.opis = "kurban";
		
		TipOdsustva to = new TipOdsustva();
		to.setId_odsustva(tzc.dodajTipOdsustva(tovm));
		
		tzc.modificirajTipOdsustva(to.getId_odsustva(), "bolest", "temperatura");
		
		assertEquals(to.getNaziv(), "bolest");
	}
	
	@Test
	public void testDajSveTipove()
	{
		tovm.naziv = "bajram";
		tovm.opis = "kurban";
		
		TipOdsustva to = new TipOdsustva();
		to.setId_odsustva(tzc.dodajTipOdsustva(tovm));
		
		tovm.naziv = "bajram";
		tovm.opis = "ramazanski";
		
		TipOdsustva to2 = new TipOdsustva();
		to2.setId_odsustva(tzc.dodajTipOdsustva(tovm));
		
		assertEquals(tzc.dajSveTipove(),2);
	}

}
