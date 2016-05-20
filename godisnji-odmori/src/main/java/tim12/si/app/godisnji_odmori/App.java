package tim12.si.app.godisnji_odmori;

import java.util.Date;

/**
 * Hello world!
 *
 */

import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;
import tim12.si.app.godisnji_odmori.Model.*;


public class App 
{
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        dodajSetor(session);
        dodajTipOdsustva(session, "Godisnji", "Idemo na more",1);
        dodajTipOdsustva(session, "Bolovanje", "Umiremo",2);
        dodajTipOdsustva(session, "Neplanirano", "Nista ne planiram",3);
        dodajZaposlenika(session);   
        session.close();
       
    }
    
    private static void dodajSetor(Session session)
    {
    	Transaction t = session.beginTransaction();
    	Sektor s = new Sektor();
    	s.setSektor_id(1);
    	s.setBroj_uposlenih(0);
    	Date danas = new Date();
    	s.setGodina_osnivanja(danas.getYear());
    	s.setMax_broj_odsutnih(5);
    	s.setNaziv("Administracija");
    	s.setOpis("Inicijalni administratorski sektor");
    	Long id = (Long) session.save(s);
    	t.commit();
    	
    }
    private static void dodajTipOdsustva(Session session, String naziv, String opis,long idTo)
    {
    	Transaction t = session.beginTransaction();
    	TipOdsustva to = new TipOdsustva();
    	to.setId_odsustva(idTo);
    	to.setNaziv(naziv);
    	to.setOpis(opis);
    	Long id = (Long) session.save(to);
    	t.commit();
    }
    private static void dodajZaposlenika(Session session) {
    	Transaction t = session.beginTransaction();
    	Zaposlenik s = new Zaposlenik();
    	s.setZaposlenik_id(1);
    	s.setIme("Administrator");
    	s.setPrezime("");
    	s.setUsername("admin");
    	s.setPassword("admin");
    	s.setSektor_id(1);
    	s.setAdresa_stanovanja("");
    	s.setBroj_dana_godisnjeg(20);
    	s.setEmail("email@email.com");
    	s.setTelefon("+123456");
    	Date datum = new Date(1993, 2, 2);
    	s.setDatum_rodjenja(datum);
    	s.setPrivilegija(true);
    	Long id = (Long) session.save(s);
    	t.commit();
    	}

}