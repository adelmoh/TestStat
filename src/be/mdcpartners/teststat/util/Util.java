package be.mdcpartners.teststat.util;

import java.util.Calendar;
import java.util.Date;

public class Util {
	public static int caclPeriodYear(int periodDef) {
		if (periodDef < 50) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.YEAR, periodDef);
			return calendar.get(Calendar.YEAR);
		}
		return periodDef;
	}

	public static int caclPeriodYearMonth(int periodDef) {
		if (periodDef < 5000) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, periodDef % 100);
			calendar.add(Calendar.YEAR, periodDef / 100);
			return yearMonth(calendar.getTime());
		}
		return periodDef;
	}

	public static int year(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	public static int yearMonth(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR) + 100 * calendar.get(Calendar.MONTH);
	}
}
