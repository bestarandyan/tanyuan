package com.tanyuan.app.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static final String TIMEZONE_CN = "Asia/Shanghai";
    /**
     * ********************SIMPLEFORMATTYPE对应的字串*********************
     */
    /**
     * SIMPLEFORMATTYPESTRING1 对应类型：yyyyMMddHHmmss
     */
    public final static String SIMPLEFORMATTYPESTRING1 = "yyyyMMddHHmmss";

    /**
     * SIMPLEFORMATTYPESTRING2 对应的类型：yyyy-MM-dd HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING3 对应的类型：yyyy-M-d HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING3 = "yyyy-M-d HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING4对应的类型：yyyy-MM-dd HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING4 = "yyyy-MM-dd HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING5 对应的类型：yyyy-M-d HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING5 = "yyyy-M-d HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING6对应的类型：yyyyMMdd
     */
    public final static String SIMPLEFORMATTYPESTRING6 = "yyyyMMdd";

    /**
     * SIMPLEFORMATTYPESTRING7对应的类型：yyyy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING7 = "yyyy-MM-dd";
    public final static String SIMPLEFORMATTYPESTRING22 = "yyyy-MM";

    /**
     * SIMPLEFORMATTYPESTRING8对应的类型： yyyy-M-d
     */
    public final static String SIMPLEFORMATTYPESTRING8 = "yyyy-M-d";

    /**
     * SIMPLEFORMATTYPESTRING9对应的类型：yyyy年MM月dd日
     */
    public final static String SIMPLEFORMATTYPESTRING9 = "yyyy年MM月dd日";

    /**
     * SIMPLEFORMATTYPESTRING10对应的类型：yyyy年M月d日
     */
    public final static String SIMPLEFORMATTYPESTRING10 = "yyyy年M月d日";


    /**
     * SIMPLEFORMATTYPESTRING11对应的类型：M月d日
     */
    public final static String SIMPLEFORMATTYPESTRING11 = "M月d日";

    /**
     * SIMPLEFORMATTYPESTRING12对应的类型：HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING12 = "HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING13对应的类型：HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING13 = "HH:mm";
    /**
     * SIMPLEFORMATTYPESTRING14对应的类型：yyyy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING14 = "yyyy/MM/dd";
    /**
     * SIMPLEFORMATTYPESTRING15对应的类型：MM/dd
     */
    public final static String SIMPLEFORMATTYPESTRING15 = "MM/dd";
    /**
     * SIMPLEFORMATTYPESTRING16对应的类型：MM月dd日
     */
    public final static String SIMPLEFORMATTYPESTRING16 = "MM月dd日";
    /**
     * SIMPLEFORMATTYPESTRING17对应的类型：MM月dd日 HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING17 = "MM月dd日 HH:mm";
    /**
     * SIMPLEFORMATTYPESTRING18对应的类型：yyyy年MM月dd日 HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING18 = "yyyy年MM月dd日 HH:mm";
    public final static String SIMPLEFORMATTYPESTRING21 = "yyyy年MM月";

    /**
     * SIMPLEFORMATTYPESTRING7对应的类型： yy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING19 = "MM-dd";

    /**
     * SIMPLEFORMATTYPESTRING11对应的类型：M月d日
     */
    public final static String SIMPLEFORMATTYPESTRING20 = "MM.dd";


    /**
     * 将时间转换为simpleFormatString对应的格式输出
     *
     * @param calendar
     * @param simpleFormatString
     * @return
     */
    public static String getCalendarStrBySimpleDateFormat(Calendar calendar, String simpleFormatString) {
        String str = "";
        if (!TextUtils.isEmpty(simpleFormatString) && calendar != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(simpleFormatString);
            dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
            str = (dateFormat).format(calendar.getTime());
        }
        return str;
    }

    /**
     * 将时间转换为simpleFormatString对应的格式输出
     *
     * @param time
     * @param simpleFormatString
     * @return
     */
    public static String getCalendarStrBySimpleDateFormat(Long time, String simpleFormatString) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat(simpleFormatString);
        return dateFormat.format(date);
    }

    /**
     * 将时间转换为simpleFormatString对应的格式输出
     *
     * @param simpleFormatString
     * @return
     */
    public static String DateToString(Date date, String simpleFormatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(simpleFormatString);
        return dateFormat.format(date);
    }

    /**
     * 将时间字串#yyyyMMddHHmmss转为 Calendar
     *
     * @param dateStr 小于8位时返回null，不足14位补0
     * @return
     */
    @SuppressWarnings("ResourceType")
    public static Calendar getCalendarByDateTimeStr(String dateStr) {
        if (TextUtils.isEmpty(dateStr) || dateStr.length() < 8)
            return null;
        while (dateStr.length() < 14) {
            dateStr += "0";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        int hour = Integer.parseInt(dateStr.substring(8, 10));
        int min = Integer.parseInt(dateStr.substring(10, 12));
        int second = 0;
        if (dateStr.length() >= 14)
            second = Integer.parseInt(dateStr.substring(12, 14));
        calendar.set(year, month - 1, day, hour, min, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    //把字符串转为日期
    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }


    /**
     * 判断当前日期是星期几
     * //格式如2012-09-08
     * @param pTime
     * @return
     */
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }



        return Week;
    }

    //把字符串转为日期
    public static Date stringToDate(String str,String format) {
        DateFormat dateformat = new SimpleDateFormat(format);
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = dateformat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }

    /**
     * 获取手机系统日期
     *
     * @return
     */
    public static Calendar getCalendarTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
        return calendar;
    }

    /**
     * 获取服务器系统时间
     *
     * @return
     */
    public static Calendar getCurrentTime() {
        return TimeUtil.getCurrentTime();
    }



    private final static long toDay = 1000 * 60 * 60 * 24; // 将毫秒数转换为天
    private final static long toHour = 1000 * 60 * 60; // 将毫秒数转换为小时
    private final static long toMinute = 1000 * 60; // 将毫秒数转换为分钟

    /**
     * 返回两个日期的小时差，异常返回-1；
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long caculateHour(Date date1, Date date2) {

        long result = -1;
        if (date1 != null && date2 != null) {
            long maxDay = date1.getTime() / toHour;
            long minDay = date2.getTime() / toHour;
            result = maxDay > minDay ? maxDay - minDay : minDay - maxDay;
        }
        return result;

    }

 /**
     * 返回两个日期的分钟差，异常返回-1；
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long caculateMinute(Date date1, Date date2) {

        long result = -1;
        if (date1 != null && date2 != null) {
            long maxDay = date1.getTime() / toMinute;
            long minDay = date2.getTime() / toMinute;
            result = maxDay > minDay ? maxDay - minDay : minDay - maxDay;
        }
        return result;

    }


 /**
     * 返回当前时间和目标时间的分钟差，异常返回-1；
     *
     * @param date
     * @return
     */
    public static long caculateMinute(Date date) {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        long result = -1;
        if (date != null && curDate != null) {
            long maxDay = date.getTime() / toMinute;
            long minDay = curDate.getTime() / toMinute;
            result = maxDay > minDay ? maxDay - minDay : minDay - maxDay;
        }
        return result;

    }


    /**
     * 返回两个日期的天数差，同一天返回0
     *
     * @param fromDate
     * @param desDate
     * @return fromDate - desDate
     */
    public static long caculateDay(Date fromDate, Date desDate) {
        try {
            System.out.println("fromDate:" + fromDate.toString());
            System.out.println("desDate:" + desDate.toString());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date from = format.parse(format.format(fromDate));
            Date dest = format.parse(format.format(desDate));
            System.out.println("from:" + from.toString());
            System.out.println("dest:" + dest.toString());
            System.out.println("from.getTime() - dest.getTime():" + (from.getTime() - dest.getTime()));
            System.out.println("from.getTime() - dest.getTime()) / toDay:" + ((from.getTime() - dest.getTime()) / toDay));
            return (from.getTime() - dest.getTime()) / toDay;
        } catch (Exception e) {
            return Long.MAX_VALUE;
        }
    }

    /**
     * 返回目标日期同今天的天数差;
     *
     * @param desDate
     * @return [今天]-desDate
     */
    public static long caculateDayFromToday(Date desDate) {
        return caculateDay(getCurrentTime().getTime(), desDate);
    }

    /**
     * 返回目标日期同今天的天数差，同一天返回true，异常返回false；
     *
     * @param dateA , dateB
     * @return
     */

    public static boolean isSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * /**
     * 返回目标日期同今天的天数差，同一天返回true，异常返回false；
     *
     * @param desDate
     * @return
     */
    public static boolean isToday(Date desDate) {
        return isSameDay(getCurrentTime().getTime(), desDate);
    }

    /**
     * 日期 + 时间
     * 当日     展示： 时:分
     * 非当日   展示：*日*月 时:分
     */
    public static String displayDateAndTime(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            int day = (int) caculateDayFromToday(date); // 天
            switch (day) {
                case 0:
                    break;
                default:
                    SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING11);
                    dateFormart.append(sdf.format(date));
                    dateFormart.append(" ");
                    break;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
            dateFormart.append(timeFormat.format(date));
        }
        return dateFormart.toString();
    }

    /**
     * ryan
     * 只有时间
     * HH24:mm
     */
    public static String displayTime(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
            dateFormart.append(sdf.format(date));
        }
        return dateFormart.toString();
    }

    /**
     * ryan
     * 只有日期
     * D日 0点0分0秒 到 23点59分59秒 ： 今天
     * D - 1+日 0点0分0秒 到 23点59分59秒 ： 昨天
     * D - 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd
     * D + 1+日 0点0分0秒 到 23点59分59秒 ： 明天
     * D + 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd
     */
    public static String displayDate(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            int day = (int) caculateDayFromToday(date); // 天
            switch (day) {
                case -1:
                    dateFormart.append("明天");
                    break;
                case 0:
                    dateFormart.append("今天");
                    break;
                case 1:
                    dateFormart.append("昨天");
                    break;
                default:
                    SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING11);
                    dateFormart.append(sdf.format(date));
                    break;
            }
        }
        return dateFormart.toString();
    }
    /**
     * 判断日期格式是否正确
     */
    public static boolean IsDateFormat(String dataStr) {
        boolean state = false;
        try {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            dFormat.setLenient(false);
            Date d = dFormat.parse(dataStr);
            state = true;
        } catch (ParseException e) {
            e.printStackTrace();
            state = false;
        }
        return state;
    }
    /**
     * 计算date之前n天的日期
     */
    public static Date getDateBefore(Date date, int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - n);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     */
    public static String getDateAfter(Date d, int day,String format) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date newDate = now.getTime();
        return sdf.format(newDate);
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time,String format) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    /* 时间戳转换成Date*/
    public static Date getDateToDate(long time){
        Date d = new Date(time);
        return d;
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }



}
