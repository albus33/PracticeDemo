package com.example.administrator.pulltorefreshlistviewdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/5/18.
 */

public class DateFormat {
    public static final int FORMAT_BEFORE_TIME = 3600;
    public static final int FORMAT_JUST_TIME = 60;

    public DateFormat() {
    }

    public static String formatDate(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate(long dateLong, int type) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = null;
            switch(type) {
                case 0:
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    break;
                case 1:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    break;
                case 2:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 3:
                    format = new SimpleDateFormat("yyyy/MM/dd");
                    break;
                case 4:
                    format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    break;
                case 5:
                    format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    break;
                default:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            return format.format(e);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String formatDate1(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate2(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate3(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate4(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDate5(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatTime(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String formatDuring(long mss) {
        long days = mss / 86400000L;
        long hours = mss % 86400000L / 3600000L;
        long minutes = mss % 3600000L / 60000L;
        long seconds = mss % 60000L / 1000L;
        return days > 0L?add0((int)days) + " 天 " + add0((int)hours) + " 时 " + add0((int)minutes) + " 分 " + add0((int)seconds) + " 秒 ":add0((int)hours) + " 时 " + add0((int)minutes) + " 分 " + add0((int)seconds) + " 秒 ";
    }

    public static String add0(int target) {
        return target >= 10?"" + target:"0" + target;
    }

    public static String getDateString(long ceartmillis) {
        String dateString = "";
        long delmillis = (System.currentTimeMillis() - ceartmillis) / 1000L;
        Date newdate = new Date();
        Calendar newcalendar = Calendar.getInstance(Locale.CHINA);
        newcalendar.setTime(newdate);
        Date date = new Date(ceartmillis);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        int dateday = newcalendar.get(6) - calendar.get(6);
        if(delmillis / 60L / 60L / 24L >= 0L && delmillis / 60L / 60L / 24L < 4L) {
            if(dateday == 0) {
                if(delmillis >= 0L && delmillis <= 60L) {
                    dateString = "刚刚";
                } else if(delmillis > 60L && delmillis <= 3600L) {
                    dateString = delmillis / 60L + "分钟前";
                } else {
                    dateString = "今天" + formatTime(ceartmillis);
                }
            } else if(dateday == 1) {
                dateString = "昨天" + formatTime(ceartmillis);
            } else if(dateday == 2) {
                dateString = "前天" + formatTime(ceartmillis);
            } else {
                dateString = formatDate2(ceartmillis);
            }
        } else {
            dateString = formatDate2(ceartmillis);
        }

        return dateString;
    }

    public static long convertTime(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(dateStr).getTime();
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public static String[] getPeriodTime(String dateTime, int size) {
        long l = convertTime(dateTime);
        Date date = new Date();
        date.setTime(l);
        Calendar c = Calendar.getInstance();
        String[] period = new String[size];

        for(int i = 0; i < size; ++i) {
            c.setTime(date);
            c.add(5, -i);
            period[size - i - 1] = c.get(1) + "-" + (c.get(2) + 1) + "-" + c.get(5);
        }

        return period;
    }

    public static boolean contrastTime(String publishDate) {
        long c = convertTime(getSysTime(0));
        long l = convertTime(publishDate);
        return c > l;
    }

    public static boolean contrastTime(long date) {
        return System.currentTimeMillis() > date;
    }

    public static String getSysTime(int type) {
        SimpleDateFormat formatter = null;
        if(type == 0) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }

        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String transformTime(int taskWorkLong) {
        taskWorkLong /= 1000;
        int minute = taskWorkLong / 60;
        int hour = minute / 60;
        int second = taskWorkLong % 60;
        minute %= 60;
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)});
    }

    public static String[] transformTime2(int timeLong) {
        String[] str = new String[3];
        timeLong *= 1000;
        timeLong /= 1000;
        int minute = timeLong / 60;
        int hour = minute / 60;
        int second = timeLong % 60;
        minute %= 60;
        str[0] = String.format("%02d", new Object[]{Integer.valueOf(hour)});
        str[1] = String.format("%02d", new Object[]{Integer.valueOf(minute)});
        str[2] = String.format("%02d", new Object[]{Integer.valueOf(second)});
        return str;
    }

    public static String showTime(int time) {
        int minute = time / 60;
        int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        return hour > 0?String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)}):String.format("%02d:%02d", new Object[]{Integer.valueOf(minute), Integer.valueOf(second)});
    }

    public static String showTime2(int time) {
        int minute = time / 60;
        int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        return hour > 0?String.format("%02d时%02d分%02d秒", new Object[]{Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)}):String.format("%02d分%02d秒", new Object[]{Integer.valueOf(minute), Integer.valueOf(second)});
    }
}
