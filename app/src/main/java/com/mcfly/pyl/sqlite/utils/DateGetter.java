package com.mcfly.pyl.sqlite.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class DateGetter 
{
	private static DateGetter instance;
	
	public static DateGetter getInstance() {
		if(instance==null) {
			instance = new DateGetter();
		}
		return instance;
	}
	
	private DateGetter() {
		
	}

	/**
	 * format : yyyy-MM-dd HH:mm:ss
	 * @param value
	 * @return
	 */
	public Date getDateFromString(String value) {
		Date date = null;
		if(value!=null && !value.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				date = sdf.parse(value);
			} catch (ParseException e) {
				Log.e("DateGetter", "Error while getting date",e);
			}
		}
		return date;
	}
	
	public Date getDateFromStringWithoutHours(String value) {
		Date date = null;
		if(value!=null && !value.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				date = sdf.parse(value);
			} catch (ParseException e) {
				Log.e("DateGetter", "Error while getting date",e);
			}
		}
		return date;
	}
	
	/**
	 * format : yyyy-MM-dd H:m:s
	 * @param date
	 * @return
	 */
	public static String getStringFromDate(Date date) {
		String result="";
		if(date!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			result = format.format(date);
		}
		return result;
	}
	
	/**
	 * format : "yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getDayStringFromDate(Date date) {
		String result="";
		if(date!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(date);
		}
		return result;
	}
	
	
	/**
	 * For CLin Form 
	 * @param date
	 * @return
	 */
	public static String getStringFromDate_(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String t = format.format(date);
		return t;
	}
	
	/**
	 * For CLin Form
	 * @param value
	 * @return
	 */
	public static Date getCFDateFromString_(String value) {
		Date date = null;
		if(value!=null && !value.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				date = sdf.parse(value);
			} catch (ParseException e) {
				Log.e("DateGetter", "Error while getting date",e);
			}
		}
		return date;
	}
	
	
}
