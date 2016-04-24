package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class UserMainWindow {

	private JFrame frmSolutionsi;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtHhasicgmailcom;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainWindow window = new UserMainWindow();
					window.frmSolutionsi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsi = new JFrame();
		frmSolutionsi.setTitle("SolutionSI");
		frmSolutionsi.getContentPane().setLayout(null);
		frmSolutionsi.setBounds(10, 20, 665,525);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 626, 442);
		frmSolutionsi.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Početna", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\AyLLa\\workspace\\SolutionSI\\Slike\\Person.gif"));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBounds(10, 42, 128, 128);
		panel.add(label);
		
		JLabel lblHasoHasi = new JLabel("Haso Hasi\u0107");
		lblHasoHasi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHasoHasi.setBounds(35, 188, 89, 26);
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
		
		JLabel lblEkonomski = new JLabel("Ekonomski");
		lblEkonomski.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEkonomski.setBounds(70, 292, 68, 14);
		panel.add(lblEkonomski);
		
		JLabel lblUkupnoRadnihDana = new JLabel("Ukupno radnih dana:");
		lblUkupnoRadnihDana.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUkupnoRadnihDana.setBounds(10, 332, 128, 14);
		panel.add(lblUkupnoRadnihDana);
		
		JLabel lblNewLabel = new JLabel("251");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(148, 332, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(10, 254, 68, 14);
		panel.add(lblStatus);
		
		JLabel lblZaposlenik = new JLabel("Zaposlenik");
		lblZaposlenik.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblZaposlenik.setBounds(70, 251, 89, 21);
		panel.add(lblZaposlenik);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(263, 115, 333, 227);
		panel.add(calendar);
		
		JLabel label_5 = new JLabel("20");
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
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Osnovni podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JLabel label_19 = new JLabel("12352");
		label_19.setBounds(184, 40, 91, 14);
		panel_10.add(label_19);
		
		JLabel lblHaso = new JLabel("Haso");
		lblHaso.setBounds(184, 64, 91, 14);
		panel_10.add(lblHaso);
		
		JLabel lblHasi = new JLabel("Hasić");
		lblHasi.setBounds(184, 88, 91, 14);
		panel_10.add(lblHasi);
		
		JLabel label_30 = new JLabel("15.02.1978.");
		label_30.setBounds(184, 113, 91, 14);
		panel_10.add(label_30);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Kontakt podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(10, 192, 285, 134);
		panel_9.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 27, 98, 14);
		panel_11.add(lblEmail);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(10, 52, 98, 14);
		panel_11.add(lblBrojTelefona);
		
		txtHhasicgmailcom = new JTextField();
		txtHhasicgmailcom.setText("hhasic1@gmail.com");
		txtHhasicgmailcom.setBounds(127, 21, 148, 20);
		panel_11.add(txtHhasicgmailcom);
		txtHhasicgmailcom.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("+387 62 256 364");
		textField_3.setBounds(127, 46, 148, 20);
		panel_11.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setBounds(10, 77, 46, 14);
		panel_11.add(lblAdresa);
		
		JLabel lblIsmetaMujezinovia = new JLabel("Ismeta Mujezinovića 23/5");
		lblIsmetaMujezinovia.setBounds(127, 77, 148, 14);
		panel_11.add(lblIsmetaMujezinovia);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Promjena \u0161ifre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 26, 118, 20);
		panel_12.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(156, 52, 118, 20);
		panel_12.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(156, 80, 118, 20);
		panel_12.add(passwordField_2);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Informacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(326, 11, 285, 159);
		panel_9.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblBrojRadnihDana = new JLabel("Broj radnih dana:");
		lblBrojRadnihDana.setBounds(10, 29, 102, 14);
		panel_13.add(lblBrojRadnihDana);
		
		JLabel label_1 = new JLabel("125");
		label_1.setBounds(219, 29, 46, 14);
		panel_13.add(label_1);
		
		JLabel lblIskoritenoDanaGodinjeg = new JLabel("Iskorišteno dana godišnjeg odmora:");
		lblIskoritenoDanaGodinjeg.setBounds(10, 104, 231, 14);
		panel_13.add(lblIskoritenoDanaGodinjeg);
		
		JLabel lblOstaloDanaGodinjeg = new JLabel("Ostalo dana godišnjeg odmora:");
		lblOstaloDanaGodinjeg.setBounds(10, 129, 231, 14);
		panel_13.add(lblOstaloDanaGodinjeg);
		
		JLabel label_2 = new JLabel("10");
		label_2.setBounds(219, 104, 46, 14);
		panel_13.add(label_2);
		
		JLabel label_3 = new JLabel("20");
		label_3.setBounds(219, 129, 46, 14);
		panel_13.add(label_3);
		
		JLabel lblBrojDanaBolovanja = new JLabel("Broj dana bolovanja:");
		lblBrojDanaBolovanja.setBounds(10, 54, 129, 14);
		panel_13.add(lblBrojDanaBolovanja);
		
		JLabel lblBrojDanaNeplaniranog = new JLabel("Broj dana neplaniranog odsustva:");
		lblBrojDanaNeplaniranog.setBounds(10, 79, 189, 14);
		panel_13.add(lblBrojDanaNeplaniranog);
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setBounds(219, 54, 46, 14);
		panel_13.add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("0");
		label_4.setBounds(219, 79, 46, 14);
		panel_13.add(label_4);
		
		JButton btnSpasiPromjene = new JButton("Spasi promjene");
		btnSpasiPromjene.setBounds(317, 380, 142, 23);
		panel_9.add(btnSpasiPromjene);
		
		JButton btnOdbaciPromjene = new JButton("Odbaci promjene");
		btnOdbaciPromjene.setBounds(469, 380, 142, 23);
		panel_9.add(btnOdbaciPromjene);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Godišnji odmor", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblOstaloVamJe = new JLabel("Ostalo Vam je još ");
		lblOstaloVamJe.setBounds(104, 295, 108, 14);
		panel_1.add(lblOstaloVamJe);
		
		JLabel lblX = new JLabel("20");
		lblX.setForeground(Color.RED);
		lblX.setBounds(222, 295, 22, 14);
		panel_1.add(lblX);
		
		JLabel lblDanaGodinjegOdmora = new JLabel("dana godišnjeg odmora za ovu godinu");
		lblDanaGodinjegOdmora.setBounds(254, 295, 278, 14);
		panel_1.add(lblDanaGodinjegOdmora);
		
		JButton btnRezervisiGodisnjiOdmor = new JButton("Rezerviši godišnji odmor");
		btnRezervisiGodisnjiOdmor.setBounds(385, 367, 195, 23);
		panel_1.add(btnRezervisiGodisnjiOdmor);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Vremenski interval", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(43, 21, 537, 244);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel label_15 = new JLabel("Od:");
		label_15.setBounds(20, 39, 46, 14);
		panel_8.add(label_15);
		
		JLabel label_21 = new JLabel("Do:");
		label_21.setBounds(282, 39, 46, 14);
		panel_8.add(label_21);
		
		JCalendar calendar_1 = new JCalendar();
		calendar_1.setBounds(30, 64, 198, 153);
		panel_8.add(calendar_1);
		
		JCalendar calendar_2 = new JCalendar();
		calendar_2.setBounds(315, 64, 198, 153);
		panel_8.add(calendar_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Bolovanje", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton button = new JButton("Kreiraj zahtjev");
		button.setBounds(439, 380, 132, 23);
		panel_2.add(button);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Vremenski interval", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(79, 11, 492, 130);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblOd_1 = new JLabel("Od:");
		lblOd_1.setBounds(32, 31, 128, 14);
		panel_6.add(lblOd_1);
		
		JLabel lblDo_1 = new JLabel("Do:");
		lblDo_1.setBounds(290, 31, 114, 14);
		panel_6.add(lblDo_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setDateFormatString("yyyy-MM-dd");
		dateChooser_2.setBounds(52, 56, 114, 20);
		panel_6.add(dateChooser_2);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setDateFormatString("yyyy-MM-dd");
		dateChooser_3.setBounds(300, 56, 114, 20);
		panel_6.add(dateChooser_3);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Razlog bolovanja", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(79, 158, 492, 171);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 20, 472, 140);
		panel_7.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		JCheckBox chckbxNalazSpecijaliste = new JCheckBox("Nalaz specijaliste");
		chckbxNalazSpecijaliste.setBounds(79, 354, 132, 23);
		panel_2.add(chckbxNalazSpecijaliste);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Neplanirano odsustvo", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Lični podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(43, 52, 498, 123);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(22, 37, 68, 14);
		panel_4.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(22, 75, 68, 14);
		panel_4.add(lblPrezime);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(299, 58, 46, 14);
		panel_4.add(lblSektor);
		
		textField = new JTextField();
		textField.setBounds(100, 34, 112, 20);
		panel_4.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 72, 112, 20);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(376, 55, 112, 20);
		panel_4.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Razlog odsustva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(43, 257, 498, 100);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 23, 478, 66);
		panel_5.add(scrollPane);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane.setViewportView(textArea_1);
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		
		JButton btnKreirajZahtjev = new JButton("Kreiraj zahtjev");
		btnKreirajZahtjev.setBounds(401, 368, 140, 23);
		panel_3.add(btnKreirajZahtjev);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Vrijeme odsustva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(43, 186, 498, 58);
		panel_3.add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(24, 22, 46, 14);
		panel_14.add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(330, 22, 46, 14);
		panel_14.add(lblDo);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(98, 22, 112, 20);
		panel_14.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(376, 22, 112, 20);
		panel_14.add(dateChooser_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsi.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogOut = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogOut);
	}
}
