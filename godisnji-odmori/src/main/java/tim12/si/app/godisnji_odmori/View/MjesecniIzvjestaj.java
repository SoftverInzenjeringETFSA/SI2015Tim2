package tim12.si.app.godisnji_odmori.View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import tim12.si.app.godisnji_odmori.ViewModel.IzvjestajZapVM;

public class MjesecniIzvjestaj {

	private JFrame frmSolutionsiMjesecni;
	private JTable table;
	private JComboBox comboBox;
	private JDialog frame;
	private static final Logger logger = Logger.getLogger(MjesecniIzvjestaj.class);

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MjesecniIzvjestaj() {
		initialize();
		OsvjeziComboBox(false);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiMjesecni = new JFrame();
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, "", null},
				{null, null, null, null, ""},
				{null, null, null, null, null},
				{null, "", "Ukupno:", "", null},
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
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(10, 11, 59, 22);
		frmSolutionsiMjesecni.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(57, 12, 106, 20);
		comboBox.setEditable(true);
		JTextComponent jtc = (JTextComponent) comboBox.getEditor().getEditorComponent();
		jtc.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						OsvjeziComboBox(true);
					}
				});
			}
			
			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						OsvjeziComboBox(true);
					}
				});
			}
			
			public void changedUpdate(DocumentEvent e) {
				//This never happens
			}
		});
		frmSolutionsiMjesecni.getContentPane().add(comboBox);
		
		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(206, 12, 59, 20);
		frmSolutionsiMjesecni.getContentPane().add(lblMjesec);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(261, 11, 117, 22);
		frmSolutionsiMjesecni.getContentPane().add(monthChooser);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiMjesecni.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogOut = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogOut);
	}
	
	private void OsvjeziComboBox(boolean expand) {
		Session sess = null;
		
		try {
			if ( comboBox.getEditor().getItem() instanceof String){
				
				sess = HibernateUtil.getSessionFactory().openSession();
				IzvjestajController icont= new IzvjestajController (sess);
				
				String ime = (String) comboBox.getEditor().getItem();
				

				ArrayList<IzvjestajZapVM> data = icont.nadjiPoImenu(ime);
				Object[] array = new Object[data.size() + 1];
				array[0] = ime;
				
				for(int i = 0; i < data.size(); i++){
					array[i+1] = data.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(array);
				comboBox.setModel(model);
				
				if(expand)
					comboBox.getUI().setPopupVisible(comboBox, true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
		
	}
}
