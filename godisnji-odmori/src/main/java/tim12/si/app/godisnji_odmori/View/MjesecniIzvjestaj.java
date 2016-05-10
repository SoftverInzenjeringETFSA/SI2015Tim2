package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.toedter.calendar.JMonthChooser;

public class MjesecniIzvjestaj {

	private JFrame frmSolutionsiMjesecni;
	private JTable table;

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
}
