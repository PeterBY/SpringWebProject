package ru.javawebinar.topjava.web.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public final class LocalTimeConverter implements Converter<String, LocalTime> {

    private final DateTimeFormatter formatter;
    private final String dateFormat = "HH:mm:ss";

    public LocalTimeConverter() {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public LocalTime convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        return LocalTime.parse(source, formatter);
    }
}