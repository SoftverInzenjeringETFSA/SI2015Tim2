package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IzvjestajPoKorisniku {

	private JFrame frmSolutionsiIzvjetaj;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajPoKorisniku window = new IzvjestajPoKorisniku();
					window.frmSolutionsiIzvjetaj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajPoKorisniku() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiIzvjetaj = new JFrame();
		frmSolutionsiIzvjetaj.setTitle("SolutionSI - Izvje\u0161taj po zaposlenicima");
		frmSolutionsiIzvjetaj.setBounds(100, 100, 680, 300);
		frmSolutionsiIzvjetaj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSolutionsiIzvjetaj.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani", "Preostalo slobodnih"},
				{"Ekonomski", "Haso", "Hasi\u0107", "125", "15", "10"},
				{"Ekonomski", "Haso", "Hasi\u0107", "263", "17", "8"},
				{null, null, null, null, null, null},
				{null, null, null, null, "", ""},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(10, 11, 644, 80);
		frmSolutionsiIzvjetaj.getContentPane().add(table);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Sektor", "Ime", "Prezime", "Radni dani", "Neradni dani", "Preostalo slobodnih"},
				{"IT", "Ramo", "Rami\u0107", "150", "10", "20"},
				{"IT", "Ramo", null, "253", "20", "10"},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setBounds(10, 107, 644, 80);
		frmSolutionsiIzvjetaj.getContentPane().add(table_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiIzvjetaj.setJMenuBar(menuBar);
		
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
