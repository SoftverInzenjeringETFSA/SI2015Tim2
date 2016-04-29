package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class KalendarPregledManagement {

	private JFrame frmZauzetiTerminiPo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KalendarPregledManagement window = new KalendarPregledManagement();
					window.frmZauzetiTerminiPo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KalendarPregledManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZauzetiTerminiPo = new JFrame();
		frmZauzetiTerminiPo.setTitle("SolutionSI - Zauzeti termini po sektorima");
		frmZauzetiTerminiPo.setBounds(100, 100, 595, 376);
		frmZauzetiTerminiPo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZauzetiTerminiPo.getContentPane().setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(43, 88, 498, 223);
		frmZauzetiTerminiPo.getContentPane().add(calendar);
		
		JLabel lblZauzetiTerminiPo = new JLabel("Zauzeti termini po sektorima:");
		lblZauzetiTerminiPo.setBounds(43, 25, 181, 32);
		frmZauzetiTerminiPo.getContentPane().add(lblZauzetiTerminiPo);
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(388, 36, 59, 22);
		frmZauzetiTerminiPo.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(435, 37, 106, 20);
		frmZauzetiTerminiPo.getContentPane().add(comboBox);
	}
}
