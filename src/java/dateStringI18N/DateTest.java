package dateStringI18N;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest{
	
	public static void main(String[] arg){
		Date d1 = new Date(1_000_000_000_000l);
		System.out.println("1st date: " + d1.toString());

		Calendar c = Calendar.getInstance();
		System.out.println("2nd date: " + c.getTime());
		c.setTime(d1);


		if ( Calendar.SUNDAY == c.getFirstDayOfWeek() ){
			System.out.println("Sunday is the first day of the week");
		}
		
		// Find out the day of the week that the time falls on
		System.out.println("Trillionth milli day of week is " + c.get(Calendar.DAY_OF_WEEK) );
		
		// get the time that is 1 month later
		c.add(Calendar.MONTH, 1);
		Date d2 = c.getTime();
		System.out.println("new Date: " + d2.toString());




		// Date Time zone format example
		//https://stackoverflow.com/questions/21349475/calendar-getinstancetimezone-gettimezoneutc-is-not-returning-utc-time

		System.out.println(System.lineSeparator());
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
		Calendar calendar = Calendar.getInstance(timeZone, Locale.PRC);  // UTC + 8
		//31 DEC 1971 is UTC + 8 Time
		calendar.set(1971, Calendar.DECEMBER,31, 10, 0, 0);
		// date1 is UTC + 0 Time
		Date date1 = calendar.getTime();
		// the output time is date1 in locale time zone (AMS:UTC+1)
		System.out.println("locate time : " + calendar.getTime());


		timeZone = TimeZone.getTimeZone("UTC");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy");
		simpleDateFormat.setTimeZone(timeZone);
		// the output is date1 in UTC + 0 time
		System.out.println("UTC time    : " + simpleDateFormat.format(calendar.getTime()));
	}
}