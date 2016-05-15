package tim12.si.app.godisnji_odmori;


import junit.framework.TestCase;
import junit.framework.TestSuite;
import tim12.si.app.godisnji_odmori.Model.*;
import tim12.si.app.godisnji_odmori.Controller.*;
import tim12.si.app.godisnji_odmori.ViewModel.*;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OdsustvoTest extends TestCase {
	TipOdsustvaVM to;
	TipOdsustvaVM to1;
	TipOdsustvaController tc;
	OdsustvoController oC= new OdsustvoController();
	@Test
	public void testdodajOdsustvo () {
		
		to.naziv="Odsustvo1";
		to.opis="opis1";
		boolean i=false;
		int inc=tc.dodajTipOdsustva(to);
		
		if(inc!=0)
		{
			i=true;

		assertEquals(true, i);
          }
	}
		public void testdajImeTipaOdsustva ()
		{
			to.naziv="Odsustvo1";
			to.opis="opis1";
			to1.naziv="Odsustvo2";
			to1.opis="opis2";
			String naziv=tc.dajImeTipaOdsustva((long)to.hashCode());
			assertEquals(naziv,"Odsustvo1" );
	          }
		
	@Test
	public void testdajOdsustvaPoTipu () {
		
		
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Kerim", "Balic","Godisnji"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Kerim", "Balic","Godisnji"));
		
		List<OdsustvoVM> listaPoTipu = oC.dajOdsustvaPoTipu(1);
		
		//TIpOdsustvva godisnji treba u bazi da bude pod ID 1 ??
		
		assertEquals(listaPoTipu.size(),2);
		
          }
	@Test
	public void testdajOdsustvaPoZaposleniku () {
		
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Kerim", "Balic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Kerim", "Balic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Kerim", "Balic","Bolest"));
		
		List<OdsustvoVM> listaPoZaposleniku = oC.dajOdsutvaPoZaposleniku(1);
		
		//Zaposlenik Kerim Balic treba u bazi da bude pod ID 1 ??
		
		assertEquals(listaPoZaposleniku.size(),3);
		
          }
	@Test
	public void testdajOdsustvaPoPeriodu () {
		
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(5/6/2016),"Kerim", "Balic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/6/2016),"Kerim", "Balic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(16/6/2016),"Kerim", "Balic","Bolest"));
		
		List<OdsustvoVM> listaPoPeriodu=oC.dajOdsustvaPoPeriodu(new Date (10/6/2016), new Date(28/6/2016));
		
		
		assertEquals(listaPoPeriodu.size(),2);
		
          }
	@Test
	public void testdajOdsustvaPoSektoru () {
		
		
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Timur", "Cerimagic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Timur", "Cerimagic","Bolest"));
		oC.dodajOdsustvo(kreirajOdsustvo(new Date(25/7/2016),"Timur", "Cerimagic","Bolest"));
		
		List<OdsustvoVM> listaPoSektoru=oC.dajOdsustvaPoSektoru(2);
		
		//timur treba biti u sektori razlictiom od kerima i ajle i da mu je sketor ID 2
		
		assertEquals(listaPoSektoru.size(),3);
		
          }
	@Test
	public void testdajSvaOdsustva () {
		
		assertEquals(oC.dajSvaOdsustva("IT"),44);
		
		//44 je prouzvoljan broj i treba provjerit u bazi koje ce biti prije nego sto se testira
		
          }
	
	public OdsustvoVM kreirajOdsustvo (Date datum, String ime, String prezime,String tip) {
		
		OdsustvoVM odsustvo = new OdsustvoVM();
		
		odsustvo.zaposlenikIme=ime;
		odsustvo.zaposlenikPrezime=prezime;
		odsustvo.datum= datum;
		odsustvo.tipOdsustva=tip;
		odsustvo.opis="Imam visoku temperaturu";
		
		return odsustvo;
		
			
	


		}
	

}
