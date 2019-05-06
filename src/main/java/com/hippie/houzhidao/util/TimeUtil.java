package com.hippie.houzhidao.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by reckywangbowen_i on 2019/03/06
 */
public class TimeUtil {
    public static Timestamp getCurrentTime(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static String getTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }
}
