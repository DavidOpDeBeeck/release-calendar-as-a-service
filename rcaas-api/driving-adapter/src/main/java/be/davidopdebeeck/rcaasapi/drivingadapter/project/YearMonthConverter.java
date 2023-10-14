package be.davidopdebeeck.rcaasapi.drivingadapter.project;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

import static java.lang.Integer.parseInt;

@Component
public class YearMonthConverter implements Converter<String, YearMonth> {

    @Override
    public YearMonth convert(String source) {
        String[] split = source.split("-");
        return YearMonth.of(parseInt(split[0]), parseInt(split[1]));
    }
}
