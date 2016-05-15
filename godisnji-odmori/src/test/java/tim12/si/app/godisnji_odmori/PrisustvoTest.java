package tim12.si.app.godisnji_odmori;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import junit.framework.Assert;
import tim12.si.app.godisnji_odmori.Controller.PrisustvoController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.Model.Prisustvo;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.PrisustvoVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

public class PrisustvoTest {

	PrisustvoController pc;
	PrisustvoVM pVM;
	Prisustvo p;
	Zaposlenik z;
	@Test
	public void TestEvidentirajPrisustvo()
	{
		Date d= new Date(2016,5,15);
		pc.evidentirajPrisustvo(z.getZaposlenik_id(), d);
		boolean x=pc.provjeriDaLiPostoji(z.getZaposlenik_id(), d);
		assertEquals(true, x);
	}
	@Test
	public void TestEvidentirajPrisustvo1()
	{
		Date d= new Date(2016,5,15);
		//pc.evidentirajPrisustvo(z.getZaposlenik_id(), d);
		boolean x=pc.provjeriDaLiPostoji(z.getZaposlenik_id(), d);
		assertEquals(false, x);
	}

}
