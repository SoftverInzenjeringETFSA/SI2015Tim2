package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class IzvjestajPoKorisniku {

	private JFrame frmSolutionsiIzvjetaj;
	private JTable table;

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 644, 80);
		frmSolutionsiIzvjetaj.getContentPane().add(scrollPane);
		
		table = new JTable();
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
		
		JMenuBar menuBar = new JMenuBar();
		frmSolutionsiIzvjetaj.setJMenuBar(menuBar);
		
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
