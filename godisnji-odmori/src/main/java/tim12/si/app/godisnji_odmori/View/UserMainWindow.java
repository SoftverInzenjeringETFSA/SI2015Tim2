package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import tim12.si.app.godisnji_odmori.Singleton;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.Controller.KalendarController;
import tim12.si.app.godisnji_odmori.Controller.OdsustvoController;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.Model.Zaposlenik;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikAccountVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMainWindow {

	private JFrame frmSolutionsi;

	private JLabel lblIDInfo;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblAdresaStanovanja;
	private JLabel lblDatumRodjenja;
	private JDialog frame;
	private JLabel lblRadniDani;
	private JLabel lblDaniBolovanja;
	private JLabel lblNeplaniranoOdsustvo;
	private JLabel lblIskoristeno;
	private JLabel lblOstaloGodisnjeg;
	private JPasswordField txtStaraSifra;
	private JPasswordField txtNovaSifra;
	private JPasswordField txtNovaSifraPotvrda;
	private JCalendar calendar_1;
	private JCalendar calendar_2;
	private ArrayList<Date> events;
	private ZaposlenikBrDana zaposlenikUserPocetna;
	private JLabel lblEkonomski;
	private JLabel lblNewLabel;
	private JLabel lblHasoHasi;
	private JLabel label_5;
	private JCalendar calendar;
	private JDateChooser dateChooser_2;
	private JDateChooser dateChooser_3;
	private JTextArea textArea;
	private JCheckBox chckbxNalazSpecijaliste;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JTextArea textArea_1;
	private ZaposlenikBrDana zbd;
	private ZaposlenikVM zvm;
	private JLabel label_1;
	private JLabel lblpostaviEmail;
	private JLabel lblPostaviBrTel;
	private JLabel lblNisteUnijeliOd;
	private JLabel lblNisteUnijeliKrajnji;
	private JLabel lblNisteUnijeliPocetni;
	private JLabel lblNisteUnijeliKrajnji_1;
	private JLabel lblX;
	
	private static final Logger logger = Logger.getLogger(UserMainWindow.class);


	/**
	 * Launch the application.
	 */
	Session sess = null;

	public static void User() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainWindow window = new UserMainWindow();
					window.frmSolutionsi.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	public UserMainWindow() throws ZaposlenikNotFound {

		initialize();
		PrikaziInfo();
	}

	public void PrikaziInfo() throws ZaposlenikNotFound {
		try {
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();

			ZaposlenikController zc = new ZaposlenikController(sess);
			OdsustvoController oc = new OdsustvoController(sess);
			KalendarController kc = new KalendarController();

			zaposlenikUserPocetna = zc.DajZaposlenikViewModel(Singleton.getInstance().getUsername());

			lblEkonomski.setText(zaposlenikUserPocetna.getSektor());
			lblNewLabel.setText(zaposlenikUserPocetna.getRadniDani().toString());
			lblHasoHasi.setText(
					zaposlenikUserPocetna.getZaposlenikIme() + " " + zaposlenikUserPocetna.getZaposlenikPrezime());
			label_5.setText(zaposlenikUserPocetna.getPreostaloSlobodnih().toString());

			events = oc.dajSvaOdsustva(zaposlenikUserPocetna.getSektor());
			calendar.getDayChooser().setEnabled(false);
			kc.postaviZauzete(events, calendar);

			ZaposlenikVM zvm = zc.DajZaposlenikoveInformacije(Singleton.getInstance().getUsername());
			zvm = zc.DajZaposlenikoveInformacije(Singleton.getInstance().getUsername());

			lblIme.setText(zvm.getIme());
			lblPrezime.setText(zvm.getPrezime());
			lblAdresaStanovanja.setText(zvm.getAdresaStanovanja());
			lblDatumRodjenja.setText(zvm.getDatumRodjenja().toString());
			lblPostaviBrTel.setText(zvm.getTelefon());
			lblpostaviEmail.setText(zvm.getEmail());
			

			final Color c = calendar_1.getDayChooser().getDayPanel().getComponent(20).getBackground();
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();

			events = oc.dajSvaOdsustva(zc.dajNazivSektoraZaposlenikaBaza(Singleton.getInstance().getUsername()));

			kc.postaviZauzete(events, calendar_1);
			kc.postaviZauzete(events, calendar_2);
			calendar_2.getDayChooser().setEnabled(true);
			calendar_1.getDayChooser().setEnabled(true);

		} catch (Exception er) {
			
			logger.error(er);
			if (er.getMessage() != null)
				JOptionPane.showMessageDialog(frame, er.getMessage(), "Greška", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(frame,
						"Korisnik sa username: " + Singleton.getInstance().getUsername() + " ne postoji.", "Greška",
						JOptionPane.INFORMATION_MESSAGE);

		} finally {
			if (sess != null)
				sess.close();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ZaposlenikNotFound {

		frmSolutionsi = new JFrame();
		frmSolutionsi.setTitle("SolutionSI");
		frmSolutionsi.getContentPane().setLayout(null);
		frmSolutionsi.setBounds(10, 20, 665, 525);
		frmSolutionsi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 626, 442);
		frmSolutionsi.getContentPane().add(tabbedPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsi.setLocation(dim.width / 2 - frmSolutionsi.getSize().width / 2,
				dim.height / 2 - frmSolutionsi.getSize().height / 2);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Početna", null, panel, null);
		panel.setLayout(null);

		ImageIcon ii = new ImageIcon(getClass().getResource("/boss2.png"));
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 42, 128, 128);
		label.setIcon(ii);
		panel.add(label);

		lblHasoHasi = new JLabel("Haso Hasi\u0107");
		lblHasoHasi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHasoHasi.setBounds(35, 188, 191, 26);
		panel.add(lblHasoHasi);

		JLabel lblZaOvuGodinu = new JLabel("Za ovu godinu Vam je ostalo da iskoristite još");
		lblZaOvuGodinu.setForeground(new Color(128, 0, 0));
		lblZaOvuGodinu.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblZaOvuGodinu.setBounds(10, 377, 291, 26);
		panel.add(lblZaOvuGodinu);

		JLabel lblSektor_1 = new JLabel("Sektor:");
		lblSektor_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSektor_1.setBounds(10, 286, 68, 26);
		panel.add(lblSektor_1);

		lblEkonomski = new JLabel("Ekonomski");
		lblEkonomski.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEkonomski.setBounds(70, 292, 156, 14);
		panel.add(lblEkonomski);

		JLabel lblUkupnoRadnihDana = new JLabel("Ukupno radnih dana:");
		lblUkupnoRadnihDana.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUkupnoRadnihDana.setBounds(10, 332, 128, 14);
		panel.add(lblUkupnoRadnihDana);

		lblNewLabel = new JLabel("251");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(148, 332, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(10, 254, 68, 14);
		panel.add(lblStatus);

		JLabel lblZaposlenik = new JLabel("Zaposlenik");
		lblZaposlenik.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblZaposlenik.setBounds(70, 251, 124, 21);
		panel.add(lblZaposlenik);

		calendar = new JCalendar();
		calendar.setBounds(263, 115, 333, 227);
		panel.add(calendar);

		JComboBox jcb = (JComboBox) calendar.getMonthChooser().getComboBox();
		jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KalendarController kc = new KalendarController();
				kc.postaviZauzete(events, calendar);
			}
		});

		label_5 = new JLabel("20");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		label_5.setBounds(311, 379, 27, 26);
		panel.add(label_5);

		JLabel lblDanaOdGodinjeg = new JLabel("dana od godišnjeg odmora");
		lblDanaOdGodinjeg.setForeground(new Color(128, 0, 0));
		lblDanaOdGodinjeg.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblDanaOdGodinjeg.setBounds(335, 379, 164, 26);
		panel.add(lblDanaOdGodinjeg);

		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("Informacije", null, panel_9, null);
		panel_9.setLayout(null);

		// INFORMACIJE//

		// --osnovni podaci--
		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZaposlenikController zc = new ZaposlenikController(sess);
		ZaposlenikVM zvm = zc.DajZaposlenikoveInformacije(Singleton.getInstance().getUsername());

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(
				new TitledBorder(null, "Osnovni podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 11, 285, 159);
		panel_9.add(panel_10);
		panel_10.setLayout(null);

		JLabel label_20 = new JLabel("Id zaposlenika:");
		label_20.setBounds(10, 40, 120, 14);
		panel_10.add(label_20);

		JLabel label_23 = new JLabel("Ime:");
		label_23.setBounds(10, 64, 120, 14);
		panel_10.add(label_23);

		JLabel label_24 = new JLabel("Prezime:");
		label_24.setBounds(10, 88, 120, 14);
		panel_10.add(label_24);

		JLabel label_27 = new JLabel("Datum rođenja:");
		label_27.setBounds(10, 113, 120, 14);
		panel_10.add(label_27);

		JLabel lblIDInfo = new JLabel();
		lblIDInfo.setBounds(184, 40, 91, 14);
		panel_10.add(lblIDInfo);

		lblIme = new JLabel(zvm.getIme());
		lblIme.setBounds(184, 64, 91, 14);
		panel_10.add(lblIme);

		lblPrezime = new JLabel(zvm.prezime);
		lblPrezime.setBounds(184, 88, 91, 14);
		panel_10.add(lblPrezime);
		// String strDate =
		// DateFormat.getDateInstance().format(zvm.datumRodjenja);
		lblDatumRodjenja = new JLabel();
		lblDatumRodjenja.setBounds(184, 113, 91, 14);
		panel_10.add(lblDatumRodjenja);

		// --kraj osnovni podaci

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(
				new TitledBorder(null, "Kontakt podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(10, 192, 285, 134);
		panel_9.add(panel_11);
		panel_11.setLayout(null);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 27, 98, 14);
		panel_11.add(lblEmail);

		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(10, 52, 98, 14);
		panel_11.add(lblBrojTelefona);

		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setBounds(10, 77, 46, 14);
		panel_11.add(lblAdresa);

		lblAdresaStanovanja = new JLabel(zvm.getAdresaStanovanja());
		lblAdresaStanovanja.setBounds(127, 77, 148, 14);
		panel_11.add(lblAdresaStanovanja);

		lblpostaviEmail = new JLabel("");
		lblpostaviEmail.setBounds(127, 27, 148, 14);
		panel_11.add(lblpostaviEmail);

		lblPostaviBrTel = new JLabel("");
		lblPostaviBrTel.setBounds(127, 52, 148, 14);
		panel_11.add(lblPostaviBrTel);

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Promjena \u0161ifre",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12.setBounds(326, 192, 285, 134);
		panel_9.add(panel_12);
		panel_12.setLayout(null);

		JLabel lblStaraifra = new JLabel("* Stara šifra:");
		lblStaraifra.setBounds(10, 32, 154, 14);
		panel_12.add(lblStaraifra);

		JLabel lblnovaifra = new JLabel("* Nova šifra:");
		lblnovaifra.setBounds(10, 58, 154, 14);
		panel_12.add(lblnovaifra);

		JLabel lblPotvrditeNovu = new JLabel("* Potvrdite novu šifru:");
		lblPotvrditeNovu.setBounds(10, 86, 154, 14);
		panel_12.add(lblPotvrditeNovu);

		txtStaraSifra = new JPasswordField();
		txtStaraSifra.setBounds(156, 26, 118, 20);
		panel_12.add(txtStaraSifra);

		txtNovaSifra = new JPasswordField();
		txtNovaSifra.setBounds(156, 52, 118, 20);
		panel_12.add(txtNovaSifra);

		txtNovaSifraPotvrda = new JPasswordField();
		txtNovaSifraPotvrda.setBounds(156, 80, 118, 20);
		panel_12.add(txtNovaSifraPotvrda);

		// INORMACIJE O DANIMA ZAPOSLENIKA

		// zbd=
		// zc.DajZaposlenikViewModel(Singleton.getInstance().getUsername());

		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZaposlenikController zc1 = new ZaposlenikController(sess);
		zbd = zc1.DajZaposlenikViewModelZaZahtjev(Singleton.getInstance().getUsername());

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Informacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(326, 11, 285, 159);
		panel_9.add(panel_13);
		panel_13.setLayout(null);

		JLabel lblBrojRadnihDana = new JLabel("Broj radnih dana:");
		lblBrojRadnihDana.setBounds(10, 29, 102, 14);
		panel_13.add(lblBrojRadnihDana);

		lblRadniDani = new JLabel("");
		lblRadniDani.setBounds(219, 29, 46, 14);
		panel_13.add(lblRadniDani);

		JLabel lblIskoritenoDanaGodinjeg = new JLabel("Iskorišteno dana godišnjeg odmora:");
		lblIskoritenoDanaGodinjeg.setBounds(10, 104, 197, 14);
		panel_13.add(lblIskoritenoDanaGodinjeg);

		JLabel lblOstaloDanaGodinjeg = new JLabel("Ostalo dana godišnjeg odmora:");
		lblOstaloDanaGodinjeg.setBounds(10, 129, 231, 14);
		panel_13.add(lblOstaloDanaGodinjeg);

		lblIskoristeno = new JLabel("");
		lblIskoristeno.setBounds(219, 104, 46, 14);
		panel_13.add(lblIskoristeno);

		lblOstaloGodisnjeg = new JLabel("");
		lblOstaloGodisnjeg.setBounds(219, 129, 46, 14);
		panel_13.add(lblOstaloGodisnjeg);

		JLabel lblBrojDanaBolovanja = new JLabel("Broj dana bolovanja:");
		lblBrojDanaBolovanja.setBounds(10, 54, 129, 14);
		panel_13.add(lblBrojDanaBolovanja);

		JLabel lblBrojDanaNeplaniranog = new JLabel("Broj dana neplaniranog odsustva:");
		lblBrojDanaNeplaniranog.setBounds(10, 79, 189, 14);
		panel_13.add(lblBrojDanaNeplaniranog);

		lblDaniBolovanja = new JLabel("");
		lblDaniBolovanja.setBounds(219, 54, 46, 14);
		panel_13.add(lblDaniBolovanja);

		lblNeplaniranoOdsustvo = new JLabel("");
		lblNeplaniranoOdsustvo.setBounds(219, 79, 46, 14);
		panel_13.add(lblNeplaniranoOdsustvo);

		//Postavljenje DANAAA
		lblRadniDani.setText(zbd.getRadniDani().toString());
		lblOstaloGodisnjeg.setText(zbd.getPreostaloSlobodnih().toString());
		lblDaniBolovanja.setText(zbd.getDaniBolovanja().toString());
		lblNeplaniranoOdsustvo.setText(zbd.getDaniNeplaniranog().toString());
		lblIskoristeno.setText(zbd.getIskoristeniGodisnji().toString());
		
		// KRAJ INFO DANI

		JButton btnSpasiPromjene = new JButton("Spasi promjene");
		btnSpasiPromjene.setBounds(305, 380, 142, 23);
		panel_9.add(btnSpasiPromjene);
		btnSpasiPromjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
					ZaposlenikController zc = new ZaposlenikController(sess);
					label_1.setText("");
					if (!validirajUnosNoveSifre())
						return;

					if (zc.promjeniSifru(Singleton.getInstance().getUsername(), txtNovaSifra.getText())) {

						JOptionPane.showMessageDialog(null, "Uspješno ste promijenili šifru", "Obavjestenje",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} catch (Exception e) {
					logger.error(e);
				}

			}
		});

		JButton btnOdbaciPromjene = new JButton("Odbaci promjene");
		btnOdbaciPromjene.setBounds(469, 380, 142, 23);
		panel_9.add(btnOdbaciPromjene);

		btnOdbaciPromjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					initialize();
				} catch (ZaposlenikNotFound e) {
					logger.error(e);
				}

			}
		});

		label_1 = new JLabel("");
		label_1.setForeground(Color.RED);
		label_1.setBounds(326, 333, 285, 36);
		panel_9.add(label_1);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Godišnji odmor", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblOstaloVamJe = new JLabel("Ostalo Vam je još ");
		lblOstaloVamJe.setBounds(104, 295, 108, 14);
		panel_1.add(lblOstaloVamJe);

		lblX = new JLabel("20");
		lblX.setForeground(Color.RED);
		lblX.setBounds(222, 295, 22, 14);
		panel_1.add(lblX);
		lblX.setText(zbd.getPreostaloSlobodnih().toString());

		JLabel lblDanaGodinjegOdmora = new JLabel("dana godišnjeg odmora za ovu godinu");
		lblDanaGodinjegOdmora.setBounds(254, 295, 278, 14);
		panel_1.add(lblDanaGodinjegOdmora);

		JButton btnRezervisiGodisnjiOdmor = new JButton("Rezerviši godišnji odmor");
		btnRezervisiGodisnjiOdmor.setBounds(385, 367, 195, 23);
		panel_1.add(btnRezervisiGodisnjiOdmor);
		btnRezervisiGodisnjiOdmor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spasiZahtjevGodisnji();
			}
		});

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(
				new TitledBorder(null, "Vremenski interval", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(43, 21, 537, 244);
		panel_1.add(panel_8);
		panel_8.setLayout(null);

		JLabel label_15 = new JLabel("Od:");
		label_15.setBounds(20, 39, 46, 14);
		panel_8.add(label_15);

		JLabel label_21 = new JLabel("Do:");
		label_21.setBounds(282, 39, 46, 14);
		panel_8.add(label_21);

		calendar_1 = new JCalendar();
		calendar_1.setBounds(30, 64, 198, 153);
		panel_8.add(calendar_1);
		calendar_1.getMonthChooser().getSpinner().setEnabled(false);
		calendar_1.getMonthChooser().getComboBox().setEnabled(true);

		calendar_2 = new JCalendar();
		calendar_2.setBounds(315, 64, 198, 153);
		panel_8.add(calendar_2);
		calendar_2.getMonthChooser().getSpinner().setEnabled(false);
		calendar_2.getMonthChooser().getComboBox().setEnabled(true);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Bolovanje", null, panel_2, null);
		panel_2.setLayout(null);

		JButton button = new JButton("Kreiraj zahtjev");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				spasiZahtjevBolovanje();
			}

		});

		button.setBounds(439, 380, 132, 23);
		panel_2.add(button);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(
				new TitledBorder(null, "Vremenski interval", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(79, 11, 492, 130);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblOd_1 = new JLabel("Od:");
		lblOd_1.setBounds(32, 31, 128, 14);
		panel_6.add(lblOd_1);

		JLabel lblDo_1 = new JLabel("Do:");
		lblDo_1.setBounds(290, 31, 114, 14);
		panel_6.add(lblDo_1);

		dateChooser_2 = new JDateChooser();
		dateChooser_2.setDateFormatString("yyyy-MM-dd");
		dateChooser_2.setBounds(52, 56, 114, 20);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser_2.getDateEditor();
		editor.setEditable(false);
		panel_6.add(dateChooser_2);

		dateChooser_3 = new JDateChooser();
		dateChooser_3.setDateFormatString("yyyy-MM-dd");
		dateChooser_3.setBounds(300, 56, 114, 20);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) dateChooser_3.getDateEditor();
		editor1.setEditable(false);
		panel_6.add(dateChooser_3);

		lblNisteUnijeliOd = new JLabel("Niste unijeli pocetni datum");
		lblNisteUnijeliOd.setForeground(Color.RED);
		lblNisteUnijeliOd.setBounds(52, 87, 185, 14);
		lblNisteUnijeliOd.setVisible(false);
		panel_6.add(lblNisteUnijeliOd);

		lblNisteUnijeliKrajnji = new JLabel("Niste unijeli krajnji datum");
		lblNisteUnijeliKrajnji.setForeground(Color.RED);
		lblNisteUnijeliKrajnji.setBounds(298, 87, 184, 14);
		lblNisteUnijeliKrajnji.setVisible(false);
		panel_6.add(lblNisteUnijeliKrajnji);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Razlog bolovanja",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(79, 158, 492, 171);
		panel_2.add(panel_7);
		panel_7.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 20, 472, 140);
		panel_7.add(scrollPane_1);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);

		chckbxNalazSpecijaliste = new JCheckBox("Nalaz specijaliste");
		chckbxNalazSpecijaliste.setBounds(79, 354, 132, 23);
		panel_2.add(chckbxNalazSpecijaliste);

		JPanel panel_3 = new JPanel();
		
		tabbedPane.addTab("Neplanirano odsustvo", null, panel_3, null);
		panel_3.setLayout(null);

		JButton btnKreirajZahtjev = new JButton("Kreiraj zahtjev");
		btnKreirajZahtjev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				spasiZahtjevNeplanirano();
			}
		});
		btnKreirajZahtjev.setBounds(432, 380, 140, 23);
		panel_3.add(btnKreirajZahtjev);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(
				new TitledBorder(null, "Vremenski interval", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(80, 11, 492, 130);
		panel_3.add(panel_4);

		JLabel label_6 = new JLabel("Od:");
		label_6.setBounds(32, 31, 128, 14);
		panel_4.add(label_6);

		JLabel label_7 = new JLabel("Do:");
		label_7.setBounds(290, 31, 114, 14);
		panel_4.add(label_7);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(52, 56, 114, 20);
		JTextFieldDateEditor editor4 = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor4.setEditable(false);
		panel_4.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(311, 56, 114, 20);
		JTextFieldDateEditor editor2 = (JTextFieldDateEditor) dateChooser_1.getDateEditor();
		editor2.setEditable(false);
		panel_4.add(dateChooser_1);
		
		lblNisteUnijeliPocetni = new JLabel("Niste unijeli pocetni datum");
		lblNisteUnijeliPocetni.setForeground(Color.RED);
		lblNisteUnijeliPocetni.setBounds(52, 93, 187, 14);
		lblNisteUnijeliPocetni.setVisible(false);
		panel_4.add(lblNisteUnijeliPocetni);
		
		lblNisteUnijeliKrajnji_1 = new JLabel("Niste unijeli krajnji datum");
		lblNisteUnijeliKrajnji_1.setForeground(Color.RED);
		lblNisteUnijeliKrajnji_1.setBounds(310, 93, 172, 14);
		lblNisteUnijeliKrajnji_1.setVisible(false);
		panel_4.add(lblNisteUnijeliKrajnji_1);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Razlog odsustva",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(80, 158, 492, 171);
		panel_3.add(panel_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 472, 140);
		panel_5.add(scrollPane);

		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane.setViewportView(textArea_1);

		JMenuBar menuBar = new JMenuBar();
		frmSolutionsi.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);

		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);

		JMenuItem mntmLogOut = new JMenuItem("Odjavi se");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSolutionsi.dispose();
				UI ui = new UI();
				ui.UIShow();
			}
		});
		mnOdjava.add(mntmLogOut);
	}

	public void ProslijediInfo(ZaposlenikVM zvm) {
		// TODO Auto-generated method stub
		lblpostaviEmail.setText("Merseda");
	}

	private void spasiZahtjevBolovanje() {
		if (!validirajBolovanje())
			return;
		Date Od = dateChooser_2.getDate();
		Date Do = dateChooser_3.getDate();
		String opis = textArea.getText();
		Boolean nalaz = chckbxNalazSpecijaliste.isSelected();

		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZahtjevController bolovanje = new ZahtjevController(sess);
		ZahtjevVM zvm = new ZahtjevVM(Od, Do, (long) 2, opis, nalaz);
		try {
			
			if(Od.after(Do) ){
				
				JOptionPane.showMessageDialog(null, "Datum Od pocinje nakon datuma zavrsetka.", "Obavještenje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Long bolovanje_id = bolovanje.kreirajZahtjev(zvm);

			JOptionPane.showMessageDialog(null, "Uspješno poslan zahtjev za bolovanje", "Obavještenje",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			logger.error(e);
			
		} finally {
			sess.close();
			ocistiBolovanje();
		}

	}
	
	private void ocistiBolovanje()
	{dateChooser_2.setDate(null);
	dateChooser_3.setDate(null);
	textArea.setText(null);
	chckbxNalazSpecijaliste.setSelected(false);
		
	}
	
	private void ocistiNeplanirano()
	{
		dateChooser.setDate(null);
		dateChooser_1.setDate(null);
		textArea_1.setText(null);
		chckbxNalazSpecijaliste.setSelected(false);
	}

	private Boolean validirajBolovanje() {
		Boolean validacija = true;
		lblNisteUnijeliOd.setVisible(false);
		lblNisteUnijeliKrajnji.setVisible(false);
		if (dateChooser_2.getDate() == null) {
			validacija = false;
			lblNisteUnijeliOd.setVisible(true);
		}
		if (dateChooser_3.getDate() == null) {
			validacija = false;
			lblNisteUnijeliKrajnji.setVisible(true);
		}
		return validacija;

	}

	private void spasiZahtjevNeplanirano() {
		if (!validirajNeplanirano())
			return;
		Date Od = dateChooser.getDate();
		Date Do = dateChooser_1.getDate();
		String opis = textArea_1.getText();
		Boolean nalaz = false;

		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZahtjevController neplanirano = new ZahtjevController(sess);
		ZahtjevVM zvm = new ZahtjevVM(Od, Do, (long) 3, opis, nalaz);
		try {
			if(Od.after(Do) ){
				
				JOptionPane.showMessageDialog(null, "Datum Od pocinje nakon datuma zavrsetka.", "Obavještenje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Long neplanirano_id = neplanirano.kreirajZahtjev(zvm);
			JOptionPane.showMessageDialog(null, "Uspješno poslan zahtjev za neplanirano odsustvo", "Obavještenje",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			logger.error(e);
		} finally {
			sess.close();
			ocistiNeplanirano();
		}
	}
	private Boolean validirajNeplanirano() {
		Boolean validacija = true;
		lblNisteUnijeliPocetni.setVisible(false);
		lblNisteUnijeliKrajnji_1.setVisible(false);
		if (dateChooser.getDate() == null) {
			validacija = false;
			lblNisteUnijeliPocetni.setVisible(true);
		}
		if (dateChooser_1.getDate() == null) {
			validacija = false;
			lblNisteUnijeliKrajnji_1.setVisible(true);
		}
		return validacija;

	}

	private void spasiZahtjevGodisnji() {

		Date Od = calendar_1.getDate();
		Date Do = calendar_2.getDate();
		String opis = "Godisnji";
		Boolean nalaz = false;
		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZahtjevController godisnji = new ZahtjevController(sess);
		ZahtjevVM zvm = new ZahtjevVM(Od, Do, (long) 1, opis, nalaz);
		try {
			if(!godisnji.provjeriMozelToliko(zvm)){
				
				JOptionPane.showMessageDialog(null, "Izabrali ste vie dana nego sto Vam je dozvoljeno.", "Obavještenje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(Od.after(Do) ){
				
				JOptionPane.showMessageDialog(null, "Datum Od pocinje nakon datuma zavrsetka.", "Obavještenje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				
			
			Long godisnji_id = godisnji.kreirajZahtjev(zvm);
			JOptionPane.showMessageDialog(null, "Uspješno poslan zahtjev za godisnji odmor", "Obavještenje",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			
			logger.error(e);
			
		} finally {
			sess.close();

		}
	}

	public Boolean validirajUnosNoveSifre() {

		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZaposlenikController zc = new ZaposlenikController(sess);

		if (txtStaraSifra.getText().equals("") || txtNovaSifra.getText().equals("")
				|| txtNovaSifraPotvrda.getText().equals("")) {

			label_1.setText("Sva polja moraju biti popunjena.");
			return false;

		}

		if (!zc.uporediSifruBaza(Singleton.getInstance().getUsername(), txtStaraSifra.getText())) {

			label_1.setText("Pogresna trenutna sifra.");
			return false;

		}

		if (!txtNovaSifra.getText().equals(txtNovaSifraPotvrda.getText())) {

			label_1.setText("Nove sifre se ne podudaraju.");
			return false;
		}

		return true;

	}
}
