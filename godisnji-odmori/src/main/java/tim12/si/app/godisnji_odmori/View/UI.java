package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import tim12.si.app.godisnji_odmori.App;
import tim12.si.app.godisnji_odmori.Singleton;
import tim12.si.app.godisnji_odmori.Controller.PrisustvoController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikAccountVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikVM;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frmLogin;
	private JTextField usernameInput;
	private JButton btnLogin;
	private JLabel lblSolutionsi;
	private JPasswordField passwordInput;
	private JLabel lblSi;
	private JDialog frame;
	private PrisustvoController pC;
	
	Session sess = null;
	private static final Logger logger = Logger.getLogger(UI.class);

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmLogin.setVisible(true);
					App.main(null);
				} catch (Exception e) {
					
					logger.error(e);
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public UI() {
		sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
		initialize();
		pC = new PrisustvoController(sess);
	}
	
	public void UIShow()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}
	
	public void provjeriUserIPass(){
		
		
		try {
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZaposlenikController zc = new ZaposlenikController(sess);
			Singleton.getInstance().setUsername(usernameInput.getText());
			Date danas = new Date();
			if(!pC.provjeriDaLiPostoji(zc.dajIdPoUsernamuBaza(Singleton.getInstance().getUsername()),danas))		
			pC.evidentirajPrisustvo(zc.dajIdPoUsernamuBaza(Singleton.getInstance().getUsername()),danas);
			 ZaposlenikAccountVM acc = zc.DajZaposlenikAccVM(Singleton.getInstance().getUsername(), passwordInput.getText());
			 
			 if(acc.getPrivilegija()==true)
			 {
			
				 //otvori manager formu
				 ManagementMainWindow mw = new ManagementMainWindow();
				 mw.Management();
			 }
			 else
			 {
				 //otvori user formu
				 UserMainWindow uw = new UserMainWindow();
				 //ZaposlenikVM zvm= zc.DajZaposlenikoveInformacije(acc.getUsername());
				 uw.User();
				 
				 
			 }
			 frmLogin.dispose();
			 
		}
		catch (Exception er) {

			logger.error(er);
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);

		} finally {
			if (sess != null)
				sess.close();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmLogin.getContentPane().setBackground(SystemColor.scrollbar);
		frmLogin.setBackground(Color.LIGHT_GRAY);
		frmLogin.setBounds(100, 100, 325, 254);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocation(dim.width/2-frmLogin.getSize().width/2, dim.height/2-frmLogin.getSize().height/2);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel.setBounds(55, 80, 59, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		usernameInput = new JTextField();
		usernameInput.setBounds(133, 77, 86, 20);
		frmLogin.getContentPane().add(usernameInput);
		usernameInput.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblPassword.setBounds(55, 121, 59, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				provjeriUserIPass();
			}
		});
		
		
		btnLogin.setBounds(183, 170, 89, 23);
		frmLogin.getContentPane().add(btnLogin);
		SwingUtilities.getRootPane(btnLogin).setDefaultButton(btnLogin);
		lblSolutionsi = new JLabel("Solution");
		lblSolutionsi.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 40));
		lblSolutionsi.setForeground(new Color(128, 0, 0));
		lblSolutionsi.setBounds(49, 11, 155, 50);
		frmLogin.getContentPane().add(lblSolutionsi);
		
		passwordInput = new JPasswordField();
		passwordInput.setBounds(133, 118, 86, 20);
		frmLogin.getContentPane().add(passwordInput);
		
		lblSi = new JLabel("SI");
		lblSi.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 40));
		lblSi.setForeground(Color.RED);
		lblSi.setBounds(200, 14, 59, 45);
		frmLogin.getContentPane().add(lblSi);

	}
}
