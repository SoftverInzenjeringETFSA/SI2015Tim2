package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;
import tim12.si.app.godisnji_odmori.Controller.*;
import tim12.si.app.godisnji_odmori.Model.Sektor;
import tim12.si.app.godisnji_odmori.ViewModel.SektorVM;

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
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;


import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import tim12.si.app.godisnji_odmori.Singleton;
import tim12.si.app.godisnji_odmori.ZaposlenikNotFound;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

import com.toedter.calendar.JYearChooser;
import javax.swing.JCheckBox;

public class ManagementMainWindow {

	private JFrame frmSolutionsi;
	private JTextField txtHaso;
	private JTextField txtHasi;
	private JTextField txtEmail;
	private JTextField txtBrojTelefona;
	private JTextField txtBrojDanaOdmora;
	private JTable table;
	private JTextField txtEkonomskiSektor;
	private JLabel lblMujoMuji;
	private JLabel lblMenadmentLjudskihResursa;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_10;
	private JLabel lblIDZaposlenika;
	private JList list;
	private JScrollPane scrollPane_1;
	private JDialog frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextArea txtrEkonomskiSektorSe;
	private JSpinner spinner_2;
	private JDateChooser dateRodjen;
	private JLabel label_9;
	private JScrollPane scrollPane_3;
	private JComboBox comboSektor;
	JList<String> list_1;
	JYearChooser spinner_1;
	JYearChooser spinner_3;
	JYearChooser spinner_4;
	private JCheckBox chckbxManagerPrivilegija;
	


	//static final Logger logger = Logger.getLogger(ManagementMainWindow.class);

	Session sess = null;
	private ArrayList<ZahtjevVM> zvm;
	private SektorController sC = new SektorController();
	private ZaposlenikController zC;
	private JTextField txtAdresa;


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
		sC = new SektorController();
		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		zC = new ZaposlenikController(sess);
		initialize();
		provjeriUsera();
		
	}
	
	
	public void provjeriUsera ()
	{
		
		//this.frmSolutionsi.setVisible(true);
		try {
			//UI.SetUsername("dbabahmeto1");
			
			ZaposlenikController zc = new ZaposlenikController(sess);
			ZaposlenikBrDana zbd = zc.DajZaposlenikViewModel(Singleton.getInstance().getUsername());
			lblMujoMuji.setText(zbd.getZaposlenikIme() + " " + zbd.getZaposlenikPrezime());
			lblMenadmentLjudskihResursa.setText(zbd.getSektor());
			label_6.setText(zbd.getRadniDani().toString());
			label_7.setText(zbd.getPreostaloSlobodnih().toString());
			
			ZahtjevController zc2 = new ZahtjevController(sess);
			zvm = zc2.dajSveZahtjeveIzSektora(zbd.getSektor());
			ArrayList<String> listaPodnosilaca = new ArrayList<String>();
			for (int i=0; i<zvm.size(); i++) listaPodnosilaca.add("Zahtjev od " + zvm.get(i).getPodnosilacIme() + " " + zvm.get(i).getPodnosilacPrezime());
			list = new JList(listaPodnosilaca.toArray());
			scrollPane_1.setViewportView(list);
			label_10.setText(Integer.toString(zvm.size()));
			
			
			
		}
		catch (Exception er) {

			
			if (er.getMessage() != null )
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(frame, "Korisnik sa username: " + Singleton.getInstance().getUsername() + " ne postoji.",
					"Greška", JOptionPane.INFORMATION_MESSAGE);

		} 
		
		
	}
	public void otvoriZahtjev (int selected)
	{
		if (selected == -1) {JOptionPane.showMessageDialog(frame, "Neophodno je prvo selektovati zahtjev",
				"Info", JOptionPane.INFORMATION_MESSAGE); return;}
		new ZahtjevPregledManagement(zvm.get(selected).getIdZahtjeva(),zvm.get(selected).getNazivSektora(),zvm.get(selected).getUsernamePodnosioca());
		frmSolutionsi.dispose();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsi = new JFrame();
		frmSolutionsi.setTitle("SolutionSI");
		frmSolutionsi.setBounds(100, 100, 840, 550);
		frmSolutionsi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsi.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsi.setLocation(dim.width/2-frmSolutionsi.getSize().width/2, dim.height/2-frmSolutionsi.getSize().height/2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 804, 468);
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
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(10, 223, 68, 14);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_1);
		
		JLabel lblMenader = new JLabel("Menad\u017Eer");
		lblMenader.setBounds(148, 220, 89, 21);
		lblMenader.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMenader);
		
		JLabel label_3 = new JLabel("Sektor:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setBounds(10, 255, 68, 26);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(label_3);
		
		lblMenadmentLjudskihResursa = new JLabel("Menadžment ljudskih resursa");
		lblMenadmentLjudskihResursa.setBounds(148, 258, 219, 20);
		lblMenadmentLjudskihResursa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblMenadmentLjudskihResursa);
		
		JLabel lblOdraeniRadniDani = new JLabel("Odrađeni radni dani:");
		lblOdraeniRadniDani.setHorizontalAlignment(SwingConstants.LEFT);
		lblOdraeniRadniDani.setBounds(10, 301, 128, 14);
		lblOdraeniRadniDani.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblOdraeniRadniDani);
		
		label_6 = new JLabel("251");
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
		
		JLabel lblZahtjevaekaju = new JLabel("zahtjeva čekaju na obradu");
		lblZahtjevaekaju.setHorizontalAlignment(SwingConstants.CENTER);
		lblZahtjevaekaju.setBounds(85, 25, 176, 14);
		panel_1.add(lblZahtjevaekaju);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(54, 50, 269, 131);
		panel_1.add(scrollPane_1);
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		/*list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Zahtjev od Haso Hasić"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});*/
		
		JButton btnPogledajDetalje = new JButton("Pogledaj zahtjev");
		btnPogledajDetalje.setBounds(175, 245, 148, 23);
		panel_1.add(btnPogledajDetalje);
		
		btnPogledajDetalje.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int selected = list.getSelectedIndex();
						otvoriZahtjev(selected);
					}
				});
		
		JButton btnPregledKalendara = new JButton("Kalendar");
		btnPregledKalendara.setBounds(54, 245, 111, 23);
		panel_1.add(btnPregledKalendara);
		
		btnPregledKalendara.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//frmSolutionsi.setVisible(false);
				new KalendarPregledManagement();
				
			}
		});
		
		label_10 = new JLabel("0");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setBounds(39, 25, 46, 14);
		panel_1.add(label_10);
		
		label_7 = new JLabel("20");
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
		panel_3.setBounds(35, 11, 289, 131);
		panel_2.add(panel_3);
		
		JLabel label_2 = new JLabel("Id zaposlenika:");
		label_2.setBounds(10, 19, 120, 14);
		panel_3.add(label_2);
		
		JLabel label_4 = new JLabel("Ime:");
		label_4.setBounds(10, 41, 120, 14);
		panel_3.add(label_4);
		
		JLabel label_8 = new JLabel("Prezime:");
		label_8.setBounds(10, 69, 120, 14);
		panel_3.add(label_8);
		
		JLabel label_11 = new JLabel("Datum rođenja:");
		label_11.setBounds(10, 96, 120, 14);
		panel_3.add(label_11);
		
		JLabel lblIDZaposlenika = new JLabel("");
		lblIDZaposlenika.setBounds(148, 19, 91, 14);
		panel_3.add(lblIDZaposlenika);
		
		txtHaso = new JTextField();
		txtHaso.setBounds(140, 38, 139, 20);
		panel_3.add(txtHaso);
		txtHaso.setColumns(10);
		
		txtHasi = new JTextField();
		txtHasi.setBounds(140, 66, 139, 20);
		panel_3.add(txtHasi);
		txtHasi.setColumns(10);
		
		dateRodjen = new JDateChooser();
		dateRodjen.setDateFormatString("yyyy-MM-dd");
		dateRodjen.setBounds(140, 96, 139, 19);
		panel_3.add(dateRodjen);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_4.setBounds(89, 54, 190, 14);
		panel_3.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(89, 83, 190, 14);
		panel_3.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_6.setBounds(89, 114, 190, 14);
		panel_3.add(lblNewLabel_6);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Podaci o zaposlenju", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(35, 148, 289, 126);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblDatumZaposlenja = new JLabel("Broj dana odmora:");
		lblDatumZaposlenja.setBounds(10, 24, 107, 14);
		panel_5.add(lblDatumZaposlenja);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 55, 87, 14);
		panel_5.add(lblSektor);
		
		txtBrojDanaOdmora = new JTextField();
		txtBrojDanaOdmora.setBounds(141, 21, 138, 20);
		panel_5.add(txtBrojDanaOdmora);
		txtBrojDanaOdmora.setColumns(10);
		
		comboSektor = new JComboBox();
		String sektori[] = sC.dajSveSektore();
		comboSektor.setModel(new DefaultComboBoxModel(sektori));
		comboSektor.setSelectedIndex(-1);
		comboSektor.setBounds(141, 52, 138, 20);
		panel_5.add(comboSektor);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(74, 38, 205, 14);
		panel_5.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setBounds(103, 71, 176, 14);
		panel_5.add(lblNewLabel_8);
		
		chckbxManagerPrivilegija = new JCheckBox("Manager privilegija            ");
		chckbxManagerPrivilegija.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxManagerPrivilegija.setBounds(10, 96, 176, 23);
		panel_5.add(chckbxManagerPrivilegija);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Kontakt podaci", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(35, 285, 289, 110);
		panel_2.add(panel_4);
		
		JLabel label_15 = new JLabel("E-mail:");
		label_15.setBounds(10, 25, 98, 14);
		panel_4.add(label_15);
		
		JLabel label_16 = new JLabel("Broj telefona:");
		label_16.setBounds(10, 51, 98, 14);
		panel_4.add(label_16);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(127, 19, 152, 20);
		panel_4.add(txtEmail);
		
		txtBrojTelefona = new JTextField();
		txtBrojTelefona.setColumns(10);
		txtBrojTelefona.setBounds(127, 48, 152, 20);
		panel_4.add(txtBrojTelefona);
		
		JLabel label_18 = new JLabel("Adresa:");
		label_18.setBounds(10, 80, 46, 14);
		panel_4.add(label_18);
		
		txtAdresa = new JTextField();
		txtAdresa.setBounds(127, 77, 152, 20);
		panel_4.add(txtAdresa);
		txtAdresa.setColumns(10);
		
		spinner_4 = new JYearChooser();
		spinner_4.setBounds(192, 72, 147, 20);
		spinner_4.setValue(9999);
		spinner_4.setVisible(false);
		panel_4.add(spinner_4);
		
		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(91, 36, 188, 14);
		panel_4.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(90, 64, 188, 14);
		panel_4.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(91, 94, 188, 14);
		panel_4.add(lblNewLabel_11);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zaposlenici", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(334, 11, 455, 384);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 435, 350);
		panel_6.add(scrollPane);
		zC.dajSveZaposlenike();
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			zC.dajSveZaposlenike(),
			new String[] {
				"ID zaposlenika", "Ime", "Prezime", "Sektor", "Broj dana godi\u0161njeg"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		
		
		JButton btnDodajZaposlenika = new JButton("Dodaj zaposlenika");
		btnDodajZaposlenika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocistiUnosZaposlenika();
            	if(!validirajUnosZaposlenika())
            		return;
				try {
					
	            	List<String> listaStringova =zC.DodajZaposlenika(new ZaposlenikVM (txtHaso.getText(), txtHasi.getText(),txtEmail.getText(), dateRodjen.getDate(), txtBrojTelefona.getText(), txtAdresa.getText(),(String)comboSektor.getSelectedItem(),txtBrojDanaOdmora.getText(),chckbxManagerPrivilegija.isSelected()));
	            	JOptionPane.showMessageDialog (null, "Uspjesno ste dodali novog zaposlenika\n Username :"+listaStringova.get(0)+"\n Password: "+ listaStringova.get(1), "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
                osvjeziTabeluZaposlenika();
            	ocistiPoljaZaposlenik();
			}
		});
		btnDodajZaposlenika.setBounds(35, 406, 143, 23);
		panel_2.add(btnDodajZaposlenika);
		
		JButton btnUrediPodatkeO = new JButton("Uredi zaposlenika");
		btnUrediPodatkeO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					ZaposlenikVM zaposlenikVM=zC.dajZaposlenikaZaUredjivanje(id);
					txtHaso.setText(zaposlenikVM.ime);
					txtHasi.setText(zaposlenikVM.prezime);
					txtEmail.setText(zaposlenikVM.email);
					dateRodjen.setDate(zaposlenikVM.datumRodjenja);
					txtBrojTelefona.setText(zaposlenikVM.telefon);
					txtAdresa.setText(zaposlenikVM.adresaStanovanja);
					comboSektor.setSelectedItem(sC.dajNazivSektoraPoIdBaza(zaposlenikVM.sektor));
					chckbxManagerPrivilegija.setSelected(zaposlenikVM.privilegija);
					spinner_4.setValue(id);
					
					StringBuilder sb = new StringBuilder();
		        	sb.append("");
		        	sb.append(zaposlenikVM.brojDanaGodisnje);
		        	String strI1 = sb.toString();
					
					txtBrojDanaOdmora.setText(strI1);
					
					
				} catch (Exception er) {
					
					if (er.getMessage() != null )
						JOptionPane.showMessageDialog(frame, er.getMessage(),
								"Greška", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
			
		});
		btnUrediPodatkeO.setBounds(408, 406, 140, 23);
		panel_2.add(btnUrediPodatkeO);
		
		JButton btnObriiZaposlenika = new JButton("Obriši zaposlenika");
		btnObriiZaposlenika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0){
            		JOptionPane.showMessageDialog (null, "Da bi ste obrisali zapolsenika, prvo ga morate selektovat", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				/*sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
				zC = new ZaposlenikController(sess);*/
				Object[] options = { "Da", "Ne" };
				int n = JOptionPane.showOptionDialog(null,
                        "Da li ste sigurni da zelite obrisati?",
                        "Upozorenje",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,     
                         options,  
                         options[0]);
				
				if (n == JOptionPane.YES_OPTION) {
					
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					zC.obrisiZaposlenika(id);
				    osvjeziTabeluZaposlenika();
				    ocistiPolja();
				    ocistiUnosZaposlenika();
				}
			}
		});
		btnObriiZaposlenika.setBounds(610, 406, 140, 23);
		panel_2.add(btnObriiZaposlenika);
		
		JButton btnSpasiPromjene = new JButton("Spasi promjene");
		btnSpasiPromjene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ocistiUnosZaposlenika();
            	if(!validirajUnosZaposlenika())
            		return;        	
            		
            		
            		if(spinner_4.getValue()==9999){
                		
                		JOptionPane.showMessageDialog (null, "Ako zelite dodati novog zaposlenika pritisnite dugme Dodaj zaposlenika", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
                		return;
                	}
            		
            		zC.ModificirajZaposlenika(new ZaposlenikVM(txtHaso.getText(), txtHasi.getText(),txtEmail.getText(), dateRodjen.getDate(), txtBrojTelefona.getText(),txtAdresa.getText(),(String)comboSektor.getSelectedItem(),txtBrojDanaOdmora.getText(),chckbxManagerPrivilegija.isSelected()),spinner_4.getValue());
	            	JOptionPane.showMessageDialog (null, "Uspjesno ste uredili zaposlenika ","Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
            		osvjeziTabeluZaposlenika();
                	ocistiPoljaZaposlenik();
            	
			}
		});
		
		btnSpasiPromjene.setBounds(198, 406, 126, 23);
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
		
		list_1 = new JList<String>();
		//list_1.setModel(sC.dajSveSektore());
		scrollPane_2.setViewportView(list_1);
		list_1.setModel(new AbstractListModel() {
			String[] values = sC.dajSveSektore();
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
		lblGodinaOsnivanja.setBounds(10, 78, 108, 14);
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
		txtEkonomskiSektor.setText("");
		txtEkonomskiSektor.setBounds(192, 37, 147, 20);
		panel_9.add(txtEkonomskiSektor);
		txtEkonomskiSektor.setColumns(10);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(295, 144, 44, 20);
		panel_9.add(spinner_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(89, 185, 250, 89);
		panel_9.add(scrollPane_3);
		
		txtrEkonomskiSektorSe = new JTextArea();
		txtrEkonomskiSektorSe.setLineWrap(true);
		scrollPane_3.setViewportView(txtrEkonomskiSektorSe);
		txtrEkonomskiSektorSe.setToolTipText("");
		txtrEkonomskiSektorSe.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrEkonomskiSektorSe.setRows(10);
		txtrEkonomskiSektorSe.setText("");
		
		label_9 = new JLabel("0");
		label_9.setBounds(192, 114, 147, 14);
		panel_9.add(label_9);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(192, 55, 147, 14);
		panel_9.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(146, 89, 193, 14);
		panel_9.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(64, 160, 275, 14);
		panel_9.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(192, 280, 147, 14);
		panel_9.add(lblNewLabel_3);
		
		spinner_1 = new JYearChooser();
		spinner_1.setBounds(192, 72, 147, 20);
		panel_9.add(spinner_1);
		
		spinner_3 = new JYearChooser();
		spinner_3.setBounds(192, 72, 147, 20);
		spinner_3.setValue(9999);
		spinner_3.setVisible(false);
		panel_9.add(spinner_3);
		
		JButton btnDodajNoviSektor = new JButton("Dodaj sektor");
		btnDodajNoviSektor.setBounds(59, 356, 124, 23);
		panel_7.add(btnDodajNoviSektor);
		btnDodajNoviSektor.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	
            	ocistiUnosSektora();
            	if(!validirajUnosSektora())
            		return;
            	sC.dodajSektor(new SektorVM(txtEkonomskiSektor.getText(), spinner_1.getValue(),0,spinner_2.getValue().toString(),txtrEkonomskiSektorSe.getText()));
        		JOptionPane.showMessageDialog (null, "Uspjesno ste dodali novi sektor", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
            	osvjeziListuSektora();
            	ocistiPolja();
            }
        }); 

		
		JButton btnSpasiIzmjene = new JButton("Uredi sektor");
		btnSpasiIzmjene.setBounds(496, 356, 124, 23);
		panel_7.add(btnSpasiIzmjene);
		btnSpasiIzmjene.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e)
            {   
            	String naziv = list_1.getSelectedValue();
            	Sektor sektor = sC.dajSektorPoNazivu(naziv);
            	txtEkonomskiSektor.setText(sektor.getNaziv());
            	txtrEkonomskiSektorSe.setText(sektor.getOpis());     	
            	spinner_1.setValue(sektor.getGodina_osnivanja());
            	spinner_2.setValue(sektor.getMax_broj_odsutnih());
            	spinner_3.setValue((int)sektor.getSektor_id());
            	StringBuilder sb = new StringBuilder();
            	sb.append("");
            	sb.append(sektor.getBroj_uposlenih());
            	String strI = sb.toString();
            	label_9.setText(strI);
            	
            }
        });
	
		
		JButton btnObriiSektor = new JButton("Obriši sektor");
		btnObriiSektor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list_1.getSelectedValue()==null){
            		JOptionPane.showMessageDialog (null, "Da bi ste obrisali sektor, prvo ga morate selektovat", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object[] options = { "Da", "Ne" };
				int n = JOptionPane.showOptionDialog(null,
                        "Da li ste sigurni da zelite obrisati?",
                        "Upozorenje",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,     
                         options,  
                         options[0]);
				
				if (n == JOptionPane.YES_OPTION) {
					String naziv = list_1.getSelectedValue();
				    sC.obrisiSektor(naziv);
				    osvjeziListuSektora();
				    ocistiPolja();
				    ocistiUnosSektora();
				}
				
			}
		});
		btnObriiSektor.setBounds(630, 356, 124, 23);
		panel_7.add(btnObriiSektor);
		
		JButton btnSpasiPromjene_1 = new JButton("Spasi promjene");
		btnSpasiPromjene_1.setBounds(232, 356, 124, 23);
		panel_7.add(btnSpasiPromjene_1);
		btnSpasiPromjene_1.addActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e)
            {   
            	
            	ocistiUnosSektora();
            	if(!validirajUnosSektora())
            		return;
            	if(spinner_3.getValue()==9999){
            		
            		JOptionPane.showMessageDialog (null, "Ako zelite dodati novi sektor pritisnite dugme Dodaj sektor", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
            		return;
            	}
            	sC.modificirajSektor(new SektorVM(txtEkonomskiSektor.getText(), spinner_1.getValue(),0,spinner_2.getValue().toString(),txtrEkonomskiSektorSe.getText()), spinner_3.getValue());
            	JOptionPane.showMessageDialog (null, "Uspjesno ste  uredili sektor ","Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
            	osvjeziListuSektora();
            	ocistiPolja();
            }
        });
		
		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Izvještaji", null, panel_10, null);
		panel_10.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Mjesečni izvještaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MjesecniIzvjestaj mi= new MjesecniIzvjestaj();
				mi.mjesecniIzvjestaj();
				
			}
		});
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
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSolutionsi.dispose();
				UI ui = new UI();
				ui.UIShow();
			}
		});
		mnOdjava.add(mntmLogOut);
	}
	/*public static void Prekini(final JDialog dialog) {
		ActionListener escListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		};

		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}*/

 
  
  public void osvjeziListuSektora (){
		list_1.setModel(new AbstractListModel() {
			String[] values = sC.dajSveSektore();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
  
  public void osvjeziTabeluZaposlenika(){
	  
	  table.setModel(new DefaultTableModel(
				zC.dajSveZaposlenike(),
				new String[] {
					"ID zaposlenika", "Ime", "Prezime", "Sektor", "Broj dana godi\u0161njeg"
				}
			));
	  
	  table.getColumnModel().getColumn(0).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
	  
  }

public Boolean validirajUnosSektora(){
	  Boolean validacija = true;

	  
	  if(txtEkonomskiSektor.getText().isEmpty()){
		  
		  lblNewLabel.setText("Polje Naziv je obavezno");
		  validacija=false;
		  
	  }
	  
	    StringBuilder sb = new StringBuilder();
	  	sb.append("");
	  	sb.append(spinner_1.getValue());
	  	String strI1 = sb.toString();
	  
	  if(spinner_1.getValue()== 0 || strI1.equals("")){
		  
		  lblNewLabel_1.setText("Polje Godina je obavezno");
		  validacija=false;
		  
	  }
	  else{
			
			String regex = "^\\d+$";
			if(!strI1.matches(regex)){
				
				lblNewLabel_1.setText("Polje Godina moze sadrzavat samo cifre");
				validacija=false;
			}
			
		}
	  
	  if(spinner_2.getValue().toString().equals("0") || spinner_2.getValue().toString().equals("") ){
		  
		  lblNewLabel_2.setText("Polje Maximalan broj odsutnih je obavezno");
		  validacija=false;  
	  }
	  else{
			
			String regex = "^\\d+$";
			if(!spinner_2.getValue().toString().matches(regex)){
				
				lblNewLabel_2.setText("Polje Maximalan broj odsustva moze sadrzavat samo cifre");
				validacija=false;
			}
			
		}
	  
	  return validacija;
}

	public Boolean validirajUnosZaposlenika(){
		
		Boolean validacija=true;
		if (txtHaso.getText().isEmpty()){
			lblNewLabel_4.setText("Polje Ime je obavezno");
			validacija=false;
		}
		if (txtHasi.getText().isEmpty()){
			lblNewLabel_5.setText("Polje Prezime je obavezno");
			validacija=false;
		}

		if(txtBrojDanaOdmora.getText().isEmpty()){
			
			lblNewLabel_7.setText("Polje Broj dana odmora je obavezno");
			validacija=false;
		}
		else{
			
			String regex = "^\\d+$";
			if(!txtBrojDanaOdmora.getText().matches(regex)){
				
				lblNewLabel_7.setText("Polje Broj dana moze sadrzavat samo cifre");
				validacija=false;
			}
			
		}
		
		if (comboSektor.getSelectedIndex()==-1){
			lblNewLabel_8.setText("Polje Sektor je obavezno");
			validacija=false;
		}
		
		return validacija;
	}

	public void ocistiUnosSektora(){
	  
	  lblNewLabel.setText("");
	  lblNewLabel_1.setText("");
	  lblNewLabel_2.setText("");
	  lblNewLabel_3.setText("");
	  
	}

	public void ocistiUnosZaposlenika(){
		  
		  lblNewLabel_4.setText("");
		  lblNewLabel_5.setText("");
		  lblNewLabel_6.setText("");
		  lblNewLabel_7.setText("");
		  lblNewLabel_8.setText("");
		  lblNewLabel_9.setText("");
		  lblNewLabel_10.setText("");
		  lblNewLabel_11.setText("");
		  
		}

	public void ocistiPolja (){
		
		txtEkonomskiSektor.setText("");
    	txtrEkonomskiSektorSe.setText("");
    	label_9.setText("0");
    	spinner_1.setValue(2016);
    	spinner_2.setValue(0);
    	spinner_3.setValue(9999);
	}
	
	public void ocistiPoljaZaposlenik(){
		

		txtHaso.setText("");
		txtHasi.setText("");
		dateRodjen.setDate(null);
		txtBrojDanaOdmora.setText("");
		comboSektor.setSelectedIndex(-1);
		txtEmail.setText("");
		txtBrojTelefona.setText("");
		txtAdresa.setText("");
		spinner_4.setValue(9999);
	}
}

