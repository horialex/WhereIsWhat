package com.tools.utils;


import java.time.LocalDateTime;

public class DateUtils {
    public static LocalDateTime addDaysToCurrentDate(int days){
        return LocalDateTime.now().plusDays(days);
    }

    public static LocalDateTime addMinutesToCurrentDate(int minutes){
        return LocalDateTime.now().plusMinutes(minutes);

    }

    public static LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }


    public static LocalDateTime addHoursToCurrentDate(int hours){
        LocalDateTime ldt = LocalDateTime.now().plusHours(hours);
        for (int i = ldt.getMinute(); i <= 59; i++) {
            if (i % 5 == 0) {
                ldt  = ldt.withMinute(i);
                break;
            }
        }
        return ldt;
    }
}
