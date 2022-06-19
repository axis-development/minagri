/*
 * Copyright 2003-2009 LCM-ANMC, Inc. All rights reserved.
 * This source code is the property of LCM-ANMC, Direction
 * Informatique and cannot be copied or distributed without
 * the formal permission of LCM-ANMC.
 */
package com.axis.caravela.commons.control;

import lombok.SneakyThrows;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * Utility class for working with dates.
 *
 * @author t51sagbse Benny Segers
 * @version %PR%
 */
public interface Dates {

    static boolean isEqualTo(LocalDate date1, LocalDate date2) {
        if (date1 == null && date2 == null) {
            return true;
        }
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.isEqual(date2);
    }

    static boolean isNotEqualTo(LocalDate date1, LocalDate date2) {
        return !isEqualTo(date1, date2);
    }

    static boolean isBeforeOrEqualTo(LocalDate localDate1, LocalDate localDate2) {
        return !localDate1.isAfter(localDate2);
    }

    static boolean isAfterOrEqualTo(LocalDate localDate1, LocalDate localDate2) {
        return !localDate1.isBefore(localDate2);
    }

    static boolean isTimeEqualTo(LocalDateTime dateTime, int hour, int minute) {
        return dateTime.getHour() == hour && dateTime.getMinute() == minute;
    }

    static boolean isTimeBefore(LocalDateTime dateTime, int hour, int minute) {
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return time.isBefore(LocalTime.of(hour, minute));
    }

    static boolean isTimeBeforeOrEqualTo(LocalDateTime dateTime, int hour, int minute) {
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return !time.isAfter(LocalTime.of(hour, minute));
    }

    static boolean isTimeAfter(LocalDateTime dateTime, int hour, int minute) {
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return time.isAfter(LocalTime.of(hour, minute));
    }

    static boolean isTimeAfterOrEqualTo(LocalDateTime dateTime, int hour, int minute) {
        LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
        return !time.isBefore(LocalTime.of(hour, minute));
    }

    static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Checks if a date is null or equal to 1970-01-01 or 1800-01-01
     */
    static Boolean isEmptyDate(LocalDate date) {
        if (date == null) {
            return true;
        }
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        return (year == 1970 || year == 1800) && month == 1 && day == 1;
    }

    /**
     * Checks if a date is not null or equal to 1970-01-01 or 1800-01-01
     */
    static Boolean isNotEmptyDate(LocalDate date) {
        return !isEmptyDate(date);
    }

    static boolean isWithinPeriod(LocalDate referenceDate, LocalDate periodBegin, LocalDate periodEnd) {
        boolean validBegin = periodBegin == null || !referenceDate.isBefore(periodBegin);
        boolean validEnd = periodEnd == null || !referenceDate.isAfter(periodEnd);
        return validBegin && validEnd;
    }

    static boolean isWithinPeriod(LocalDateTime referenceDate, LocalDateTime periodBegin, LocalDateTime periodEnd) {
        boolean validBegin = periodBegin == null || !referenceDate.isBefore(periodBegin);
        boolean validEnd = periodEnd == null || !referenceDate.isAfter(periodEnd);
        return validBegin && validEnd;
    }

    static Integer getQuarter(LocalDate date) {
        int monthMinus1 = date.getMonthValue() - 1;
        return (monthMinus1 / 3) + 1;
    }

    static int getSemester(LocalDate date) {
        return date.getMonthValue() <= 6 ? 1 : 2;
    }

    static LocalDate getEarliestDate(Collection<LocalDate> dates) {
        return dates.stream().min(LocalDate::compareTo).orElse(null);
    }

    static LocalDate getLatestDate(Collection<LocalDate> dates) {
        return dates.stream().max(LocalDate::compareTo).orElse(null);
    }

    static Integer getYearsBetween(LocalDate date1, LocalDate date2) {
        return (int) ChronoUnit.YEARS.between(date1, date2);
    }

    static Integer getDaysBetween(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return null;
        }

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        return Math.abs((int) daysBetween);
    }

    static Integer getSecondsBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        Long seconds = Duration.between(dateTime1, dateTime2).getSeconds();
        return seconds.intValue();
    }

    static List<LocalDate> getAllDatesInPeriod(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate date = fromDate;
        while (isBeforeOrEqualTo(date, toDate)) {
            dates.add(date);
            date = date.plusDays(1);
        }
        return dates;
    }

    /**
     * Returns the year and month of the given date as YYYYMM.
     */
    static Integer getYearMonthValue(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        return (year * 100) + month;
    }

    static LocalDateTime addOptionalDateAndTime(LocalDate localDate, LocalTime localTime) {
        if (localDate == null && localTime == null) {
            return null;
        }
        if (localDate == null || localTime == null) {
            throw new RuntimeException("Invalid date and time"+localDate+ " "+localTime);
        }
        return LocalDateTime.of(localDate, localTime);
    }

    static LocalDateTime atEndOfDay(LocalDate date) {
        return date.atTime(23, 59, 59);
    }

    static LocalDate atStartOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    static LocalDate atEndOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    static LocalDate atStartOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    static LocalDate atEndOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    static LocalDate atStartOfYear(LocalDate date) {
        return date.withMonth(1).withDayOfMonth(1);
    }

    static LocalDate atEndOfYear(LocalDate date) {
        return date.withMonth(12).withDayOfMonth(31);
    }

    static LocalDate atStartOfSemester(LocalDate date) {
        if (date.getMonthValue() <= 6) {
            return atStartOfYear(date);
        } else {
            return date.withMonth(7).withDayOfMonth(1);
        }
    }

    static LocalDate atEndOfSemester(LocalDate date) {
        if (date.getMonthValue() <= 6) {
            return date.withMonth(6).withDayOfMonth(30);
        } else {
            return atEndOfYear(date);
        }
    }

    static LocalDate atStartOfTrimester(LocalDate date) {
        if (date.getMonthValue() <= 3) {
            return date.withMonth(1).withDayOfMonth(1);
        }
        if (date.getMonthValue() <= 6) {
            return date.withMonth(4).withDayOfMonth(1);
        }
        if (date.getMonthValue() <= 9) {
            return date.withMonth(7).withDayOfMonth(1);
        }
        return date.withMonth(10).withDayOfMonth(1);
    }

    static LocalDate atEndOfTrimester(LocalDate date) {
        if (date.getMonthValue() <= 3) {
            return date.withMonth(3).withDayOfMonth(31);
        }
        if (date.getMonthValue() <= 6) {
            return date.withMonth(6).withDayOfMonth(30);
        }
        if (date.getMonthValue() <= 9) {
            return date.withMonth(9).withDayOfMonth(30);
        }
        return date.withMonth(12).withDayOfMonth(31);
    }

    static Date toDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date toDate(LocalTime localTime) {
        return localTime == null ? null : Date.from(LocalDateTime.of(LocalDate.of(1970, 1, 1), localTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    static Date toDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    static Calendar toCalendar(LocalDate localDate) {
        return toCalendar(toDate(localDate));
    }

    @SneakyThrows
    static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }

        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
    }

    static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
        return toXMLGregorianCalendar(toDate(localDate));
    }

    static XMLGregorianCalendar toXMLGregorianCalendar(LocalTime localTime) {
        return toXMLGregorianCalendar(toDate(localTime));
    }

    static XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime localDateTime) {
        return toXMLGregorianCalendar(toDate(localDateTime));
    }

    static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    static LocalDate toLocalDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toLocalDate();
    }

    static LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }

        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    static LocalDateTime toLocalDateTime(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }

        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }

    static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    static LocalDateTime toLocalDateTime(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.atStartOfDay();
    }

    static LocalDateTime toLocalDateTime(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return LocalDateTime.of(LocalDate.of(1970, 1, 1), localTime);
    }

    static LocalDateTime toLocalDateTime(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone() == null ? ZoneId.systemDefault() : cal.getTimeZone().toZoneId());
    }

    static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    static LocalTime toLocalTime(Date date) {
        if (date == null) {
            return null;
        }
        return toLocalDateTime(date).toLocalTime();
    }

    static LocalTime toLocalTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toLocalTime();
    }

    static LocalTime toLocalTime(XMLGregorianCalendar cal) {
        if (cal == null) {
            return null;
        }
        return toLocalDateTime(cal).toLocalTime();
    }

    static LocalDate toLocalDate(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return toLocalDateTime(cal).toLocalDate();
    }

    static Long toEpochMillis(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    static Long toEpochMillis(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return toEpochMillis(localDate.atTime(0, 0));
    }
}