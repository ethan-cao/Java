package dateStringI18N;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatTest {

	public static void main(String[] args) {
		localeTest();

		formatAndParsr();
	}
	
	public static void formatAndParsr(){
		Date d1 = new Date(1000000000L);
		System.out.println("d1 = " + d1.toString());

		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		
		String s = df.format(d1);
		System.out.println(s);
		
		try{
			Date d2 = df.parse(s);
			System.out.println("parsed : " + d2.toString());
		} catch (ParseException pe){
			System.out.println("ParseException");
		}
	}

	public static void localeTest(){
		Calendar c = Calendar.getInstance();
		c.set(2010, 11, 14);
		Date d1 = c.getTime();
		
		Locale localeIT = new Locale("it", "IT");
		Locale localePT = new Locale("pt");
		Locale localeBR = new Locale("pt", "BR");

		Locale localeFR = new Locale("fr_FR");
		
		DateFormat df = DateFormat.getInstance();
		System.out.println("current : " + df.format(d1));
		
		DateFormat dfFull = DateFormat.getDateInstance(DateFormat.FULL);
		System.out.println("current : " + dfFull.format(d1));

		DateFormat dfFullIT = DateFormat.getDateInstance(DateFormat.FULL, localeIT);
		System.out.println("current : " + dfFullIT.format(d1));
		
		DateFormat dfDateTime = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG, localeIT);
		System.out.println("current : " + dfDateTime.format(d1));

		System.out.println("locale: " + localeBR.getDisplayCountry() );
		System.out.println("locale: " + localeBR.getDisplayLanguage() );
		System.out.println("locale: " + localePT.getDisplayCountry() );
		System.out.println("locale: " + localePT.getDisplayLanguage() );
		System.out.println("locale: " + localePT.getDisplayLanguage(localeIT) );
		
	}

}
