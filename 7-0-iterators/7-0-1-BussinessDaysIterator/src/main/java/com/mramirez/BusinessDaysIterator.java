package com.mramirez;

import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIterator implements Iterator<LocalDate> {

    private LocalDate date;

    public BusinessDaysIterator(final LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public LocalDate next() {
        return getNextBusinessDay();
    }

    private LocalDate getNextBusinessDay() {
        do {
            date = date.plusDays(1);
        } while(date.getDayOfWeek().getValue() > 5);
        return date;
    }
}
