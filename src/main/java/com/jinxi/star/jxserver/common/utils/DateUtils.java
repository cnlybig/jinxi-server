package com.jinxi.star.jxserver.common.utils;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author: Shiz
 * @date: 2019/8/23 18:14
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param pattern
     * @return java.lang.String
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串格式化
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseString2Date(String date, String format) throws ParseException {
        Date toDate = null;
        if (!Strings.isNullOrEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            toDate = sdf.parse(date + "");
        }
        return toDate;
    }

    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(date);
        timeCal.set(Calendar.DAY_OF_MONTH, 1);
        return timeCal.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(date);
        timeCal.set(Calendar.DAY_OF_MONTH, 1);
        timeCal.add(Calendar.MONTH, 1);
        timeCal.add(Calendar.DAY_OF_MONTH, -1);
        return timeCal.getTime();
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(date);
        int day = timeCal.get(Calendar.DATE);
        timeCal.set(Calendar.DATE, day - 1);
        return timeCal.getTime();
    }

    /**
     * 获取日期前一天 返回yyyymmdd
     *
     * @param date
     * @return
     */
    public static String getDateBefore(Date date) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(date);
        int day = timeCal.get(Calendar.DATE);
        timeCal.set(Calendar.DATE, day - 1);
        return format(timeCal.getTime(), "yyyymmdd");
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
}
