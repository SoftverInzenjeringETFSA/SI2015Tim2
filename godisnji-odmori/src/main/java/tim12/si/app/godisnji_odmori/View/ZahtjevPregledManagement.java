package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;

public class ZahtjevPregledManagement {

	private JFrame frmSolutionsiZahtjev;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZahtjevPregledManagement window = new ZahtjevPregledManagement();
					window.frmSolutionsiZahtjev.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ZahtjevPregledManagement() {
		//ManagementMainWindow.Prekini(this);
		initialize();
		this.frmSolutionsiZahtjev.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSolutionsiZahtjev = new JFrame();
		frmSolutionsiZahtjev.setTitle("SolutionSI - Zahtjev detalji");
		frmSolutionsiZahtjev.setBounds(100, 100, 840, 407);
		frmSolutionsiZahtjev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsiZahtjev.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 30, 264, 227);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacije o zaposleniku", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmSolutionsiZahtjev.getContentPane().add(panel);
		
		JLabel label = new JLabel("Broj radnih dana:");
		label.setBounds(10, 102, 102, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("125");
		label_1.setBounds(219, 102, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Iskorišteno dana godišnjeg odmora:");
		label_2.setBounds(10, 177, 198, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Ostalo dana godišnjeg odmora:");
		label_3.setBounds(10, 202, 189, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("10");
		label_4.setBounds(219, 177, 46, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("20");
		label_5.setBounds(219, 202, 46, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Broj dana bolovanja:");
		label_6.setBounds(10, 127, 129, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Broj dana neplaniranog odsustva:");
		label_7.setBounds(10, 152, 189, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("0");
		label_8.setBounds(219, 127, 46, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("0");
		label_9.setBounds(219, 152, 46, 14);
		panel.add(label_9);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 27, 46, 14);
		panel.add(lblIme);
		
		JLabel lblHamo = new JLabel("Hamo");
		lblHamo.setBounds(219, 27, 46, 14);
		panel.add(lblHamo);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(10, 52, 70, 14);
		panel.add(lblPrezime);
		
		JLabel lblPipa = new JLabel("Pipa");
		lblPipa.setBounds(219, 52, 46, 14);
		panel.add(lblPipa);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 77, 46, 14);
		panel.add(lblSektor);
		
		JLabel lblNewLabel_1 = new JLabel("IT");
		lblNewLabel_1.setBounds(219, 77, 46, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Zahtjev informacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(307, 30, 237, 227);
		frmSolutionsiZahtjev.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(10, 22, 46, 14);
		panel_1.add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(10, 47, 46, 14);
		panel_1.add(lblDo);
		
		JLabel lblTip = new JLabel("Tip:");
		lblTip.setBounds(10, 72, 46, 14);
		panel_1.add(lblTip);
		
		JCheckBox chckbxPosjedujeDokumente = new JCheckBox("Posjeduje dokumente");
		chckbxPosjedujeDokumente.setBounds(10, 184, 162, 23);
		panel_1.add(chckbxPosjedujeDokumente);
		
		JLabel lblRazlog = new JLabel("Razlog:");
		lblRazlog.setBounds(10, 97, 46, 14);
		panel_1.add(lblRazlog);
		
		JTextPane txtpnUmoranOdDosade = new JTextPane();
		txtpnUmoranOdDosade.setBounds(71, 101, 162, 75);
		panel_1.add(txtpnUmoranOdDosade);
		txtpnUmoranOdDosade.setText("Umoran od dosade");
		
		JLabel label_10 = new JLabel("20-4-2016");
		label_10.setBounds(66, 22, 68, 14);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("30-4-2016");
		label_11.setBounds(71, 46, 68, 14);
		panel_1.add(label_11);
		
		JLabel lblGodisnjiOdmor = new JLabel("Godisnji odmor");
		lblGodisnjiOdmor.setBounds(71, 71, 101, 14);
		panel_1.add(lblGodisnjiOdmor);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(581, 33, 233, 261);
		frmSolutionsiZahtjev.getContentPane().add(calendar);
		
		JButton btnOdobri = new JButton("Odobri");
		btnOdobri.setBounds(589, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdobri);
		
		JButton btnOdbij = new JButton("Odbij");
		btnOdbij.setBounds(701, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdbij);
	}
}
