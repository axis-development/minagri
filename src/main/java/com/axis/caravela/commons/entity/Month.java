package com.axis.caravela.commons.entity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * The Enum Month.
 */
public enum Month {

    /** The JANUARY == 0. */
    JANUARY(Calendar.JANUARY),

    /** The FEBRUARY == 1. */
    FEBRUARY(Calendar.FEBRUARY),

    /** The MARCH == 2. */
    MARCH(Calendar.MARCH),

    /** The APRIL == 3. */
    APRIL(Calendar.APRIL),

    /** The MAY == . */
    MAY(Calendar.MAY),

    /** The JUNE. */
    JUNE(Calendar.JUNE),

    /** The JULY. */
    JULY(Calendar.JULY),

    /** The AUGUST. */
    AUGUST(Calendar.AUGUST),

    /** The SEPTEMBER. */
    SEPTEMBER(Calendar.SEPTEMBER),

    /** The OCTOBER. */
    OCTOBER(Calendar.OCTOBER),

    /** The NOVEMBER. */
    NOVEMBER(Calendar.NOVEMBER),

    /** The DECEMBER. */
    DECEMBER(Calendar.DECEMBER);

    /** The number. */
    private final int calendarNumber;

    /** The java map. */
    private static Map<Integer, Month> javaMap = new HashMap<Integer, Month>();

    static {
        for (Month m : values()) {
            javaMap.put(m.calendarNumber, m);
        }
    }

    /**
     * Instantiates a new month.
     * 
     * @param monthAsNumber the month as number
     */
    Month(int monthAsNumber) {
        this.calendarNumber = monthAsNumber;
    }

    /**
     * Gets the number.
     * 
     * @return the number
     */
    public int getNumber() {
        return calendarNumber + 1;
    }

    /**
     * Gets the number.
     *
     * @return the number
     */
    public int getCalendarNumber() {
        return calendarNumber;
    }

    /**
     * Gets the by month as calendar number.
     * 
     * @param javaNumber the java number. Where 0 is January and 11 December (see {@link Calendar})
     * @return the by month as java number
     */
    public static Month getByCalendarNumber(int javaNumber) {
        return javaMap.get(javaNumber);
    }

    /**
     * Gets the by month as number.
     * 
     * @param monthNumber the month number. Where 1 is January and 12 December
     * @return the by month as number
     */
    public static Month getByNumber(int monthNumber) {
        return javaMap.get(monthNumber - 1);
    }
}
