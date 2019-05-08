package com.scenario_projects.lifeline_front_stage.utils;

import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataConverter {

    public DataConverter() {

    }

    public static int parseSecondDigitValue(String label) {
        Matcher qtyMatcher = Pattern.compile("\\d+$").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return Integer.parseInt(qtyMatcher.group());
    }

    public static String parseMonthValue(String label) {
        Matcher qtyMatcher = Pattern.compile("0\\d").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract open tasks value!");
        return qtyMatcher.group();
    }

    public static String parseYearValue(String label) {
        Matcher qtyMatcher = Pattern.compile("^\\d+").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract open tasks value!");
        return qtyMatcher.group();
    }

    public static String parseDateValue(String label) {
        Matcher qtyMatcher = Pattern.compile("\\d+$").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return qtyMatcher.group();
    }

    public static String parsePatientFullName(String label) {
        Matcher qtyMatcher = Pattern.compile("^\\w+\\s\\w+\\s\\w+").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return qtyMatcher.group();
    }

    public static int parseNewMonthValue(String label) {
        String date;
        Matcher qtyMatcher = Pattern.compile("-\\d+\\-").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        date = qtyMatcher.group().replaceAll("\\D", "");
        return Integer.parseInt(date);
    }

    public static String parseDataTime(String label) {
        Matcher qtyMatcher = Pattern.compile("\\d+\\s\\w+\\s\\-\\s\\d+\\s\\w+").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return qtyMatcher.group();
    }

    public static String parseFileName(String label) {
        Matcher qtyMatcher = Pattern.compile("\\w+\\.\\w+$").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return qtyMatcher.group();
    }

    public static String parseFilePath(String label) {
        Matcher qtyMatcher = Pattern.compile("\\w+\\/\\w+\\-\\d+\\-\\d+\\_\\d+\\-\\d+\\-\\d+").matcher(label);
        Assert.assertTrue(qtyMatcher.find(), "Unable to extract Due tasks value!");
        return qtyMatcher.group();
    }
}
