package cn.chen.zy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

}
