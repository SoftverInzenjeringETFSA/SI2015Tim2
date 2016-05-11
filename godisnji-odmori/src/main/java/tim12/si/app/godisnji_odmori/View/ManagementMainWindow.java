package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import org.hibernate.Session;


import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;


import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;

public class ManagementMainWindow {

	private JFrame frmSolutionsi;
	private JTextField txtHaso;
	private JTextField txtHasi;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable table;
	private JTextField txtEkonomskiSektor;
	private JLabel lblMujoMuji;
	private JLabel lblMenadmentLjudskihResursa;
	private JDialog frame;
	Session sess = null;
	//static final Logger logger = Logger.getLogger(ManagementMainWindow.class);

	/**
	 * Launch the application.
	 */
	public static void Management() {
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
		provjeriUsera();
		
	}
	
	
	public void provjeriUsera ()
	{
		try {
			//UI.SetUsername("dbabahmeto1");
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZaposlenikController zc = new ZaposlenikController(sess);
			String varij = UI.DajUsername();
			ZaposlenikBrDana zbd = zc.DajZaposlenikViewModel(UI.DajUsername());
			System.out.println(zbd.getZaposlenikIme());
			System.out.println(zbd.getZaposlenikPrezime());
			lblMujoMuji.setText(zbd.getZaposlenikIme() + " " + zbd.getZaposlenikPrezime());
			lblMenadmentLjudskihResursa.setText(zbd.getSektor());
			
		}
		catch (Exception er) {

			
			JOptionPane.showMessageDialog(frame, er.getMessage(),
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
		frmSolutionsi = new JFrame();
		frmSolutionsi.setTitle("SolutionSI");
		frmSolutionsi.setBounds(100, 100, 840, 489);
		frmSolutionsi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsi.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 804, 407);
		frmSolutionsi.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Početna", null, panel, null);
		panel.setLayout(null);
		
		ImageIcon ii = new ImageIcon(getClass().getResource("/boss2.png"));
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 160, 128);
		label.setIcon(ii);
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
		
		lblMenadmentLjudskihResursa = new JLabel("Menadžment ljudskih resursa");
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
		
		JLabel lblZaOvuGodinu = new JLabel("Za ovu godinu Vam je ostalo da iskoristite još ");
		lblZaOvuGodinu.setBounds(10, 346, 292, 26);
		lblZaOvuGodinu.setForeground(new Color(128, 0, 0));
		lblZaOvuGodinu.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		panel.add(lblZaOvuGodinu);
		
		lblMujoMuji = new JLabel("");
		lblMujoMuji.setHorizontalAlignment(SwingConstants.CENTER);
		lblMujoMuji.setBounds(10, 152, 160, 26);
		lblMujoMuji.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMujoMuji);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(421, 36, 368, 279);
		panel_1.setBorder(new TitledBorder(null, "Zahtjevi za obradu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblZahtjevaekaju = new JLabel("1 zahtjev čeka na obradu");
		lblZahtjevaekaju.setHorizontalAlignment(SwingConstants.CENTER);
		lblZahtjevaekaju.setBounds(54, 25, 207, 14);
		panel_1.add(lblZahtjevaekaju);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(54, 50, 269, 131);
		panel_1.add(scrollPane_1);
		
		JList list = new JList();
		scrollPane_1.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Zahtjev od Haso Hasić"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnPogledajDetalje = new JButton("Pogledaj zahtjev");
		btnPogledajDetalje.setBounds(175, 245, 148, 23);
		panel_1.add(btnPogledajDetalje);
		
		JButton btnPregledKalendara = new JButton("Kalendar");
		btnPregledKalendara.setBounds(54, 245, 111, 23);
		panel_1.add(btnPregledKalendara);
		
		JLabel label_7 = new JLabel("20");
		label_7.setForeground(new Color(128, 0, 0));
		label_7.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		label_7.setBounds(302, 347, 30, 25);
		panel.add(label_7);
		
		JLabel lblDanaOdGodinjeg = new JLabel("dana od godišnjeg odmora");
		lblDanaOdGodinjeg.setForeground(new Color(128, 0, 0));
		lblDanaOdGodinjeg.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblDanaOdGodinjeg.setBounds(330, 346, 160, 26);
		panel.add(lblDanaOdGodinjeg);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Zaposlenici", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Osnovni podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(35, 11, 289, 126);
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
		
		JLabel label_11 = new JLabel("Datum rođenja:");
		label_11.setBounds(10, 96, 120, 14);
		panel_3.add(label_11);
		
		JLabel label_14 = new JLabel("12352");
		label_14.setBounds(148, 23, 91, 14);
		panel_3.add(label_14);
		
		txtHaso = new JTextField();
		txtHaso.setText("Haso");
		txtHaso.setBounds(140, 44, 139, 20);
		panel_3.add(txtHaso);
		txtHaso.setColumns(10);
		
		txtHasi = new JTextField();
		txtHasi.setText("Hasić");
		txtHasi.setBounds(140, 68, 139, 20);
		panel_3.add(txtHasi);
		txtHasi.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(140, 96, 139, 19);
		panel_3.add(dateChooser);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Podaci o zaposlenju", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(35, 136, 289, 99);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblDatumZaposlenja = new JLabel("Broj dana odmora:");
		lblDatumZaposlenja.setBounds(10, 30, 107, 14);
		panel_5.add(lblDatumZaposlenja);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 55, 87, 14);
		panel_5.add(lblSektor);
		
		textField_3 = new JTextField();
		textField_3.setText("20");
		textField_3.setBounds(141, 27, 138, 20);
		panel_5.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Ekonomski"}));
		comboBox_2.setBounds(141, 52, 138, 20);
		panel_5.add(comboBox_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Kontakt podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(35, 235, 289, 99);
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
		textField.setBounds(127, 23, 152, 20);
		panel_4.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("+387 62 256 364");
		textField_1.setColumns(10);
		textField_1.setBounds(127, 48, 152, 20);
		panel_4.add(textField_1);
		
		JLabel label_17 = new JLabel("Ismeta Mujezinovića 23/5");
		label_17.setBounds(127, 76, 152, 14);
		panel_4.add(label_17);
		
		JLabel label_18 = new JLabel("Adresa:");
		label_18.setBounds(10, 76, 46, 14);
		panel_4.add(label_18);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zaposlenici", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(334, 11, 455, 323);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 435, 289);
		panel_6.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"13212", "Osman", "Lav", "IT", "15"},
				{"12352", "Haso", "Hasi\u0107", "Ekonomski", "20"},
			},
			new String[] {
				"ID zaposlenika", "Ime", "Prezime", "Sektor", "Broj dana godi\u0161njeg"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		
		JButton btnDodajZaposlenika = new JButton("Dodaj zaposlenika");
		btnDodajZaposlenika.setBounds(35, 356, 143, 23);
		panel_2.add(btnDodajZaposlenika);
		
		JButton btnUrediPodatkeO = new JButton("Uredi zaposlenika");
		btnUrediPodatkeO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUrediPodatkeO.setBounds(408, 356, 140, 23);
		panel_2.add(btnUrediPodatkeO);
		
		JButton btnObriiZaposlenika = new JButton("Obriši zaposlenika");
		btnObriiZaposlenika.setBounds(610, 356, 140, 23);
		panel_2.add(btnObriiZaposlenika);
		
		JButton btnSpasiPromjene = new JButton("Spasi promjene");
		btnSpasiPromjene.setBounds(198, 356, 126, 23);
		panel_2.add(btnSpasiPromjene);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Sektori", null, panel_7, null);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Sektori", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(478, 27, 282, 305);
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 25, 262, 269);
		panel_8.add(scrollPane_2);
		
		JList list_1 = new JList();
		scrollPane_2.setViewportView(list_1);
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Ekonomski sektor", "Tehnički sektor", "Pravni sektor", "IT sektor"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Podaci o sektoru", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(35, 27, 349, 305);
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
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(946681200000L), null, null, Calendar.YEAR));
		spinner_1.setBounds(192, 68, 147, 20);
		panel_9.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(295, 144, 44, 20);
		panel_9.add(spinner_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(89, 185, 250, 89);
		panel_9.add(scrollPane_3);
		
		JTextArea txtrEkonomskiSektorSe = new JTextArea();
		txtrEkonomskiSektorSe.setLineWrap(true);
		scrollPane_3.setViewportView(txtrEkonomskiSektorSe);
		txtrEkonomskiSektorSe.setToolTipText("");
		txtrEkonomskiSektorSe.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrEkonomskiSektorSe.setRows(10);
		txtrEkonomskiSektorSe.setText("Ekonomski sektor se bavi ekonomskim \r\nposlovima kompanije");
		
		JLabel label_9 = new JLabel("5");
		label_9.setBounds(192, 114, 147, 14);
		panel_9.add(label_9);
		
		JButton btnDodajNoviSektor = new JButton("Dodaj sektor");
		btnDodajNoviSektor.setBounds(59, 356, 124, 23);
		panel_7.add(btnDodajNoviSektor);
		
		JButton btnSpasiIzmjene = new JButton("Uredi sektor");
		btnSpasiIzmjene.setBounds(496, 356, 124, 23);
		panel_7.add(btnSpasiIzmjene);
		
		JButton btnObriiSektor = new JButton("Obriši sektor");
		btnObriiSektor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnObriiSektor.setBounds(630, 356, 124, 23);
		panel_7.add(btnObriiSektor);
		
		JButton btnSpasiPromjene_1 = new JButton("Spasi promjene");
		btnSpasiPromjene_1.setBounds(232, 356, 124, 23);
		panel_7.add(btnSpasiPromjene_1);
		
		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Izvještaji", null, panel_10, null);
		panel_10.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Mjesečni izvještaj");
		btnNewButton_1.setBounds(269, 69, 251, 49);
		panel_10.add(btnNewButton_1);
		
		JButton btnGodinjiIzvjetaj = new JButton("Godišnji izvještaj");
		btnGodinjiIzvjetaj.setBounds(269, 151, 251, 49);
		panel_10.add(btnGodinjiIzvjetaj);
		
		JButton btnIzvjetajOPreostalom = new JButton("Izvještaj o preostalim danima");
		btnIzvjetajOPreostalom.setBounds(269, 236, 251, 49);
		panel_10.add(btnIzvjetajOPreostalom);
		
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
