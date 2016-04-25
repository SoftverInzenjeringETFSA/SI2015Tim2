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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 712, 178);
		frmSolutionsiMjesecni.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, "Ukupno:", null, null},
			},
			new String[] {
				"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani", "Ukupno radni", "Ukupno neradni"
			}
		));
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(167);
		scrollPane.setViewportView(table);
		
		JComboBox mjesecCB = new JComboBox();
		mjesecCB.setBounds(10, 11, 91, 20);
		frmSolutionsiMjesecni.getContentPane().add(mjesecCB);
		
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
