package com.easy.easypaywebgateway.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat DateTimeFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String DateTimeFormaterArray[] = new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd" };

	/**
	 * 将日期格式化成yyyyMMddHHmmss的格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate1(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	/**
	 * 将当前日期格式化成yyyyMMddHHmmss的格式
	 * 
	 * @return
	 */
	public static String formatDate1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * 将日期格式化成yyyy-MM-dd HH:mm:ss的格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate2(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 将当前日期格式化成yyyy-MM-dd HH:mm:ss的格式
	 * 
	 * @return
	 */
	public static String formatDate2() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 与系统时间相差几个小时
	 * 
	 * @param startTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param timeNum
	 *            12/24/48/....个小时
	 * @return
	 */
	public static boolean subtractionEndTime(String startTime, int timeNum) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(sdf.format(new Date()));
		long cha = end.getTime() - start.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		if (result <= timeNum) {
			// System.out.println("可用");
			return true;
		} else {
			// System.out.println("已过期");
			return false;
		}
	}

	/**
	 * 将日期格式化成yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate3(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 将当前日期格式化成yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * 获取当前8位日期格式yyyyMMdd
	 * 
	 * @return
	 */
	public static String format8Date() {
		return formatDate1().substring(0, 8);
	}

	/**
	 * 获得当前月的第一天，格式为yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		String today = format8Date();
		String month = today.substring(0, 6);
		return month + "01";

	}

	/**
	 * 获取当前6位日期格式HHmmss
	 * 
	 * @return
	 */
	public static String format6Time() {
		return formatDate1().substring(8);
	}

	/**
	 * 改变时间字符串的格式，便于写入数据库 传入格式为yyyyMMddHHssmm 返回格式为yyyy-MM-dd HH:ss:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateStr(String date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ret = "";
		try {
			Date tempDate = (Date) format1.parseObject(date);
			ret = format2.format(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String formatDateStr3(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ret = "";
		try {
			Date tempDate = format.parse(date); 
			ret = format.format(tempDate); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String formatDateStr4(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		String ret = "";
		try {
			Date tempDate = format.parse(date); 
			ret = format.format(tempDate); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	/**
	 * 改变时间字符串的格式，便于写入数据库 传入格式为 yyyy-MM-dd HH:ss:mm 返回格式为 yyyyMMddHHssmm
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateStr2(String date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String ret = "";
		try {
			Date tempDate = (Date) format1.parseObject(date);
			ret = format2.format(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 返回字符串日期
	public static String formatDateTimeToString(String ymd, Date datetime) {
		// 格式化当前时间
		SimpleDateFormat isNow = new SimpleDateFormat(ymd);
		String now = "";
		try {
			now = isNow.format(datetime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 将当前日期格式化成yyyy-MM-dd的格式用于生成日报
	 * 
	 * @return
	 */
	public static String formatReportDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ret = format.format(new Date());
		return ret;
	}

	/**
	 * 将当前日期格式化成yyyy-MM-dd的格式用于生成日报
	 * 
	 * @return
	 */
	public static String formatReportMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String ret = format.format(new Date());
		return ret;
	}

	/**
	 * 获取当前时间的Timestamp，用于操作数据库
	 * 
	 * @return
	 */
	public static Timestamp getTimestap() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 计算传入的时间戳距今天多少天 例如，今天是2012-4-20 传入时间为2012-4-19 00:00:00，返回值为-1
	 * 传入时间为2012-4-21 00:00:00 返回为1
	 * 
	 * @param date
	 *            格式：2012-01-01 4位年份，2位月份，2位日期
	 * @return
	 */
	public static int daysAgoToday(String date) {
		int day = (int) (getTimestap(date + " 00:00:00").getTime() / (1000 * 60 * 60 * 24));
		int today = (int) (getTimestap(formatDate3() + " 00:00:00").getTime() / (1000 * 60 * 60 * 24));
		return day - today;
	}

	/**
	 * 得到当前时间的前一个月的年月日时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastMonthTime() {
		long oneDayValue = 24 * 60 * 60 * 1000;
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(start.getTimeInMillis() - oneDayValue * 30);
		// start.add(Calendar.MONTH, -1);
		int year = start.get(Calendar.YEAR);
		int month = start.get(Calendar.MONTH) + 1;
		int date = start.get(Calendar.DATE);
		int hour = start.get(Calendar.HOUR_OF_DAY);
		int minute = start.get(Calendar.MINUTE);
		int second = start.get(Calendar.SECOND);
		return year + "-" + (month < 10 ? "0" + month : month) + "-" + (date < 10 ? "0" + date : date) + " "
				+ (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second);
	}

	/**
	 * 得到当前时间的前一个月
	 * 
	 * @param args
	 */
	public static String ulTimoToString() {
		long oneDayValue = 24 * 60 * 60 * 1000;
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(start.getTimeInMillis() - oneDayValue * 30);
		String lastMonth = start.get(Calendar.YEAR)
				+ "-"
				+ ((start.get(Calendar.MONTH) + 1) < 10 ? "0" + (start.get(Calendar.MONTH) + 1) : ""
						+ (start.get(Calendar.MONTH) + 1)) + "-"
				+ (start.get(Calendar.DATE) < 10 ? "0" + start.get(Calendar.DATE) : start.get(Calendar.DATE));
		return lastMonth;
	}

	/**
	 * 将日期时间格式化成yyyyMMddHHmmss的格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Timestamp ts) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(ts);
	}

	/**
	 * 将日期时间格式化成yyyy-MM-dd的格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime2(Timestamp ts) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(ts);
	}

	/**
	 * 转换成时间字符串
	 * 
	 * @return
	 */
	public static String formatDateTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(getTimestap());
	}

	/**
	 * 转换成时间字符串
	 * 
	 * @return
	 */
	public static String formatDateTimeStr2() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(getTimestap());
	}

	/**
	 * 字符串转timestamp
	 * 
	 * @param datetime
	 * @return
	 * @throws ParseException
	 */
	public static final Timestamp parserTimestamp(String datetime) throws ParseException {
		Timestamp ts = null;
		@SuppressWarnings("unused")
		SimpleDateFormat sf = null;
		for (int i = 0; i < DateTimeFormaterArray.length; i++) {
			ts = convertDate(DateTimeFormaterArray[i], datetime);
			if (ts != null)
				break;
		}
		return ts;
	}

	public static final Timestamp convertDate(String format, String source) {
		try {
			Timestamp t = new Timestamp(new SimpleDateFormat(format).parse(source).getTime());
			return t;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日期字符串转timestamp
	 * 
	 * @param datetime
	 * @return
	 * @throws ParseException
	 */
	public static final Timestamp dateStrCovTimestamp(String date) {
		date += " 00:00:00";
		Timestamp ts = null;
		try {
			ts = new Timestamp(DateTimeFormater.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static Timestamp getTimestap(String date) {
		return Timestamp.valueOf(date);
	}

	public static String getMyTimeStamp(String tempTimes) {
		long tempTimeLong = Long.valueOf(tempTimes).intValue(); // 将数字转化成long型
		long ss = (tempTimeLong - 70 * 365 - 17 - 2) * 24 * 3600 * 1000;
		Date dss = new Date(ss);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		String str = dateFormat.format(dss);
		return str;
	}

	/**
	 * 获取系统当前时间的上几天/周/月/年或者以后几天/周/月/年的时间
	 * 
	 * @param type
	 *            ：1-天 2-周 3-月 4-年
	 * @param count
	 *            ：-1-上一、1-下一、-2-上二、2-下二...
	 * @param timeType
	 *            ：返回的时间样式，如：yyyy-MM-dd hh:mm:ss
	 */
	public static String getSomeTimes(int type, int count, String timeType) {
		String resultTimeStr = "";
		Date date = null;
		Calendar curr = Calendar.getInstance();
		switch (type) {
		case 1:
			curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + count);
			date = curr.getTime();
			break;
		case 2:
			curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + 7 * count);
			date = curr.getTime();
			break;
		case 3:
			curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + count);
			date = curr.getTime();
			break;
		case 4:
			curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + count);
			date = curr.getTime();
			break;
		default:
			break;
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat(timeType);
		resultTimeStr = timeFormat.format(date);
		return resultTimeStr;
	}

}
