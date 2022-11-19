package com.allen.dayup.arithmetic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static String getCurrentTime(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
	
	public static String parseDateToString(String dateFormat,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	public static String parseDateToString(String dateFormat, LocalDateTime date) {
		return DateTimeFormatter.ofPattern(dateFormat).format(date);
	}
	
	/**
	 * 获取当前是周几
	 * @return
	 */
	public static int getWeekDay() {
		Date date = Date.from(LocalDateTime.now().atZone( ZoneId.systemDefault()).toInstant());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static String getCurrentTimeWithoutDayTimeUnit() {
		return DateTimeUtils.parseDateToString("HHmm",new Date());
	}
	
	/**
	 * 根据当前时间设置时间偏移量(单位分钟)
	 * @param offset
	 * @return
	 */
	public static String getTimeWithOffset(String format,int offset) {
		Date date = new Date();
		Date newDate = new Date(date.getTime()+ TimeUnit.MINUTES.toMillis(offset));
		return DateTimeUtils.parseDateToString(format,newDate);
	}
	
	/**
	 * 设置时间偏移量(单位分钟)
	 * @param offset
	 * @return
	 * @throws ParseException 
	 */
	public static int getTimeWithOffset(String srcDate,String format,int offset) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(srcDate);
		Date newDate = new Date(date.getTime()+TimeUnit.MINUTES.toMillis(offset));
		return Integer.parseInt(DateTimeUtils.parseDateToString(format,newDate));
	}
	
	/**
	 * 将long类型的时间戳转换为指定格式的时间
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static String transferNumberToDate(Long timeStamp,String dateFormat) {
		try {
			Date date = new Date(timeStamp);
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
