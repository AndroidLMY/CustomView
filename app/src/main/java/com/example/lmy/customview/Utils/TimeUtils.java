package com.example.lmy.customview.Utils;


import android.os.CountDownTimer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @功能:时间工具类
 * @Creat 2019/03/09 16:13
 * @User Lmy
 * @By Android Studio
 */

public class TimeUtils {
    private static TimeUtils timeUtils;
    public static final String TIME_FORMAT_1 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String TIME_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_3 = "MM月dd日 HH:mm:ss";
    public static final String TIME_FORMAT_4 = "MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_5 = "MM月dd日 HH:mm";
    public static final String TIME_FORMAT_6 = "MM-dd HH:mm";
    public static final String TIME_FORMAT_7 = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_8 = "HH:mm:ss";
    public static final String TIME_FORMAT_10 = "yyyy-MM-dd";
    public static final String TIME_FORMAT_11 = "yyyy年MM月dd日";
    public static final String TIME_FORMAT_12 = "MM-dd";
    public static final String TIME_FORMAT_13 = "MM月dd日";

    /**
     * 单例模式
     */
    public static TimeUtils getInstance() {
        if (timeUtils == null) {
            timeUtils = new TimeUtils();
        }
        return timeUtils;
    }
    //********************************************************************日期与时间***************************************************************************************

    /**
     * 获取当前时间戳
     */
    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     */
    public String getTime(String format) {
        return getSimpleDateFormat(format).format(new Date());
    }


    /**
     * 格式
     *
     * @param format
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        return getSimpleDateFormat(format, Locale.CHINA);
    }

    /**
     * 格式
     *
     * @param format
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String format, Locale locale) {
        return new SimpleDateFormat(format, locale);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time "2018-12-9"
     * @param format    TIME_FORMAT_10
     * @return 1544307012
     */
    public String getTimestamp(String user_time, String format) throws ParseException {
        String re_time = null;
        Date d;
        d = getSimpleDateFormat(format).parse(user_time);
        long l = d.getTime();
        String str = String.valueOf(l);
        re_time = str.substring(0, 10);
        return re_time;
    }

    /**
     * 将时间戳转为字符串 例如：  cc_time = 1291778220;
     */
    public String getStrTime(String timestamp, String format) {
        String re_StrTime = getSimpleDateFormat(format).format(new Date(Long.valueOf(timestamp) * 1000L));
        return re_StrTime;
    }

    /**
     * 时间戳转字符串
     */
    public String getTime(long timeMillis, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(timeMillis - TimeZone.getDefault().getRawOffset());
    }


    /**
     * 日期字符串转成指定格式的日期字符串 getTimeForFormat("2018-06-02",TimeUtils.TIME_FORMAT_10,TimeUtils.TIME_FORMAT_13)
     */
    public String getTimeForFormat(String str, String currentFormat, String format) throws ParseException {
        Date date = new SimpleDateFormat(currentFormat, Locale.getDefault()).parse(str.trim());
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    /**
     * 字符串转日期getDate("2018-06-02",TimeUtils.TIME_FORMAT_10)
     */
    public Date getDate(String str, String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.getDefault()).parse(str.trim());
    }

    /**
     * 日期转字符串
     */
    public String getDate(Date data, String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(data);
    }

    /**
     * 获取年月份、星期
     */
    public TimeBean getStringData() {
        String mYear, mMonth, mDay, mWay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
//        mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay
        return new TimeBean(mYear, mMonth, mDay, mWay);
    }


    /**
     * 获取某月实际天数getActualDaysOfMonth(2020,2)
     */
    public int getActualDaysOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 日期（天）相加减dayCalculate("2018-06-02",2)
     */
    public String dayCalculate(String current, int count) throws ParseException {
        Date currentDate = getDate(current, TIME_FORMAT_10);  // 日期字符串转换为date类型
        Calendar mCalendar = Calendar.getInstance(); // 获取Calendar对象
        mCalendar.setTime(currentDate);  // 设置日期为当前已经选择的时间
        // 进行时间相加减操作
        mCalendar.add(Calendar.DAY_OF_MONTH, count);
        String date = getDate(mCalendar.getTime(), TIME_FORMAT_10);
        return date;
    }

    /**
     * 日期（月份）相加减monthCalculate("2018-06-02",2)
     */
    public String monthCalculate(String current, int count) throws ParseException {
        Date currentDate = getDate(current, "yyyy-MM");  // 日期字符串转换为date类型
        Calendar mCalendar = Calendar.getInstance();    // 获取Calendar对象
        mCalendar.setTime(currentDate); // 设置日期为当前已经选择的时间
        mCalendar.add(Calendar.MONTH, count);  // 进行时间相加减操作
        String date = getDate(mCalendar.getTime(), "yyyy-MM");
        return date;
    }

    /**
     * 日期大小比较 date1大于date2返回1； date1小于date2返回-1 date1等于date2返回0
     * compareDate("2018-06-02","2018-06-04",TimeUtils.TIME_FORMAT_10)
     *
     * @param date1  "2018-06-02"
     * @param date2  "2018-06-04"
     * @param format 时间格式
     * @return 1 ：大   0 ：等于   -1:1小
     */
    public int compareDate(String date1, String date2, String format) throws ParseException {
        DateFormat df = getSimpleDateFormat(format);
        Date dt1 = df.parse(date1);
        Date dt2 = df.parse(date2);
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * ————————————————————————————————————————————————————倒计时Dome ————————————————————————————————————————————————————
     *
     * @param args
     */
//
//
    public static long midTime;


    /**
     * 方式一： 给定时长倒计时
     *
     * @param millisInFuture    总时间  240*1000
     * @param countDownInterval 间隔时间 1000
     */
    public void startCountDownTimer(long millisInFuture, long countDownInterval, final OnCountdownListener countdownListener) {
        new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                getTimeFormat(millisUntilFinished, countdownListener);
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    /**
     * 方式二： 设定时间戳，倒计时
     *
     * @param startTime         开始时间
     * @param endTime           结束时间
     * @param countdownListener 返回接受
     */
    public void startCountdown2(long startTime, long endTime, OnCountdownListener countdownListener) {
        midTime = (endTime - startTime) / 1000;
        while (midTime > 0) {
            midTime--;
            getTimeFormat(midTime, countdownListener);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 方式三： 使用java.util.Timer类进行倒计时
     */
    private static void startCountdown(long startTime, long endTime, final OnCountdownListener countdownListener) {
        midTime = (endTime - startTime) / 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                midTime--;
                getTimeFormat(midTime, countdownListener);
            }
        }, 0, 1000);
    }

    private static void getTimeFormat(long midTime, OnCountdownListener countdownListener) {
        long days = midTime / (1000 * 60 * 60 * 24);
        long hh = midTime / 60 / 60 % 60;
        long mm = midTime / 60 % 60;
        long ss = midTime % 60;
        System.out.println("还剩" + days + "天" + hh + "小时" + mm + "分钟" + ss + "秒");
        countdownListener.onListener(days, hh, mm, ss);
    }


    public interface OnCountdownListener {
        void onListener(long days, long hh, long mm, long ss);
    }


    /**
     * 根据时间戳来判断当前的时间是几天前,几分钟,刚刚
     *
     * @param long_time  时间戳 1544307012
     * @param timeFormat 时间格式
     * @return
     */
    public String getTimeStateNew(String long_time, String timeFormat) {
        String long_by_13 = "1000000000000";
        String long_by_10 = "1000000000";
        if (Long.valueOf(long_time) / Long.valueOf(long_by_13) < 1) {
            if (Long.valueOf(long_time) / Long.valueOf(long_by_10) >= 1) {
                long_time = long_time + "000";
            }
        }
        Timestamp time = new Timestamp(Long.valueOf(long_time));
        Timestamp now = new Timestamp(System.currentTimeMillis());
//    System.out.println("传递过来的时间:"+format.format(time));
//    System.out.println("现在的时间:"+format.format(now));
        long day_conver = 1000 * 60 * 60 * 24;
        long hour_conver = 1000 * 60 * 60;
        long min_conver = 1000 * 60;
        long time_conver = now.getTime() - time.getTime();
        long temp_conver;
//    System.out.println("天数:"+time_conver/day_conver);
        if ((time_conver / day_conver) < 3) {
            temp_conver = time_conver / day_conver;
            if (temp_conver <= 2 && temp_conver >= 1) {
                return temp_conver + "天前";
            } else {
                temp_conver = (time_conver / hour_conver);
                if (temp_conver >= 1) {
                    return temp_conver + "小时前";
                } else {
                    temp_conver = (time_conver / min_conver);
                    if (temp_conver >= 1) {
                        return temp_conver + "分钟前";
                    } else {
                        return "刚刚";
                    }
                }
            }
        } else {
            return getSimpleDateFormat(timeFormat).format(time);
        }
    }

    public class TimeBean {
        String year, month, day, week;

        public TimeBean() {
        }

        public TimeBean(String year, String month, String day, String week) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.week = week;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }
}
