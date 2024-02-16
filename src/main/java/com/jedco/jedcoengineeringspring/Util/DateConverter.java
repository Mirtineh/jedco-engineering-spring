package com.jedco.jedcoengineeringspring.Util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateConverter {
    public Day convertToStartAndDate(Date date) {
        // Define a range for the day, from the beginning to the end
        return getDay(date, date);
    }

    public Day convertToStartAndEndDate(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        try {
            date = dateFormat.parse(stringDate);
        } catch (Exception ex) {
            return null;
        }
        return getDay(date, date);
    }

    public Day convertBetweenDays(String startDateString, String endDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (Exception ex) {
            return null;
        }
        return convertBetweenDays(startDate, endDate);
    }

    public Day convertBetweenDays(Date startDate, Date endDate) {
        return getDay(startDate, endDate);
    }

    private Day getDay(Date startDate, Date endDate) {
        Calendar startOfDay = Calendar.getInstance();
        startOfDay.setTime(startDate);
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);

        Calendar endOfDay = Calendar.getInstance();
        endOfDay.setTime(endDate);
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);
        endOfDay.set(Calendar.MILLISECOND, 999);
        return new Day(startOfDay.getTime(), endOfDay.getTime());
    }
}
