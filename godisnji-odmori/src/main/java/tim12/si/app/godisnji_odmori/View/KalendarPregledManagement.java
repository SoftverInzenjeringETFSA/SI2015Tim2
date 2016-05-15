package tim12.si.app.godisnji_odmori.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;


import javax.swing.JFrame;


import com.toedter.calendar.JCalendar;
import org.apache.log4j.Logger;

import tim12.si.app.godisnji_odmori.Controller.KalendarController;
import tim12.si.app.godisnji_odmori.Controller.OdsustvoController;
import tim12.si.app.godisnji_odmori.Controller.SektorController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hibernate.Session;
import org.hibernate.boot.model.IdGeneratorStrategyInterpreter.GeneratorNameDeterminationContext;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class KalendarPregledManagement {

	private JFrame frmZauzetiTerminiPo;
	private JDialog frame;
	private JComboBox combobox;
	private JCalendar calendar;
	private ArrayList<Date> events;
	private JLabel lblNewLabel;
	private JSpinner js;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public KalendarPregledManagement() {
		initialize();
		frmZauzetiTerminiPo.setVisible(true);
		
		
			try{
			SektorController sc = new SektorController();
			String sektori[] = sc.dajSveSektore();
			combobox.setModel(new DefaultComboBoxModel(sektori));
			combobox.setSelectedIndex(-1);
			final Color c = calendar.getDayChooser().getDayPanel().getComponent(20).getBackground();
			
			
			
			combobox.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	Session sess = null;
			    	try {
			    	sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			    	OdsustvoController oc = new OdsustvoController(sess);
			    	KalendarController kc = new KalendarController();
			    	
			    	events = oc.dajSvaOdsustva((String)combobox.getSelectedItem());
			    	calendar.getDayChooser().setEnabled(true);
			    	
			    	JPanel jpanel = calendar.getDayChooser().getDayPanel();
					Component component[] = jpanel.getComponents();
					
					//for(int i=0;i<component.length;i++)component[i].setBackground(c);
			    	
		            kc.postaviZauzete(events, calendar); 
			    }
			    	catch (Exception er) {

			    		Logger.getLogger(KalendarPregledManagement.class).error(er.getMessage());
						JOptionPane.showMessageDialog(frame, er.getMessage(),
								"Greška", JOptionPane.INFORMATION_MESSAGE);
						

					} finally {
						if (sess != null)
							sess.close();
					}
			}});
			JComboBox jcb = (JComboBox) calendar.getMonthChooser().getComboBox(); 
			jcb.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	KalendarController kc = new KalendarController();
		            kc.postaviZauzete(events, calendar);
			    }
			});
			
			
			
			
			js = (JSpinner) calendar.getYearChooser().getSpinner();
			
			lblNewLabel = new JLabel("*ukoliko mijenjate godinu, selektujte ponovo mjesec kako bi se učitali podaci");
			lblNewLabel.setForeground(Color.GRAY);
			lblNewLabel.setBounds(43, 324, 498, 16);
			frmZauzetiTerminiPo.getContentPane().add(lblNewLabel);
			
			
			js.addChangeListener(new ChangeListener() {
				
				
		        public void stateChanged(ChangeEvent e) {
		        	
		            
		        	Session sess = null;
			    	try {
			    		
			    		String novaGodina = js.getValue().toString();
				    	sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();

			        	OdsustvoController oc = new OdsustvoController(sess);
			        	events = oc.dajSvaOdsustva((String)combobox.getSelectedItem());
			        	KalendarController kc = new KalendarController();
			            kc.postaviZauzeteNovaGodina(events, calendar, novaGodina); 
			    }
			    	catch (Exception er) {

						
						JOptionPane.showMessageDialog(frame, er.getMessage(),
								"Greška", JOptionPane.INFORMATION_MESSAGE);
						

					} finally {
						if (sess != null)
							sess.close();
					}
		        }
		    });
			
		}
		
		catch (Exception er) {

			
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			

		} finally {
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZauzetiTerminiPo = new JFrame();
		frmZauzetiTerminiPo.setTitle("SolutionSI - Zauzeti termini po sektorima");
		frmZauzetiTerminiPo.setBounds(100, 100, 595, 400);
		frmZauzetiTerminiPo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmZauzetiTerminiPo.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmZauzetiTerminiPo.setLocation(dim.width/2-frmZauzetiTerminiPo.getSize().width/2, dim.height/2-frmZauzetiTerminiPo.getSize().height/2);
		
		
		calendar = new JCalendar();
		calendar.setBounds(43, 88, 498, 223);
		frmZauzetiTerminiPo.getContentPane().add(calendar);
		calendar.getMonthChooser().getSpinner().setEnabled(false);
		calendar.getMonthChooser().getComboBox().setEnabled(true);
		
		JLabel lblZauzetiTerminiPo = new JLabel("Zauzeti termini po sektorima:");
		lblZauzetiTerminiPo.setBounds(43, 25, 181, 32);
		frmZauzetiTerminiPo.getContentPane().add(lblZauzetiTerminiPo);
		
		JLabel label = new JLabel("Sektor:");
		label.setBounds(388, 36, 59, 22);
		frmZauzetiTerminiPo.getContentPane().add(label);
		
		combobox = new JComboBox();
		combobox.setBounds(435, 37, 106, 20);
		frmZauzetiTerminiPo.getContentPane().add(combobox);
	}
}
