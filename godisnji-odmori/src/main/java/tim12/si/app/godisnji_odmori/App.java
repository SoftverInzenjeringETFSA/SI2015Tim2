package tim12.si.app.godisnji_odmori;

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
        System.out.println( "Hello World! Merseda " );
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println( "Hello World! Merseda " );
        //dodajZaposlenika(session);
        
        
        session.close();
    }
    
    private static void dodajZaposlenika(Session session) {
    	Transaction t = session.beginTransaction();
    	Zaposlenik s = new Zaposlenik();
    	s.setIme("Meho");
    	s.setPrezime("Mehic");
    	Long id = (Long) session.save(s);
    	System.out.println("Dodan student sa IDom "+id);
    	t.commit();
    	}

}