package ru.javawebinar.topjava.web.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public final class LocalTimeFormatter implements Formatter<LocalTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }

        return LocalTime.parse(text, formatter);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object.format(formatter);
    }
}