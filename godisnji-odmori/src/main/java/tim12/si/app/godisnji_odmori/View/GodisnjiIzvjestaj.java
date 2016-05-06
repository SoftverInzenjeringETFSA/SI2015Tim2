package tim12.si.app.godisnji_odmori.View;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JYearChooser;

public class GodisnjiIzvjestaj {

	private JFrame frmSolutionsiGodisnji;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GodisnjiIzvjestaj window = new GodisnjiIzvjestaj();
					window.frmSolutionsiGodisnji.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GodisnjiIzvjestaj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiGodisnji = new JFrame();
		frmSolutionsiGodisnji.setTitle("SolutionSI - Godišnji izvjestaj");
		frmSolutionsiGodisnji.setBounds(100, 100, 675, 300);
		frmSolutionsiGodisnji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsiGodisnji.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 639, 159);
		frmSolutionsiGodisnji.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"IT", "Haso", "Hasi\u0107", "125", "10"},
				{null, null, null, null, null},
				{null, null, "Ukupno:", "125", "10"},
			},
			new String[] {
				"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani"
			}
		));
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 11, 59, 22);
		frmSolutionsiGodisnji.getContentPane().add(lblSektor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(57, 12, 106, 20);
		frmSolutionsiGodisnji.getContentPane().add(comboBox);
		
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(205, 11, 67, 22);
		frmSolutionsiGodisnji.getContentPane().add(lblGodina);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(268, 13, 67, 20);
		frmSolutionsiGodisnji.getContentPane().add(yearChooser);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiGodisnji.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUputstvo = new JMenuItem("Uputstvo");
		mnHelp.add(mntmUputstvo);
		
		JMenu mnOdjava = new JMenu("Odjava");
		menuBar.add(mnOdjava);
		
		JMenuItem mntmLogout = new JMenuItem("Odjavi se");
		mnOdjava.add(mntmLogout);
	}
}
