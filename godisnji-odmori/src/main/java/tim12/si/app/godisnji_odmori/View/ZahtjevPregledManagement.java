package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;

import tim12.si.app.godisnji_odmori.Controller.KalendarController;
import tim12.si.app.godisnji_odmori.Controller.OdsustvoController;
import tim12.si.app.godisnji_odmori.Controller.TipOdsustvaController;
//import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.Model.TipOdsustva;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;

import javax.swing.JButton;

public class ZahtjevPregledManagement {

	private JFrame frmSolutionsiZahtjev;
	private JLabel lblHamo;
	private JLabel lblPipa;
	private JLabel lblNewLabel_1; 
	private JLabel label_1;
	private JLabel label_5;
	private JLabel label_8; 
	private JLabel label_9;
	private JLabel label_4;
	private JLabel label_10;
	private JLabel label_11;
	private JCalendar calendar;
	private JLabel lblGodisnjiOdmor;
	private JTextPane txtpnUmoranOdDosade;
	private JDialog frame;
	private JComboBox jcb;
	private Long id_zahtjeva;
	private ZaposlenikBrDana zbr;
	private ZahtjevVM zvm;
	private ArrayList<Date> events;
	private JLabel posjeduje_labela;
	
	private static final Logger logger = Logger.getLogger(ZahtjevPregledManagement.class);


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZahtjevPregledManagement window = new ZahtjevPregledManagement();
					window.frmSolutionsiZahtjev.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ZahtjevPregledManagement(Long id, String sektor, String username) {
		//ManagementMainWindow.Prekini(this);
		initialize();
		Session sess = null;
		this.frmSolutionsiZahtjev.setVisible(true);
		id_zahtjeva = id;
		
		try {
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZaposlenikController zc = new ZaposlenikController(sess);
			zbr = zc.DajZaposlenikViewModelZaZahtjev(username);
			lblHamo.setText(zbr.getZaposlenikIme());
			lblPipa.setText(zbr.getZaposlenikPrezime());
			lblNewLabel_1.setText(zbr.getSektor());
			label_1.setText(zbr.getRadniDani().toString());
			label_5.setText(zbr.getPreostaloSlobodnih().toString());
			label_8.setText(zbr.getDaniBolovanja().toString());
			label_9.setText(zbr.getDaniNeplaniranog().toString());
			label_4.setText(zbr.getIskoristeniGodisnji().toString());
			
			ZahtjevController zcc = new ZahtjevController(sess);
			zvm = zcc.dajZahtjev(id);
			txtpnUmoranOdDosade.setText(zvm.getOpis());
			DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
			String strDate = dateFormat.format(zvm.getPocetakOdsustva());
			String strDate2 = dateFormat.format(zvm.getZavrsetakOdsustva());
			label_10.setText(strDate);
			label_11.setText(strDate2);
			TipOdsustvaController toc = new TipOdsustvaController(sess);
			String nazivTipa = toc.dajImeTipaOdsustva(zvm.getTipOdsustva());
			lblGodisnjiOdmor.setText(nazivTipa); 
			
			posjeduje_labela.setText(zvm.getNalaz()?"Da":"Ne");
			//za kalendar
			OdsustvoController oc = new OdsustvoController(sess);
			events = oc.dajSvaOdsustva(sektor);
			KalendarController kc = new KalendarController();
            kc.postaviZauzete(events, calendar);
			
			
			
			jcb = (JComboBox) calendar.getMonthChooser().getComboBox(); 
			jcb.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	KalendarController kc = new KalendarController();
		            kc.postaviZauzete(events, calendar);
			    }
			});
			
			
			
			
			JSpinner js = (JSpinner) calendar.getYearChooser().getSpinner();
			
			
			js.addChangeListener(new ChangeListener() {

		        public void stateChanged(ChangeEvent e) {
		        	KalendarController kc = new KalendarController();
		            kc.postaviZauzete(events, calendar);
		        }
		    });
			
			
		}
		catch (Exception er) {

			logger.error(er);
			
			if (er.getMessage() != null )
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(frame, "Korisnik sa username: " + username + " ne postoji ili zahtjev sa id-om" + id + " ne postoji",
					"Greška", JOptionPane.INFORMATION_MESSAGE);

		} finally {
			if (sess != null)
				sess.close();
		}
		
	}
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiZahtjev = new JFrame();
		frmSolutionsiZahtjev.setResizable(false);
		frmSolutionsiZahtjev.setTitle("SolutionSI - Zahtjev detalji");
		frmSolutionsiZahtjev.setBounds(100, 100, 840, 407);
		frmSolutionsiZahtjev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsiZahtjev.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsiZahtjev.setLocation(dim.width/2-frmSolutionsiZahtjev.getSize().width/2, dim.height/2-frmSolutionsiZahtjev.getSize().height/2);
		
		frmSolutionsiZahtjev.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	ManagementMainWindow mw =new ManagementMainWindow();
		    	mw.Management();
		    	frmSolutionsiZahtjev.dispose();
		        }
		    }
		);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 30, 310, 227);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacije o zaposleniku", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmSolutionsiZahtjev.getContentPane().add(panel);
		
		JLabel lblOdraeniRadniDani = new JLabel("Odrađeni radni dani:");
		lblOdraeniRadniDani.setBounds(10, 102, 129, 14);
		panel.add(lblOdraeniRadniDani);
		
		label_1 = new JLabel("125");
		label_1.setBounds(219, 102, 79, 14);
		panel.add(label_1);
		
		JLabel lblBrojDanaNa = new JLabel("Broj dana na godišnjem odmoru:");
		lblBrojDanaNa.setBounds(10, 177, 198, 14);
		panel.add(lblBrojDanaNa);
		
		JLabel label_3 = new JLabel("Ostalo dana godišnjeg odmora:");
		label_3.setBounds(10, 202, 189, 14);
		panel.add(label_3);
		
		label_4 = new JLabel("10");
		label_4.setBounds(219, 177, 79, 14);
		panel.add(label_4);
		
		label_5 = new JLabel("20");
		label_5.setBounds(219, 202, 79, 14);
		panel.add(label_5);
		
		JLabel lblBrojDaniNa = new JLabel("Broj dana na bolovanju:");
		lblBrojDaniNa.setBounds(10, 127, 153, 14);
		panel.add(lblBrojDaniNa);
		
		JLabel label_7 = new JLabel("Broj dana neplaniranog odsustva:");
		label_7.setBounds(10, 152, 198, 14);
		panel.add(label_7);
		
		JLabel lblNewLabel = new JLabel("*ako promijenite godinu, ponovno selektujte");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(581, 294, 233, 17);
		frmSolutionsiZahtjev.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("mjesec kako bi se učitali podaci");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(580, 307, 234, 16);
		frmSolutionsiZahtjev.getContentPane().add(lblNewLabel_2);
		
	    label_8 = new JLabel("0");
		label_8.setBounds(219, 127, 79, 14);
		panel.add(label_8);
		
		label_9 = new JLabel("0");
		label_9.setBounds(219, 152, 79, 14);
		panel.add(label_9);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 27, 46, 14);
		panel.add(lblIme);
		
		lblHamo = new JLabel("Hamo");
		lblHamo.setBounds(219, 27, 79, 14);
		panel.add(lblHamo);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(10, 52, 70, 14);
		panel.add(lblPrezime);
		
		lblPipa = new JLabel("Pipa");
		lblPipa.setBounds(219, 52, 79, 14);
		panel.add(lblPipa);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 77, 46, 14);
		panel.add(lblSektor);
		
		lblNewLabel_1 = new JLabel("IT");
		lblNewLabel_1.setBounds(219, 77, 79, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Zahtjev informacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(332, 30, 237, 227);
		frmSolutionsiZahtjev.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(10, 22, 46, 14);
		panel_1.add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(10, 47, 46, 14);
		panel_1.add(lblDo);
		
		JLabel lblTip = new JLabel("Tip:");
		lblTip.setBounds(10, 72, 46, 14);
		panel_1.add(lblTip);
		
		JLabel lblRazlog = new JLabel("Razlog:");
		lblRazlog.setBounds(10, 97, 46, 14);
		panel_1.add(lblRazlog);
		
		txtpnUmoranOdDosade = new JTextPane();
		txtpnUmoranOdDosade.setEditable(false);
		txtpnUmoranOdDosade.setBounds(71, 101, 154, 75);
		panel_1.add(txtpnUmoranOdDosade);
		txtpnUmoranOdDosade.setText("Umoran od dosade");
		
		label_10 = new JLabel("20-4-2016");
		label_10.setBounds(68, 22, 157, 14);
		panel_1.add(label_10);
		
		label_11 = new JLabel("30-4-2016");
		label_11.setBounds(68, 47, 157, 14);
		panel_1.add(label_11);
		
		lblGodisnjiOdmor = new JLabel("Godisnji odmor");
		lblGodisnjiOdmor.setBounds(71, 71, 154, 14);
		panel_1.add(lblGodisnjiOdmor);
		
		JLabel lblPosjedujeDokumente = new JLabel("Posjeduje dokumente: ");
		lblPosjedujeDokumente.setBounds(10, 202, 132, 14);
		panel_1.add(lblPosjedujeDokumente);
		
		 posjeduje_labela = new JLabel("");
		posjeduje_labela.setBounds(162, 202, 46, 14);
		panel_1.add(posjeduje_labela);
		
		calendar = new JCalendar();
		calendar.setBounds(581, 33, 233, 261);
		frmSolutionsiZahtjev.getContentPane().add(calendar);
		calendar.getMonthChooser().getSpinner().setEnabled(false);
		calendar.getMonthChooser().getComboBox().setEnabled(true);
		calendar.getDayChooser().setEnabled(false);

		
		
		
	
		JButton btnOdobri = new JButton("Odobri");
		btnOdobri.setBounds(589, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdobri);
		btnOdobri.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				odobriZahtjev();
			}
		});
		
		JButton btnOdbij = new JButton("Odbij");
		btnOdbij.setBounds(701, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdbij);
		btnOdbij.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				odbijZahtjev();
			}
		});
	}

	private void odobriZahtjev() {
		Session sess = null;
		Object[] options = { "Da", "Ne" };
		int n = JOptionPane.showOptionDialog(null, "Da li ste sigurni da zelite odobriti zahtjev?", "Upozorenje",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == JOptionPane.YES_OPTION) {
			try {
				sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
				ZahtjevController zc = new ZahtjevController(sess);
				int i = zc.odobriZahtjev(id_zahtjeva, zbr, zvm);
				if (i == 0)
					JOptionPane.showMessageDialog(frame, "Ne postoji zahjtev sa datim id-om!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				else {
					JOptionPane.showMessageDialog(frame, "Zahtjev uspješno odobren!", "Odobravanje zahtjeva!",
							JOptionPane.INFORMATION_MESSAGE);
					frmSolutionsiZahtjev.dispose();
					ManagementMainWindow mw = new ManagementMainWindow();
					mw.Management();

				}

			} catch (Exception e) {
				
				logger.error(e);
				JOptionPane.showMessageDialog(frame, e.getMessage(), "Greška", JOptionPane.INFORMATION_MESSAGE);

			} finally {
				if (sess != null)
					sess.close();
			}
		}
	}

	private void odbijZahtjev() {
		Session sess = null;
		Object[] options = { "Da", "Ne" };
		int n = JOptionPane.showOptionDialog(null, "Da li ste sigurni da zelite odbiti zahtjev?", "Upozorenje",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (n == JOptionPane.YES_OPTION) {
			try {
				sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
				ZahtjevController zc = new ZahtjevController(sess);
				int i = zc.odbijZahtjev(id_zahtjeva, zbr, zvm);
				if (i == 0)
					JOptionPane.showMessageDialog(frame, "Ne postoji zahjtev sa datim id-om!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				else {
					JOptionPane.showMessageDialog(frame, "Zahtjev uspješno odbijen!", "Odbijanje zahtjeva!",
							JOptionPane.INFORMATION_MESSAGE);

					frmSolutionsiZahtjev.dispose();
					ManagementMainWindow mw = new ManagementMainWindow();
					mw.Management();
				}

			} catch (Exception e) {
				
				logger.error(e);
				JOptionPane.showMessageDialog(frame, e.getMessage(), "Greška", JOptionPane.INFORMATION_MESSAGE);

			} finally {
				if (sess != null)
					sess.close();
			}
		}

	}
}
