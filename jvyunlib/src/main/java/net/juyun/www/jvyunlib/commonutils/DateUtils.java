package net.juyun.www.jvyunlib.commonutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 关 on 2016/4/21.
 */
public class DateUtils {
    /**
     * 将字符串日期转换成短格式的字符串
     *
     * @param str
     * @return
     */
    public static String StrDateToSortStrDate(String str) {
        Date date = StrToDate(str);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);

    }

    /**
     * 获取当前时间的年月日时分秒毫秒
     * @return
     */
    public static String getYMDHMSS(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(d);

    }
    /**
     * 获取当前时间的年月日时分秒毫秒
     * @return
     */
    public static String getFromYMDHMSS(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS Z");
        return sdf.format(d);

    }
    /**
     * 将yyyy-MM-dd转换成 yyyy年MM月dd日
     */
    public static String formYMDtoYMD(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if (str != null) {
                date = format.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * 将yyyy-MM-dd HH:ss转换成 yyyy年MM月dd日 HH时mm分
     */
    public static String fromYMDHMtoYMDHM(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            if (str != null) {

                date = format.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return format.format(date);
    }

    /**
     * 将年月日时分秒的字符串，转换成年月日时分的字符串
     */
    public static String fromYMDHMStoYMDHM(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            if (str != null) {
                date = format.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    /**
     * 将年月日时分秒的字符串，转换成年月日时分的字符串
     */
    public static String fromYMDHMStoYMD(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            if (str != null) {
                date = format.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    /**
     * 将字符串装换成年月
     *
     * @param str
     * @return
     */
    public static String StrDateToYearAndMonthDate(String str) {
        if ("不限".equals(str) || "请选择日期".equals(str)) {//这里不要删除 不然赛事赛选会报错
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
            Date date = null;
            try {
                if (str != null) {
                    date = format.parse(str);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("yyyy-MM");
            return format.format(date);
        }

    }

    /**
     * 字符串转日期年月日时分
     *
     * @param str
     * @return
     */

    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前日期字符串
     */
    public static String getCurrentDateStr() {
        Date dt = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(dt);
    }

//    /**
//     * 日期转毫秒值
//     */
//    public static long StrTOMillionSeconds(String str) {
//        // 日期转毫秒
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//        long millionSeconds = 0;//毫秒
//        try {
//            millionSeconds = sdf.parse(str).getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return millionSeconds;
//    }

    /**
     * 比较两个日期的先后
     * 当前日期在前为true 当前日期在后为 false
     */
    public static boolean compareToDate(String after) {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();//当前时间的毫秒值
        calendar.setTime(StrToDate(after));//参赛报名截止日
        long afterTime = calendar.getTimeInMillis();
        if (currentTime <= afterTime) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将年月日时分秒毫秒的字段转换成年月日时分
     * SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     * String time =  formatter.format(dNow);
     */
    public static String getYMDHM(String dataStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date = null;
        try {
            date = format.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dataStr;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = format1.format(date);
        return str;
    }
    public static String getYMD(String dataStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date = null;
        try {
            date = format.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dataStr;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String str = format1.format(date);
        return str;
    }
    public static String getDay(String dataStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date = null;
        try {
            date = format.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dataStr;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("dd");
        String str = format1.format(date);
        return str;
    }
    public static String getTime(String dataStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date = null;
        try {
            date = format.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dataStr;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        String str = format1.format(date);
        return str;
    }
}


