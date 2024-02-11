package org.ykryukov.employees;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public interface Generable {
    public default int generateInt() {
        return new Random().nextInt(1000000);
    }

    public default String generateString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public default Date generateDate() {
        final int year = new Random().nextInt(1950, 2014);
        final int month = new Random().nextInt(Calendar.JANUARY, Calendar.DECEMBER);
        final int day = new Random().nextInt(1, 28);
        return new GregorianCalendar(year, month, day).getTime();
    }
}
