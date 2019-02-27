package com.scenario_projects.lifeline_front_stage.dataProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    public static String currentDate() {
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(dNow);
    }

    public static String getNextDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

    public static String currentTime() {
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("HH:mm");
        return ft.format(dNow);
    }

    public static String newCurrentTime() {
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("HH:mm:ss.SSS");
        return ft.format(dNow);
    }
}
