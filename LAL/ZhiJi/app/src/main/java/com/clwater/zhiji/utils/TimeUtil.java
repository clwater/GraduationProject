package com.clwater.zhiji.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class TimeUtil {
    public static String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String getDay(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate =  new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static List<String> getALLDate(){
        List<String> list = new ArrayList<String>();
        SimpleDateFormat formatter_year = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatter_month = new SimpleDateFormat("MM");
        SimpleDateFormat formatter_day = new SimpleDateFormat("dd");
        Date curDate =  new Date(System.currentTimeMillis());
        String str1 = formatter_year.format(curDate);
        String str2 = formatter_month.format(curDate);
        String str3 = formatter_day.format(curDate);

        list.add(str1);
        list.add(str2);
        list.add(str3);


        return list;
    }
}
