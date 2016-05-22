package tim12.si.app.godisnji_odmori;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {

			java.util.Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/db.properties"));

			Configuration configuration = new Configuration();
			 	File f = new File("src/main/resources/hibernate.cfg.xml");

			configuration.configure(f).addProperties(properties);
			

			//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

			SessionFactory sessionFactory = configuration.buildSessionFactory();
			// Create the SessionFactory from hibernate.cfg.xml
			//File f = new File("src/main/java/hibernate.cfg.xml");
			//return new Configuration().configure(f).buildSessionFactory();
			return sessionFactory;
		} catch (Exception ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}