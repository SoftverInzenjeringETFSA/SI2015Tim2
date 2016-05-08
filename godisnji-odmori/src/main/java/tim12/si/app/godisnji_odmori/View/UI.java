package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class UI {

	private JFrame frmLogin;
	private JTextField textField;
	private JButton btnLogin;
	private JLabel lblSolutionsi;
	private JPasswordField passwordField;
	private JLabel lblSi;
	//kako bi znali koji je user logovan
	private static String username;
	
	
	public static String DajUsername()
	{
		return username;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmLogin.getContentPane().setBackground(SystemColor.scrollbar);
		frmLogin.setBackground(Color.LIGHT_GRAY);
		frmLogin.setBounds(100, 100, 325, 254);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel.setBounds(55, 80, 59, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(133, 77, 86, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblPassword.setBounds(55, 121, 59, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(183, 170, 89, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		lblSolutionsi = new JLabel("Solution");
		lblSolutionsi.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 40));
		lblSolutionsi.setForeground(new Color(128, 0, 0));
		lblSolutionsi.setBounds(49, 11, 155, 50);
		frmLogin.getContentPane().add(lblSolutionsi);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 118, 86, 20);
		frmLogin.getContentPane().add(passwordField);
		
		lblSi = new JLabel("SI");
		lblSi.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 40));
		lblSi.setForeground(Color.RED);
		lblSi.setBounds(200, 14, 59, 45);
		frmLogin.getContentPane().add(lblSi);

	}
}
