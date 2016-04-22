package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MjesecniIzvjestaj {

	private JFrame frmSolutionsiMjesecni;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frmSolutionsiMjesecni.setTitle("SolutionSI - Mjesecni izvjestaj");
		frmSolutionsiMjesecni.setBounds(100, 100, 748, 306);
		frmSolutionsiMjesecni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsiMjesecni.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani", "Ukupno radni ", "Ukupno neradni"},
				{"Ekonomski", "Haso", "Hasi\u0107", "125", "10", "125", "10"},
				{"IT", "Hanuma", "Rami\u0107", "253", "25", "253", "25"},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, "Ukupno:", "378", "35"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(10, 11, 729, 240);
		frmSolutionsiMjesecni.getContentPane().add(table);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiMjesecni.setJMenuBar(menuBar);
		
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
