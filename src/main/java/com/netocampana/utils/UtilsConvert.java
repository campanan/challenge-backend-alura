package com.netocampana.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilsConvert {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate stringToDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    public static String dateToString(LocalDate date) {
        return formatter.format(date);
    }
}
