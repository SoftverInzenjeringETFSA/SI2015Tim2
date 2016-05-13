package tim12.si.app.godisnji_odmori.Controller;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JCalendar;

public class KalendarController {
	
	public KalendarController(){}
	
	/**
	 * 
	 * @param zaposlenikID
	 */
	public List<Date> dajZauzeteDaneZaZaposlenika(int zaposlenikID) {
		// TODO - implement KalendarController.dajZauzeteDaneZaZaposlenika
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposlenikID
	 */
	public List<Date> prikaziKalendarZaZaposlenika(int zaposlenikID) {
		// TODO - implement KalendarController.prikaziKalendarZaZaposlenika
		throw new UnsupportedOperationException();
	}
	public void postaviZauzete(ArrayList<Date> events, JCalendar calendar)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(calendar.getYearChooser().getYear(),calendar.getMonthChooser().getMonth(),1,0,0);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		JPanel jpanel = calendar.getDayChooser().getDayPanel();
		calendar.getDayChooser().setEnabled(true);
		Component component[] = jpanel.getComponents();
		
		
		
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
		
		
		
		}
}
