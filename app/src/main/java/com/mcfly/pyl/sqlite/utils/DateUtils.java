package com.mcfly.pyl.sqlite.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import android.content.Context;

public class DateUtils {

	public static Date getUTCDate() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return c.getTime();
	}
	
	public static Date getUTCDate(Date date) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.setTime(date);
		return c.getTime();
	}
	
	public static String getUTCDateAsLocaleString(Context context) {
		DateFormat sdf = android.text.format.DateFormat.getDateFormat(context);
		return sdf.format(getUTCDate());
	}
	
	public static String getDateAsLocaleString(Context context, Date assumingUTCDate) {
		DateFormat sdf = android.text.format.DateFormat.getDateFormat(context);
		return sdf.format(assumingUTCDate);
	}
	
	public static long getShift(Date currentDate) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		long nowInMillis = cal.getTimeInMillis();
		cal.setTime(currentDate);
		long dateInMillis = cal.getTimeInMillis();				
		return nowInMillis - dateInMillis;
	}
	
	public static Date shiftDate(Date currentDate, long shift) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(currentDate);			
		cal.setTimeInMillis(cal.getTimeInMillis() + shift);
		return cal.getTime();
	}
	
}
