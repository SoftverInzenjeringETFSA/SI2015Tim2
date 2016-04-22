package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class ManagementMainWindow {

	private JFrame frmSolutionsi;
	private JTextField txtHaso;
	private JTextField txtHasi;
	private JTextField txtkcl;
	private JTextField textField_2;
	private JTextField txtSarajevo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable table;
	private JTextField txtEkonomskiSektor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementMainWindow window = new ManagementMainWindow();
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
	public ManagementMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsi = new JFrame();
		frmSolutionsi.setTitle("SolutionSI");
		frmSolutionsi.setBounds(100, 100, 733, 489);
		frmSolutionsi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 697, 407);
		frmSolutionsi.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Po�etna", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 11, 128, 128);
		label.setIcon(new ImageIcon("C:\\Users\\AyLLa\\workspace\\SolutionSI\\Slike\\Person.gif"));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Status:");
		label_1.setBounds(10, 223, 68, 14);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_1);
		
		JLabel lblMenader = new JLabel("Menad\u017Eer");
		lblMenader.setBounds(70, 220, 89, 21);
		lblMenader.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMenader);
		
		JLabel label_3 = new JLabel("Sektor:");
		label_3.setBounds(10, 255, 68, 26);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_3);
		
		JLabel lblMenadmentLjudskihResursa = new JLabel("Menad\u017Ement ljudskih resursa");
		lblMenadmentLjudskihResursa.setBounds(70, 258, 219, 20);
		lblMenadmentLjudskihResursa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMenadmentLjudskihResursa);
		
		JLabel label_5 = new JLabel("Ukupno radnih dana:");
		label_5.setBounds(10, 301, 128, 14);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("251");
		label_6.setBounds(148, 301, 46, 14);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Za ovu godinu Vam je ostalo da iskoristite jo\u0161 20 dana od godi\u0161njeg odmora");
		label_7.setBounds(10, 346, 468, 26);
		label_7.setForeground(new Color(128, 0, 0));
		label_7.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		panel.add(label_7);
		
		JLabel lblMujoMuji = new JLabel("Mujo Muji\u0107");
		lblMujoMuji.setBounds(30, 150, 89, 26);
		lblMujoMuji.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMujoMuji);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(349, 36, 333, 279);
		panel_1.setBorder(new TitledBorder(null, "Zahtjevi za obradu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblZahtjevaekaju = new JLabel("1 zahtjev \u010Deka na obradu");
		lblZahtjevaekaju.setHorizontalAlignment(SwingConstants.CENTER);
		lblZahtjevaekaju.setBounds(54, 25, 207, 14);
		panel_1.add(lblZahtjevaekaju);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Zahtjev od Haso Hasi\u0107"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(54, 50, 221, 131);
		panel_1.add(list);
		
		JButton btnPogledajDetalje = new JButton("Pogledaj detalje zahtjeva");
		btnPogledajDetalje.setBounds(30, 208, 189, 23);
		panel_1.add(btnPogledajDetalje);
		
		JButton btnNewButton = new JButton("Odustani");
		btnNewButton.setBounds(234, 208, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnPregledKalendara = new JButton("Pregled kalendara");
		btnPregledKalendara.setBounds(113, 245, 141, 23);
		panel_1.add(btnPregledKalendara);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Zaposlenici", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Osnovni podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(35, 11, 249, 216);
		panel_2.add(panel_3);
		
		JLabel label_2 = new JLabel("Id zaposlenika:");
		label_2.setBounds(10, 23, 120, 14);
		panel_3.add(label_2);
		
		JLabel label_4 = new JLabel("Ime:");
		label_4.setBounds(10, 47, 120, 14);
		panel_3.add(label_4);
		
		JLabel label_8 = new JLabel("Prezime:");
		label_8.setBounds(10, 71, 120, 14);
		panel_3.add(label_8);
		
		JLabel label_9 = new JLabel("Broj li\u010Dne karte:");
		label_9.setBounds(10, 95, 120, 14);
		panel_3.add(label_9);
		
		JLabel label_10 = new JLabel("Spol:");
		label_10.setBounds(10, 119, 120, 14);
		panel_3.add(label_10);
		
		JLabel label_11 = new JLabel("Datum ro\u0111enja:");
		label_11.setBounds(10, 143, 120, 14);
		panel_3.add(label_11);
		
		JLabel label_12 = new JLabel("Mjesto ro\u0111enja:");
		label_12.setBounds(10, 167, 120, 14);
		panel_3.add(label_12);
		
		JLabel label_13 = new JLabel("Nacionalnost:");
		label_13.setBounds(10, 191, 120, 14);
		panel_3.add(label_13);
		
		JLabel label_14 = new JLabel("12352");
		label_14.setBounds(148, 23, 91, 14);
		panel_3.add(label_14);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 116, 99, 20);
		panel_3.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(140, 188, 99, 20);
		panel_3.add(comboBox_1);
		
		txtHaso = new JTextField();
		txtHaso.setText("Haso");
		txtHaso.setBounds(140, 44, 99, 20);
		panel_3.add(txtHaso);
		txtHaso.setColumns(10);
		
		txtHasi = new JTextField();
		txtHasi.setText("Hasi\u0107");
		txtHasi.setBounds(140, 68, 99, 20);
		panel_3.add(txtHasi);
		txtHasi.setColumns(10);
		
		txtkcl = new JTextField();
		txtkcl.setText("125KcL3");
		txtkcl.setBounds(140, 92, 99, 20);
		panel_3.add(txtkcl);
		txtkcl.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("15.02.1978.");
		textField_2.setBounds(140, 140, 99, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		txtSarajevo = new JTextField();
		txtSarajevo.setText("Sarajevo");
		txtSarajevo.setBounds(140, 164, 99, 20);
		panel_3.add(txtSarajevo);
		txtSarajevo.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Podaci o zaposlenju", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(322, 139, 285, 88);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblDatumZaposlenja = new JLabel("Datum zaposlenja:");
		lblDatumZaposlenja.setBounds(10, 30, 107, 14);
		panel_5.add(lblDatumZaposlenja);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 55, 87, 14);
		panel_5.add(lblSektor);
		
		textField_3 = new JTextField();
		textField_3.setText("25.10.2001.");
		textField_3.setBounds(127, 27, 128, 20);
		panel_5.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Ekonomski"}));
		comboBox_2.setBounds(127, 52, 128, 20);
		panel_5.add(comboBox_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Kontakt podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(322, 33, 285, 100);
		panel_2.add(panel_4);
		
		JLabel label_15 = new JLabel("E-mail:");
		label_15.setBounds(10, 29, 98, 14);
		panel_4.add(label_15);
		
		JLabel label_16 = new JLabel("Broj telefona:");
		label_16.setBounds(10, 51, 98, 14);
		panel_4.add(label_16);
		
		textField = new JTextField();
		textField.setText("hhasic1@gmail.com");
		textField.setColumns(10);
		textField.setBounds(127, 23, 148, 20);
		panel_4.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("+387 62 256 364");
		textField_1.setColumns(10);
		textField_1.setBounds(127, 48, 148, 20);
		panel_4.add(textField_1);
		
		JLabel label_17 = new JLabel("Ismeta Mujezinovi\u0107a 23/5");
		label_17.setBounds(127, 76, 148, 14);
		panel_4.add(label_17);
		
		JLabel label_18 = new JLabel("Adresa:");
		label_18.setBounds(10, 76, 46, 14);
		panel_4.add(label_18);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zaposlenici", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 238, 672, 110);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Id zaposlenika", "Ime", "Prezime", "Sektor", "Broj dana odmora"},
				{"12352", "Haso", "Hasi\u0107", "Ekonomski", "20"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(10, 23, 652, 76);
		panel_6.add(table);
		
		JButton btnDodajZaposlenika = new JButton("Dodaj novog zaposlenika");
		btnDodajZaposlenika.setBounds(495, 7, 187, 23);
		panel_2.add(btnDodajZaposlenika);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(593, 356, 89, 23);
		panel_2.add(btnOdustani);
		
		JButton btnUrediPodatkeO = new JButton("Uredi podatke o zaposleniku");
		btnUrediPodatkeO.setBounds(210, 356, 195, 23);
		panel_2.add(btnUrediPodatkeO);
		
		JButton btnObriiZaposlenika = new JButton("Obri\u0161i zaposlenika");
		btnObriiZaposlenika.setBounds(415, 356, 168, 23);
		panel_2.add(btnObriiZaposlenika);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Sektori", null, panel_7, null);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Sektori", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 63, 258, 305);
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Ekonomski sektor", "Tehni\u010Dki sektor", "Pravni sektor", "IT sektor"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(10, 25, 238, 269);
		panel_8.add(list_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Podaci o sektoru", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(278, 63, 349, 234);
		panel_7.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNazivSektora = new JLabel("Naziv sektora:");
		lblNazivSektora.setBounds(10, 40, 88, 14);
		panel_9.add(lblNazivSektora);
		
		JLabel lblGodinaOsnivanja = new JLabel("Godina osnivanja:");
		lblGodinaOsnivanja.setBounds(10, 78, 88, 14);
		panel_9.add(lblGodinaOsnivanja);
		
		JLabel lblTrenutniBrojZaposlenih = new JLabel("Trenutni broj zaposlenih:");
		lblTrenutniBrojZaposlenih.setBounds(10, 114, 155, 14);
		panel_9.add(lblTrenutniBrojZaposlenih);
		
		JLabel lblMaksimalanBrojOdsutnih = new JLabel("Maksimalan broj odsutnih  ljudi u jednom danu:");
		lblMaksimalanBrojOdsutnih.setBounds(10, 147, 275, 14);
		panel_9.add(lblMaksimalanBrojOdsutnih);
		
		JLabel lblOpisSektora = new JLabel("Opis sektora:");
		lblOpisSektora.setBounds(10, 185, 81, 14);
		panel_9.add(lblOpisSektora);
		
		txtEkonomskiSektor = new JTextField();
		txtEkonomskiSektor.setText("Ekonomski sektor");
		txtEkonomskiSektor.setBounds(192, 37, 147, 20);
		panel_9.add(txtEkonomskiSektor);
		txtEkonomskiSektor.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(192, 103, 147, 20);
		panel_9.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinner_1.setBounds(192, 68, 147, 20);
		panel_9.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(295, 144, 44, 20);
		panel_9.add(spinner_2);
		
		JTextArea txtrEkonomskiSektorSe = new JTextArea();
		txtrEkonomskiSektorSe.setToolTipText("");
		txtrEkonomskiSektorSe.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrEkonomskiSektorSe.setRows(10);
		txtrEkonomskiSektorSe.setText("Ekonomski sektor se bavi ekonomskim \r\nposlovima kompanije");
		txtrEkonomskiSektorSe.setBounds(89, 185, 250, 38);
		panel_9.add(txtrEkonomskiSektorSe);
		
		JButton btnDodajNoviSektor = new JButton("Dodaj novi sektor");
		btnDodajNoviSektor.setBounds(385, 23, 156, 23);
		panel_7.add(btnDodajNoviSektor);
		
		JButton btnSpasiIzmjene = new JButton("Spasi izmjene");
		btnSpasiIzmjene.setBounds(551, 23, 131, 23);
		panel_7.add(btnSpasiIzmjene);
		
		JButton btnOdustani_1 = new JButton("Odustani");
		btnOdustani_1.setBounds(593, 345, 89, 23);
		panel_7.add(btnOdustani_1);
		
		JButton btnObriiSektor = new JButton("Obri\u0161i sektor");
		btnObriiSektor.setBounds(460, 345, 123, 23);
		panel_7.add(btnObriiSektor);
		
		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Izvje�taji", null, panel_10, null);
		panel_10.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Mjese\u010Dni izvje\u0161taj");
		btnNewButton_1.setBounds(65, 94, 251, 103);
		panel_10.add(btnNewButton_1);
		
		JButton btnGodinjiIzvjetaj = new JButton("Godi\u0161nji izvje\u0161taj");
		btnGodinjiIzvjetaj.setBounds(326, 94, 251, 103);
		panel_10.add(btnGodinjiIzvjetaj);
		
		JButton btnIzvjetajOPreostalom = new JButton("Izvje\u0161taj o broju dana godi\u0161njeg odmora za svakog uposlenika");
		btnIzvjetajOPreostalom.setBounds(162, 208, 331, 110);
		panel_10.add(btnIzvjetajOPreostalom);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsi.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnOdjava.add(mntmLogOut);
	}
}
