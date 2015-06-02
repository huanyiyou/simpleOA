package cn.yht.simpleOA.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by YHT on 2015/5/27.
 */
public class TimeHandler {
    /**
     * 根据日期得到年份
     * @param date
     * @return
     */
    public static String getYearByDate(String date){
        String[] split = date.split("/");
        if(split.length == 3){
            return split[0];
        }
        else {
            return "";
        }
    }

    /**
     * 根据日期得到月份
     * @param date
     * @return
     */
    public static String getMonthByDate(String date){
        String[] split = date.split("/");
        if(split.length == 3){
            return  split[1];
        }
        else {
            return "";
        }
    }

    public static Double getHoursByTimeSpan(String timeSpan) {
        int hours = 0;
        int minutes = 0;
        String temp = timeSpan.replace("小时", "/").replace("分钟", "/");
        String[] hm = temp.split("/");
        if(hm.length == 2){
            hours = Integer.parseInt(hm[0]);
            minutes = Integer.parseInt(hm[1]);
        }
        return hours + (Double)(Math.round((minutes/60.0)*1000)/1000.0);
    }

    /**
     * 年份可选框的值
     * @return
     */
    public static List<String> getYears(){
        List<String> years = new ArrayList<>();
        for(int i = 2014 ; i <= Calendar.getInstance().get(Calendar.YEAR) ; i++){
            years.add(String.valueOf(i));
        }
        return years;
    }

    public static List<String> getMonths(){
        List<String> months = new ArrayList<>();
        for(int i = 1 ; i<= 12 ; i++){
            if(i < 10){
                months.add("0" + String.valueOf(i));
            }else {
                months.add(String.valueOf(i));
            }
        }
        return months;
    }

}
