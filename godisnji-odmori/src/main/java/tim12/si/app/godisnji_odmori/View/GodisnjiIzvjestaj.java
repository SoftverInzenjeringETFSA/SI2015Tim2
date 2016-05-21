package tim12.si.app.godisnji_odmori.View;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JYearChooser;

import tim12.si.app.godisnji_odmori.Controller.IzvjestajController;
import tim12.si.app.godisnji_odmori.Controller.SektorController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import tim12.si.app.godisnji_odmori.Model.Sektor;
public class GodisnjiIzvjestaj {

	private JFrame frmSolutionsiGodisnji;
	private JTable table;
	private JComboBox cbSektori;
	private JDialog frame;
	private SektorController sc;
	private JYearChooser godina;
	private IzvjestajController ic;

	final static Logger logger = Logger.getLogger(GodisnjiIzvjestaj.class);
	
	/**
	 * Launch the application.
	 */
	public static void GodisnjiIzvjestaj() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GodisnjiIzvjestaj window = new GodisnjiIzvjestaj();
					window.frmSolutionsiGodisnji.setVisible(true);
				} 
				catch (Exception e) {
					
					logger.error(e);
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GodisnjiIzvjestaj() {
		

		initialize();
		frmSolutionsiGodisnji.setVisible(true);
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
    	
    	int rowCount = model1.getRowCount();
    	//Remove rows one by one from the end of the table
    	for (int i = rowCount - 1; i >= 0; i--) {
    	    model1.removeRow(i);
    	}
		
		
		try{
			SektorController sc = new SektorController();
			String sektori[] = sc.dajSveSektore();
			cbSektori.setModel(new DefaultComboBoxModel(sektori));
			cbSektori.setSelectedIndex(-1);
			
			cbSektori.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	Session sess = null;
			    	try {
			    	sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			    	ZaposlenikController zc = new ZaposlenikController(sess);
			    	ArrayList<ZaposlenikBrDana> al = zc.DajZaposlenikeZaGodisnjiIzvjestaj((String)cbSektori.getSelectedItem(),godina.getValue());
			    	DefaultTableModel model = (DefaultTableModel) table.getModel();
			    	
			    	int rowCount = model.getRowCount();
			    	//Remove rows one by one from the end of the table
			    	for (int i = rowCount - 1; i >= 0; i--) {
			    	    model.removeRow(i);
			    	}
			    int brojiRadne=0;
			    int brojiNeradne=0;
			    	for (int i=0; i<al.size(); i++)
			    	{
			    		
			    		Object[] objs = {(String)cbSektori.getSelectedItem(), al.get(i).getZaposlenikIme(), al.get(i).getZaposlenikPrezime(), al.get(i).getRadniDani(), al.get(i).getPreostaloSlobodnih()};
			    		model.addRow(objs);
			    		brojiRadne+=al.get(i).getRadniDani();
			    		brojiNeradne+=al.get(i).getPreostaloSlobodnih();
			    		
			    		
			    	}
			    	Object[] objs = {"", "", "Ukupno: ", brojiRadne,brojiNeradne};
			    	model.addRow(objs);
			    	
			    	
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
			}});
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
		frmSolutionsiGodisnji = new JFrame();
		frmSolutionsiGodisnji.setResizable(false);
		frmSolutionsiGodisnji.setTitle("SolutionSI - Godišnji izvjestaj");
		frmSolutionsiGodisnji.setBounds(100, 100, 675, 300);
		frmSolutionsiGodisnji.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsiGodisnji.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 639, 159);
		frmSolutionsiGodisnji.getContentPane().add(scrollPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsiGodisnji.setLocation(dim.width/2-frmSolutionsiGodisnji.getSize().width/2, dim.height/2-frmSolutionsiGodisnji.getSize().height/2);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", null, ""},
				{null, null, null, null, null},
				{null, null, "Ukupno:", null, "10"},
			},
			new String[] {
				"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani"
			}
		));
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 11, 59, 22);
		frmSolutionsiGodisnji.getContentPane().add(lblSektor);
		
		cbSektori = new JComboBox();
		cbSektori.setBounds(57, 12, 106, 20);
		frmSolutionsiGodisnji.getContentPane().add(cbSektori);
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(205, 11, 67, 22);
		frmSolutionsiGodisnji.getContentPane().add(lblGodina);
		
		godina = new JYearChooser();
		godina.setBounds(268, 13, 67, 20);
		frmSolutionsiGodisnji.getContentPane().add(godina);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiGodisnji.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		mntmUputstvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("https://github.com/SoftverInzenjeringETFSA/SI2015Tim2/blob/master/Documents/User%20Interface%20v2.0.pdf");
					  desktop.browse(oURL);
					} catch (Exception e) {
						
						logger.error(e);
					
					}
			}
			});
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogout = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogout);
	}
}
