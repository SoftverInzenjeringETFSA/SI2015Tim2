package tim12.si.app.godisnji_odmori.View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class IzvjestajPoKorisniku {

	private JFrame frmSolutionsiIzvjetaj;
	private JTable table;
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
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmSolutionsiIzvjetaj.setLocation(dim.width/2-frmSolutionsiIzvjetaj.getSize().width/2, dim.height/2-frmSolutionsiIzvjetaj.getSize().height/2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 644, 155);
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
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(10, 11, 59, 22);
		frmSolutionsiIzvjetaj.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(57, 12, 106, 20);
		frmSolutionsiIzvjetaj.getContentPane().add(comboBox);
		
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
