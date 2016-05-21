package tim12.si.app.godisnji_odmori.View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.toedter.calendar.JMonthChooser;

import tim12.si.app.godisnji_odmori.HibernateUtil;
import tim12.si.app.godisnji_odmori.Controller.IzvjestajController;
import tim12.si.app.godisnji_odmori.Controller.SektorController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZapVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import javax.swing.JButton;

public class MjesecniIzvjestaj {

	private JFrame frmSolutionsiMjesecni;
	private JTable table;
	private JComboBox comboBox;
	private JButton btnPrikaiIzvjetaj;
	private JMonthChooser monthChooser;
	private JDialog frame;
	final static Logger logger = Logger.getLogger(MjesecniIzvjestaj.class);

	/**
	 * Launch the application.
	 */
	public static void mjesecniIzvjestaj() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MjesecniIzvjestaj window = new MjesecniIzvjestaj();
					window.frmSolutionsiMjesecni.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MjesecniIzvjestaj() {
		initialize();
		frmSolutionsiMjesecni.setVisible(true);
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
    	
    	int rowCount = model1.getRowCount();
    	//Remove rows one by one from the end of the table
    	for (int i = rowCount - 1; i >= 0; i--) {
    	    model1.removeRow(i);
    	}
    	
    	try{
			SektorController sc = new SektorController();
			String sektori[] = sc.dajSveSektore();
			comboBox.setModel(new DefaultComboBoxModel(sektori));
			comboBox.setSelectedIndex(-1);
	
			/*btnPrikaiIzvjetaj.addActionListener(new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	PrikaziIzvjestaj();
			    }
	});*/
			}
		
		catch (Exception er) {

			logger.error(er);
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			

		} finally {
			
		}

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiMjesecni = new JFrame();
		frmSolutionsiMjesecni.setResizable(false);
		frmSolutionsiMjesecni.setTitle("SolutionSI - Mjesečni izvjestaj");
		frmSolutionsiMjesecni.setBounds(100, 100, 547, 306);
		frmSolutionsiMjesecni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsiMjesecni.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsiMjesecni.setLocation(dim.width/2-frmSolutionsiMjesecni.getSize().width/2, dim.height/2-frmSolutionsiMjesecni.getSize().height/2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 515, 178);
		frmSolutionsiMjesecni.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Ekonomski", "Haso", "Hasic", "125", "15"},
				{"Ekonomski", "Haso", "Hasic", "263", "10"},
				{null, null, null, null, null},
				{null, "", "Ukupno:", "25", null},
			},
			new String[] {
				"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(96);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(109);
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(10, 11, 59, 22);
		frmSolutionsiMjesecni.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setBounds(57, 12, 106, 20);
		frmSolutionsiMjesecni.getContentPane().add(comboBox);
		
		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(206, 12, 59, 20);
		frmSolutionsiMjesecni.getContentPane().add(lblMjesec);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(261, 11, 117, 22);
		frmSolutionsiMjesecni.getContentPane().add(monthChooser);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiMjesecni.setJMenuBar(menuBar);
		
		
		JButton btnPrikaiIzvjetaj = new JButton("Prikaži izvještaj");
		btnPrikaiIzvjetaj.setBounds(399, 11, 126, 23);
		btnPrikaiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrikaziIzvjestaj();
			}
		});
		frmSolutionsiMjesecni.getContentPane().add(btnPrikaiIzvjetaj);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogOut = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogOut);
	}
	
	void PrikaziIzvjestaj(){
		
		if(comboBox.getSelectedIndex()==-1){
			JOptionPane.showMessageDialog(frame, "Odaberite sektor", "Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
		PrikaziZaposlenika();
		
		/*switch (monthChooser.getMonth())
		{
		case 0:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 2: 
			//if mjesecPrisustva=februar 
			PrikaziZaposlenika();
			break;
		case 3:
			//if mjesecPrisustva=mart 
			PrikaziZaposlenika();
			break;
		case 4:
			//if mjesecPrisustva=april 
			PrikaziZaposlenika();
			break;
		case 5:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 6:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 7:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 8:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 9:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 10:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 11:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		case 1:
			//if mjesecPrisustva=januar 
			PrikaziZaposlenika();
			break;
		}*/
	  		    	
			}

	void PrikaziZaposlenika(){
		
		//ostalo mi je da izdvojim informacije o zaposleniku u odnosu na ono što je 
		//selektovano u monthChooseru
		Session sess = null;
		try {
		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		ZaposlenikController zc = new ZaposlenikController(sess);
		ArrayList<ZaposlenikBrDana> al = zc.DajZaposlenikeZaIzvjestaj((String)comboBox.getSelectedItem());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int rowCount = model.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
		
		for (int i=0; i<al.size(); i++)
		{
			
			Object[] objs = {(String)comboBox.getSelectedItem(), al.get(i).getZaposlenikIme(), al.get(i).getZaposlenikPrezime(), al.get(i).getRadniDani(), al.get(i).getIskoristeniGodisnji()};
			model.addRow(objs);
		}
		
		//events = oc.dajSvaOdsustva((String)combobox.getSelectedItem());
		
		}
		catch (Exception er) {
		
			logger.error(er);
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			
		
		} finally {
			if (sess != null)
				sess.close();
		}
	}
}
