package tim12.si.app.godisnji_odmori;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.Controller.*;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import java.util.Date;
import java.util.List;

public class OdsustvoTest extends TestCase {
	
	OdsustvoController oC = new OdsustvoController ();
	
	public void testdodajOdsustvo () {
		
		Odsustvo odsustvo = new Odsustvo();
		odsustvo.setZaposlenik_id(0);
		odsustvo.setDatum( new Date (7/25/2016));
		odsustvo.setOpis("Testni opis");
		odsustvo.setTip(1);
		
		
		
		oC.dodajOdsustvo(odsustvo);
		
		//Koda nakon sto se upise u bazu provjeriti da li je stvarno upisano
		
		
	
		assertTrue(true);
		
          }
	
	public void testdajOdsustvaPoTipu () {
		
		
		List<OdsustvoVM> listaPoTipu = oC.dajOdsustvaPoTipu(1);
		
		//Provjeriti broj ovih koje dobijamo sa brojem u bazi
		
		
		assertTrue(true);
		
          }
	
	public void testdajOdsustvaPoZaposleniku () {
		
         List<OdsustvoVM> listaPoTipu = oC.dajOdsutvaPoZaposleniku(1);
		
		//Provjeriti broj ovih koje dobijamo sa brojem u bazi
		
		assertTrue(true);
		
          }
	
	public void testdajOdsustvaPoPeriodu () {
		
		
		
		
		assertTrue(true);
		
          }
	public void testdajOdsustvaPoSektoru () {
		
		
		assertTrue(true);
		
          }
	public void testdajSvaOdsustva () {
		
		
		assertTrue(true);
		
          }

	

}
