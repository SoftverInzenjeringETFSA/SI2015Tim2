package tim12.si.app.godisnji_odmori.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.hibernate.Session;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;

import tim12.si.app.godisnji_odmori.Controller.OdsustvoController;
//import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import tim12.si.app.godisnji_odmori.Controller.ZahtjevController;
import tim12.si.app.godisnji_odmori.Controller.ZaposlenikController;
import tim12.si.app.godisnji_odmori.ViewModel.ZahtjevVM;
import tim12.si.app.godisnji_odmori.ViewModel.ZaposlenikBrDana;

import javax.swing.JButton;

public class ZahtjevPregledManagement {

	private JFrame frmSolutionsiZahtjev;
	private JLabel lblHamo;
	private JLabel lblPipa;
	private JLabel lblNewLabel_1; 
	private JLabel label_1;
	private JLabel label_5;
	private JLabel label_8; 
	private JLabel label_9;
	private JLabel label_4;
	private JLabel label_10;
	private JLabel label_11;
	private JCalendar calendar;
	private JLabel lblGodisnjiOdmor;
	private JTextPane txtpnUmoranOdDosade;
	private JDialog frame;
	
	private Long id_zahtjeva;
	private ZaposlenikBrDana zbr;
	private ZahtjevVM zvm;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public ZahtjevPregledManagement(Long id, String sektor, String username) {
		//ManagementMainWindow.Prekini(this);
		initialize();
		Session sess = null;
		this.frmSolutionsiZahtjev.setVisible(true);
		id_zahtjeva = id;
		
		try {
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZaposlenikController zc = new ZaposlenikController(sess);
			zbr = zc.DajZaposlenikViewModelZaZahtjev(username);
			lblHamo.setText(zbr.getZaposlenikIme());
			lblPipa.setText(zbr.getZaposlenikPrezime());
			lblNewLabel_1.setText(zbr.getSektor());
			label_1.setText(zbr.getRadniDani().toString());
			label_5.setText(zbr.getPreostaloSlobodnih().toString());
			label_8.setText(zbr.getDaniBolovanja().toString());
			label_9.setText(zbr.getDaniNeplaniranog().toString());
			label_4.setText(zbr.getIskoristeniGodisnji().toString());
			
			ZahtjevController zcc = new ZahtjevController(sess);
			zvm = zcc.dajZahtjev(id);
			txtpnUmoranOdDosade.setText(zvm.getOpis());
			DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
			String strDate = dateFormat.format(zvm.getPocetakOdsustva());
			String strDate2 = dateFormat.format(zvm.getZavrsetakOdsustva());
			label_10.setText(strDate);
			label_11.setText(strDate2);
			lblGodisnjiOdmor.setText(zvm.getTipOdsustva()); 
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(calendar.getDate());
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			JPanel jpanel = calendar.getDayChooser().getDayPanel();
			Component component[] = jpanel.getComponents();
			
			OdsustvoController oc = new OdsustvoController(sess);
			ArrayList<Date> events = oc.dajSvaOdsustva();
			//System.out.println(events.get(2).toString());
			

			//arraylist of events
			for(int i = 0; i < events.size(); i++)
			{
				Date date= events.get(i);
				Calendar call = Calendar.getInstance();
				call.setTime(date);
				int month1 = call.get(Calendar.MONTH);
				int year1 = call.get(Calendar.YEAR);
				int day1 = call.get(Calendar.DAY_OF_MONTH);
				//selected month and year on JCalendar
			    if(month == month1 && year == year1)
			    {
			         // Calculate the offset of the first day of the month
			         cal.set(Calendar.DAY_OF_MONTH,1);
			         int offset = cal.get(Calendar.DAY_OF_WEEK) - 1;

			        //this value will differ from each month due to first days of each month
			         component[ day1 + offset + 6].setBackground(Color.red); 
			    }
			}
			/*JPanel jpanel = calendar.getDayChooser().getDayPanel();
			Component component[] = jpanel.getComponents();
			component[8].setBackground(Color.red);*/
			
			
			
			
			
			
		}
		catch (Exception er) {

			//System.out.print(er.getMessage());
			if (er.getMessage() != null )
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(frame, "Korisnik sa username: " + username + " ne postoji ili zahtjev sa id-om" + id + " ne postoji",
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
		frmSolutionsiZahtjev = new JFrame();
		frmSolutionsiZahtjev.setTitle("SolutionSI - Zahtjev detalji");
		frmSolutionsiZahtjev.setBounds(100, 100, 840, 407);
		frmSolutionsiZahtjev.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSolutionsiZahtjev.getContentPane().setLayout(null);
		
		frmSolutionsiZahtjev.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	new ManagementMainWindow();
		    	frmSolutionsiZahtjev.dispose();
		        }
		    }
		);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 30, 310, 227);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacije o zaposleniku", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmSolutionsiZahtjev.getContentPane().add(panel);
		
		JLabel lblOdraeniRadniDani = new JLabel("Odrađeni radni dani:");
		lblOdraeniRadniDani.setBounds(10, 102, 129, 14);
		panel.add(lblOdraeniRadniDani);
		
		label_1 = new JLabel("125");
		label_1.setBounds(219, 102, 79, 14);
		panel.add(label_1);
		
		JLabel lblBrojDanaNa = new JLabel("Broj dana na godišnjem odmoru:");
		lblBrojDanaNa.setBounds(10, 177, 198, 14);
		panel.add(lblBrojDanaNa);
		
		JLabel label_3 = new JLabel("Ostalo dana godišnjeg odmora:");
		label_3.setBounds(10, 202, 189, 14);
		panel.add(label_3);
		
		label_4 = new JLabel("10");
		label_4.setBounds(219, 177, 79, 14);
		panel.add(label_4);
		
		label_5 = new JLabel("20");
		label_5.setBounds(219, 202, 79, 14);
		panel.add(label_5);
		
		JLabel lblBrojDaniNa = new JLabel("Broj dana na bolovanju:");
		lblBrojDaniNa.setBounds(10, 127, 153, 14);
		panel.add(lblBrojDaniNa);
		
		JLabel label_7 = new JLabel("Broj dana neplaniranog odsustva:");
		label_7.setBounds(10, 152, 198, 14);
		panel.add(label_7);
		
	    label_8 = new JLabel("0");
		label_8.setBounds(219, 127, 79, 14);
		panel.add(label_8);
		
		label_9 = new JLabel("0");
		label_9.setBounds(219, 152, 79, 14);
		panel.add(label_9);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 27, 46, 14);
		panel.add(lblIme);
		
		lblHamo = new JLabel("Hamo");
		lblHamo.setBounds(219, 27, 79, 14);
		panel.add(lblHamo);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(10, 52, 70, 14);
		panel.add(lblPrezime);
		
		lblPipa = new JLabel("Pipa");
		lblPipa.setBounds(219, 52, 79, 14);
		panel.add(lblPipa);
		
		JLabel lblSektor = new JLabel("Sektor:");
		lblSektor.setBounds(10, 77, 46, 14);
		panel.add(lblSektor);
		
		lblNewLabel_1 = new JLabel("IT");
		lblNewLabel_1.setBounds(219, 77, 79, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Zahtjev informacije", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(332, 30, 237, 227);
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
		
		txtpnUmoranOdDosade = new JTextPane();
		txtpnUmoranOdDosade.setEditable(false);
		txtpnUmoranOdDosade.setBounds(71, 101, 154, 75);
		panel_1.add(txtpnUmoranOdDosade);
		txtpnUmoranOdDosade.setText("Umoran od dosade");
		
		label_10 = new JLabel("20-4-2016");
		label_10.setBounds(68, 22, 157, 14);
		panel_1.add(label_10);
		
		label_11 = new JLabel("30-4-2016");
		label_11.setBounds(68, 47, 157, 14);
		panel_1.add(label_11);
		
		lblGodisnjiOdmor = new JLabel("Godisnji odmor");
		lblGodisnjiOdmor.setBounds(71, 71, 154, 14);
		panel_1.add(lblGodisnjiOdmor);
		
		calendar = new JCalendar();
		calendar.setBounds(581, 33, 233, 261);
		frmSolutionsiZahtjev.getContentPane().add(calendar);
		
		
		
		
		
		
		
		JButton btnOdobri = new JButton("Odobri");
		btnOdobri.setBounds(589, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdobri);
		btnOdobri.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				odobriZahtjev();
			}
		});
		
		JButton btnOdbij = new JButton("Odbij");
		btnOdbij.setBounds(701, 334, 89, 23);
		frmSolutionsiZahtjev.getContentPane().add(btnOdbij);
		btnOdbij.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				odbijZahtjev();
			}
		});
	}
	private void odobriZahtjev()
	{
		Session sess = null;
		try{
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZahtjevController zc = new ZahtjevController(sess);
			int i = zc.odobriZahtjev(id_zahtjeva, zbr, zvm);
			if (i==0) JOptionPane.showMessageDialog(frame,
					"Ne postoji zahjtev sa datim id-om!", "Greška", JOptionPane.ERROR_MESSAGE);
			else {
				JOptionPane.showMessageDialog(frame,
						"Zahtjev uspješno odobren!", "Odobravanje zahtjeva!", JOptionPane.INFORMATION_MESSAGE);
				//mee.dispatchEvent(new WindowEvent(mee, WindowEvent.WINDOW_CLOSING));
				
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			
		} finally {
			if (sess != null)
				sess.close();
		}
	}
	private void odbijZahtjev()
	{
		Session sess = null;
		try{
			sess = tim12.si.app.godisnji_odmori.HibernateUtil.getSessionFactory().openSession();
			ZahtjevController zc = new ZahtjevController(sess);
			int i = zc.odbijZahtjev(id_zahtjeva, zbr, zvm);
			if (i==0) JOptionPane.showMessageDialog(frame,
					"Ne postoji zahjtev sa datim id-om!", "Greška", JOptionPane.ERROR_MESSAGE);
			else {
				JOptionPane.showMessageDialog(frame,
						"Zahtjev uspješno odbijen!", "Odbijanje zahtjeva!", JOptionPane.INFORMATION_MESSAGE);
				//mee.dispatchEvent(new WindowEvent(mee, WindowEvent.WINDOW_CLOSING));
				
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);
			
		} finally {
			if (sess != null)
				sess.close();
		}
	}
}
