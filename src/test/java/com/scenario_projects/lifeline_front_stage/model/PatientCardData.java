package com.scenario_projects.lifeline_front_stage.model;


public class PatientCardData {
    private static String name;
    private static String unit;
    private static String birthDate;
    private static String gender;
    private static String country;
    private static String postCode;
    private static String address;

    public PatientCardData(String name, String unit, String birthDate, String gender, String country, String postCode, String address) {
        this.name = name;
        this.unit = unit;
        this.birthDate = birthDate;
        this.gender = gender;
        this.country = country;
        this.postCode = postCode;
        this.address = address;
    }

    public static String getName() {
        return name;
    }

    public static String getUnit() {
        return unit;
    }

    public static String getBirthDate() {
        return birthDate;
    }

    public static String getGender() {
        return gender;
    }

    public static String getCountry() {
        return country;
    }

    public static String getPostCode() {
        return postCode;
    }

    public static String getAddress() {
        return address;
    }

    public static PatientCardData generate() {
        return new PatientCardData(
                "New patient " + System.currentTimeMillis(),
                "Cardiological ICU",
                "02031975",
                "Male",
                "UKRAINE",
                "643993",
                "Popova street, 5");
    }
}
