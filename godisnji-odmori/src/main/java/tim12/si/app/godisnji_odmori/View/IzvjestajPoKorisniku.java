package tim12.si.app.godisnji_odmori.View;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import tim12.si.app.godisnji_odmori.Controller.KalendarController;
import tim12.si.app.godisnji_odmori.Controller.OdsustvoController;
import tim12.si.app.godisnji_odmori.Controller.SektorController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class IzvjestajPoKorisniku {

	private JFrame frmSolutionsiIzvjetaj;
	private JTable table;
	private JComboBox comboBox;
	private JDialog frame;
	final static Logger logger = Logger.getLogger(IzvjestajPoKorisniku.class);
	/**
	 * Launch the application.
	 */
	public static void IzvjestajPoKorisniku() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajPoKorisniku window = new IzvjestajPoKorisniku();
					window.frmSolutionsiIzvjetaj.setVisible(true);
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
	public IzvjestajPoKorisniku() {
		
		initialize();
		frmSolutionsiIzvjetaj.setVisible(true);
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
			
			comboBox.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
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
			    		
			    		Object[] objs = {(String)comboBox.getSelectedItem(), al.get(i).getZaposlenikIme(), al.get(i).getZaposlenikPrezime(), al.get(i).getRadniDani(), al.get(i).getPreostaloSlobodnih(), al.get(i).getIskoristeniGodisnji() };
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
		frmSolutionsiIzvjetaj = new JFrame();
		frmSolutionsiIzvjetaj.setResizable(false);
		frmSolutionsiIzvjetaj.setTitle("SolutionSI - Izvje\u0161taj po zaposlenicima");
		frmSolutionsiIzvjetaj.setBounds(100, 100, 680, 322);
		frmSolutionsiIzvjetaj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsiIzvjetaj.getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsiIzvjetaj.setLocation(dim.width/2-frmSolutionsiIzvjetaj.getSize().width/2, dim.height/2-frmSolutionsiIzvjetaj.getSize().height/2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 644, 155);
		frmSolutionsiIzvjetaj.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Ekonomski", "Haso", "Hasi\u0107", "125", "15", "10"},
				{"Ekonomski", "Haso", "Hasi\u0107", "263", "17", "8"},
				{null, null, null, null, null, null},
				{null, null, null, null, "", ""},
			},
			new String[] {
				"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani", "Preostalo slobodno"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(122);
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(10, 11, 59, 22);
		frmSolutionsiIzvjetaj.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setBounds(57, 12, 106, 20);
		frmSolutionsiIzvjetaj.getContentPane().add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiIzvjetaj.setJMenuBar(menuBar);
		
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
					  e.printStackTrace();
					}
			}
			});
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogOut = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogOut);
	}

}
