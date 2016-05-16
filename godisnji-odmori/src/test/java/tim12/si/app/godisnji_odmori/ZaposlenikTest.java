package tim12.si.app.godisnji_odmori;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

public class ZaposlenikTest {
	
	Zaposlenik zap;
	
	@Before
	public void setUp() throws Exception {
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
		
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t= sess.beginTransaction();
		
		long id=(Long) sess.save(zap);
		
		zap.setZaposlenik_id(1);
		
		t.commit();
		sess.close();
	}
	
	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.delete(zap);
		
		t.commit();
		sess.close();
	}

	@Test
	public void testDodajZaposlenika() throws Exception {
		Session sess = null;
		
		try {
			sess=HibernateUtil.getSessionFactory().openSession();
			
			ZaposlenikController zc= new ZaposlenikController(sess);
			ZaposlenikVM zvm=new ZaposlenikVM();
			zvm.setIme("Ajla");
			zvm.setPrezime("Alic");
			Calendar c = new GregorianCalendar(1986,5,12);
			zvm.setDatumRodjenja(c.getTime());
			zvm.setBrojDanaGodisnje(30);
			zvm.setSektor(1);
			zvm.setEmail("hhasic1@gmail.com");
			zvm.setTelefon("061/543-543");
			zvm.setAdresaStanovanja("Adresa 5");
			
			List<String> tmp= zc.DodajZaposlenika(zvm);
			String x= "DELETE from Zaposlenik z " +
					"WHERE z.ime = :ime AND z.prezime=:prezime";
			
			Query p = sess.createQuery(x);
			p.setString("ime", zvm.getIme());
			p.setString("prezime", zvm.getPrezime());

			//p.setString("Id",zvm.get);
			p.executeUpdate();
			assertEquals(tmp.isEmpty(),true);
		}
		catch (ZaposlenikNotFound e){
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (sess!=null)
				sess.close();
		}
	}
	
	@Test
	 public void testObrisiZaposlenika()
		{
		ZaposlenikController zc=new ZaposlenikController();
		Calendar c = new GregorianCalendar(1993,3,2);
		List<String> l= zc.DodajZaposlenika(new ZaposlenikVM("Ajla", "Alic", "aalic1@gmail.com", c.getTime(),"061/532-653","Adresa 1", "IT","30",false ));
		
		zc.obrisiZaposlenika(1);
			
			try 
			{
				assertEquals("NekoIme", zc.dajZaposlenikaPoId(1).getIme());
			} 
			catch (Exception e)
			{
				assertEquals(e.getMessage(),"Trazeni zaposlenik ne postoji!");
				Logger.getLogger(ZaposlenikController.class).error(e.getMessage());
			}	
			
		}
	
	@Test
	public void testNadjiPoIdu() {
		ZaposlenikController zc=new ZaposlenikController();
		Calendar c = new GregorianCalendar(1993,3,2);
		List<String> l= zc.DodajZaposlenika(new ZaposlenikVM("Ajla", "Alic", "aalic1@gmail.com", c.getTime(),"061/532-653","Adresa 1", "IT","30",false ));
		
		Zaposlenik z = new Zaposlenik();
		try{
			z=zc.dajZaposlenikaPoId(1);
			assertEquals(z.getIme(),"Ime");
		}
		catch (Exception e)
		{
			assertEquals(e.getMessage(), "Trazeni zaposlenik ne postoji");
			Logger.getLogger(ZaposlenikController.class).error(e.getMessage());
		}
	}
	
	@Test
	public void testModificirajZaposlenika()
	{
		ZaposlenikController zc=new ZaposlenikController();
		Calendar c = new GregorianCalendar(1993,3,2);
		List<String> l= zc.DodajZaposlenika(new ZaposlenikVM("Ajla", "Alic", "aalic1@gmail.com", c.getTime(),"061/532-653","Adresa 1", "IT","30",false ));
		
		zc.ModificirajZaposlenika(new ZaposlenikVM("Ajla", "Alic", "aalic1@gmail.com", c.getTime(),"061/532-653","Adresa 1", "IT","30",false ), 1);
		
		Zaposlenik z=new Zaposlenik();
		try
		{		
			z = zc.dajZaposlenikaPoId(1);
			assertEquals(z.getIme(),"NekoIme");
        }
		
		catch (Exception e) 
		{
			assertEquals(e.getMessage(),"Trazeni zaposlenik ne postoji!");
			Logger.getLogger(ZaposlenikController.class).error(e.getMessage());
		}
	}
	
	@Test
	public void testDajSveZaposlenike(){
		ZaposlenikController zc=new ZaposlenikController();
		Calendar c = new GregorianCalendar(1993,3,2);
		List<String> l= zc.DodajZaposlenika(new ZaposlenikVM("Ajla", "Alic", "aalic1@gmail.com", c.getTime(),"061/532-653","Adresa 1", "IT","30",false ));
		Object[][] list=zc.dajSveZaposlenike();
		assertTrue(list.length>0);
	}
	
	@Test
	public void testZaposlenika() {
		Zaposlenik k = new Zaposlenik();
		k.setUsername("testUserK");
		k.setPassword("testPassword123");

		// Najprije je potrebnono Napraviti sesiju i otvoriti je.
		// Ovo radimo u GUI-u nakon poziva
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			// Session proslijedimo BLL klasi konstruktorom - Dependency
			// Injection
			// BLL klasa sprema kao privatni atribut sesiju koja joj je
			// prosljeđena kroz konstruktor

			// U svakoj BLL metodi otvaramo transakciju
			Transaction t = session.beginTransaction();

			// Radimo nešto u metodi
			long novi_id = (Long) session.save(k);

			assertEquals("Id zaposlenika mora biti jednak novom id-u", novi_id,
					k.getZaposlenik_id());
			// Kad zavrsimo uradimo commit da se spreme promjene
			t.commit();

			Zaposlenik novi = (Zaposlenik) session.get(Zaposlenik.class, k.getZaposlenik_id());
			assertEquals("Idevi moraju biti jednaki", k.getZaposlenik_id(), novi.getZaposlenik_id());
			assertEquals("Username moraju biti jednaki", k.getUsername(),
					novi.getUsername());
			assertEquals("Passwordi moraju biti jednaki", k.getPassword(),
					novi.getPassword());

			t = session.beginTransaction();
			session.delete(k);
			t.commit();

		} catch (Exception e) {

			fail("Greska" + e.getMessage());
		} finally {
			// VAZNO: ne zaboraviti zatvoriti konekciju u GUI-u
			if (session != null)
				session.close();
		}
	}
	
}
