/*
 * Copyright 2003-2009 LCM-ANMC, Inc. All rights reserved.
 * This source code is the property of LCM-ANMC, Direction
 * Informatique and cannot be copied or distributed without
 * the formal permission of LCM-ANMC.
 */
package com.axis.minagri.commons.control;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Conversions {

    /**
     * Returns the given value as a string.
     */
    static String toString(Number value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    /**
     * Returns the given value as a string.
     */
    static String toString(Enum value) {
        if (value == null) {
            return null;
        }
        return value.name();
    }

    /**
     * Returns the given value as a string, considering null as a blank.
     */
    static String toStringNullBlank(Number value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    /**
     * Returns the given value as a string, considering null as a zero.
     */
    static String toStringNullZero(Number value) {
        if (value == null) {
            return "0";
        }
        return value.toString();
    }

    /**
     * Returns the given value as a string.
     */
    static String toString(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? "1" : "0";
    }

    /**
     * Returns the given BigDecimal as a string with the given precision.
     */
    static String toStringWithPrecision(BigDecimal value, Integer precision) {
        if (value == null) {
            return null;
        }
        BigDecimal scaled = value.setScale(precision, RoundingMode.DOWN);
        return scaled.toPlainString();
    }

    /**
     * Returns the given value as a string if the value is not blank.
     */
    static String toStringBlankNull(String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        return value;
    }

    /**
     * Returns the given value as a string if the value is not empty.
     */
    static String toStringEmptyNull(String value) {
        if (Strings.isEmpty(value)) {
            return null;
        }
        return value;
    }

    /**
     * Returns the given value as a string of the given length padded with spaces at the right.
     */
    static String toStringRightPadded(String value, int length) {
        if (value == null) {
            return null;
        }
        if (value.length() > length) {
            return Strings.left(value, length);
        }
        return Strings.rightPad(value, length);
    }

    /**
     * Returns the given value as a string of the given length padded with spaces at the right, treating null as blank.
     */
    static String toStringRightPaddedNullBlank(String value, int length) {
        return toStringRightPadded(value == null ? "" : value, length);
    }

    /**
     * Returns the given value as a string of the given length padded with zeros.
     */
    static String toStringZeroPadded(Number value, int length) {
        if (value == null) {
            return null;
        }
        String stringValue = toString(value);
        return Strings.leftPad(stringValue, length, "0");
    }

    /**
     * Returns the given value as a string of the given length padded with zeros.
     */
    static String toStringZeroPadded(String value, int length) {
        if (value == null) {
            return null;
        }
        return Strings.leftPad(value, length, "0");
    }

    /**
     * Returns the given value as a string of the given length padded with zeros, considering null as a blank.g}
     */
    static String toStringZeroPaddedNullBlank(Number value, int length) {
        String stringValue = toStringNullBlank(value);
        return Strings.leftPad(stringValue, length, "0");
    }

    /**
     * Returns the given value as a string.
     */
    static String toString(byte[] value) {
        if (value == null) {
            return null;
        }

        try {
            return new String(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the given value as a long.
     */
    static Long toLong(Number value) {
        if (value == null) {
            return null;
        }
        return value.longValue();
    }

    /**
     * Returns the given value as a long.
     */
    static Long toLong(String value) {
        BigDecimal bigDecimalValue = toBigDecimal(value);
        return bigDecimalValue != null ? bigDecimalValue.longValue() : null;
    }

    /**
     * Returns the given value as a long or null if the value contains only zero's.
     */
    static Long toLongIgnoreZero(String value) {
        if (Strings.containsOnly(value, "0")) {
            return null;
        }
        return toLong(value);
    }

    /**
     * Returns the given value as a long or zero if the value cannot be converted.
     */
    static Long toLongDefaultZero(String value) {
        BigDecimal bigDecimalValue = toBigDecimal(value);
        return bigDecimalValue != null ? bigDecimalValue.longValue() : 0L;
    }

    /**
     * Returns the given value as an integer.
     */
    static Integer toInteger(String value) {
        BigDecimal bigDecimalValue = toBigDecimal(value);
        return bigDecimalValue != null ? bigDecimalValue.intValue() : null;
    }

    /**
     * Returns the given value as a double.
     */
    static Double toDouble(String value) {
        try {
            BigDecimal bigDecimalValue = value == null ? null : new BigDecimal(value);
            return bigDecimalValue != null ? bigDecimalValue.doubleValue() : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns the given value as an BigDecimal.
     */
    static BigDecimal toBigDecimal(Number value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value.toString());
    }

    /**
     * Returns the given value as an BigDecimal.
     */
    static BigDecimal toBigDecimal(String value) {
        if (Strings.isBlank(value) || !Strings.isNumericSigned(value)) {
            return null;
        }
        return new BigDecimal(value);
    }

    /**
     * Casts the given value to the target class or returns null of the cast cannot be done.
     */
    @SuppressWarnings("unchecked")
    static <T> T safeCast(Object value, Class<T> targetClass) {
        if (value == null || !targetClass.isAssignableFrom(value.getClass())) {
            return null;
        }
        return (T) value;
    }

    /**
     * Returns the given value as an integer.
     */
    static Integer toInteger(Number value) {
        if (value == null) {
            return null;
        }
        return value.intValue();
    }

    /**
     * Returns the given value as a double.
     */
    static Double toDouble(Number value) {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    /**
     * Returns the given value as an integer.
     */
    static Boolean toBoolean(String value) {
        if (Strings.equals(value, "1")) {
            return true;
        }
        if (Strings.equals(value, "0")) {
            return false;
        }
        if (Strings.equalsIgnoreCase(value, "true")) {
            return true;
        }
        if (Strings.equalsIgnoreCase(value, "false")) {
            return false;
        }
        return null;
    }

    static String toStringEurocentAmount(Number euroAmount) {
        if (euroAmount == null || Strings.isBlank(euroAmount.toString())) {
            return "";
        }

        BigDecimal bigDecimalValue;
        try {
            bigDecimalValue = new BigDecimal(euroAmount.toString());
        } catch (Exception e) {
            throw new RuntimeException("Value " + euroAmount + " is not numeric", e);
        }

        bigDecimalValue = bigDecimalValue.movePointRight(2);
        bigDecimalValue = bigDecimalValue.setScale(0, RoundingMode.DOWN);
        return bigDecimalValue.toPlainString();
    }

    static String toStringEurocentAmountWithoutSign(Number euroAmount, Integer length) {
        if (euroAmount == null || Strings.isBlank(euroAmount.toString())) {
            return Strings.leftPad("", length, "0");
        }

        BigDecimal bigDecimalValue;
        try {
            bigDecimalValue = new BigDecimal(euroAmount.toString()).abs();
        } catch (Exception e) {
            throw new RuntimeException("Value " + euroAmount + " is not numeric", e);
        }

        bigDecimalValue = bigDecimalValue.movePointRight(2);
        bigDecimalValue = bigDecimalValue.setScale(0, RoundingMode.DOWN);
        String centValue = bigDecimalValue.toPlainString();
        if (centValue.length() > length) {
            throw new RuntimeException("Value " + euroAmount + " too large for euroAmount field of length " + length);
        }
        return Strings.leftPad(centValue, length, "0");
    }

    static String toStringEurocentAmountWithSign(Number euroAmount, Integer length) {
        if (euroAmount == null || Strings.isBlank(euroAmount.toString())) {
            return "+" + Strings.leftPad("", length - 1, "0");
        }

        BigDecimal bigDecimalValue;
        try {
            bigDecimalValue = new BigDecimal(euroAmount.toString());
        } catch (Exception e) {
            throw new RuntimeException("Value " + euroAmount + " is not numeric", e);
        }

        bigDecimalValue = bigDecimalValue.movePointRight(2);
        bigDecimalValue = bigDecimalValue.setScale(0, RoundingMode.DOWN);
        String centValue = bigDecimalValue.toPlainString();

        boolean negative = centValue.startsWith("-");
        if (centValue.startsWith("-") || centValue.startsWith("+")) {
            centValue = Strings.substring(centValue, 1);
        }

        if (centValue.length() > length - 1) {
            throw new RuntimeException("Value " + euroAmount + " too large for euroAmount field of length " + length);
        }

        String sign = negative ? "-" : "+";
        return sign + Strings.leftPad(centValue, length - 1, "0");
    }

    static String toStringWithSign(Number value, Integer length) {
        if (value == null || Strings.isBlank(value.toString())) {
            return "+" + Strings.leftPad("", length - 1, "0");
        }

        BigDecimal bigDecimalValue;
        try {
            bigDecimalValue = new BigDecimal(value.toString());
        } catch (Exception e) {
            throw new RuntimeException("Value " + value + " is not numeric", e);
        }

        bigDecimalValue = bigDecimalValue.setScale(0, RoundingMode.DOWN);
        String stringValue = bigDecimalValue.toPlainString();

        boolean negative = stringValue.startsWith("-");
        if (stringValue.startsWith("-") || stringValue.startsWith("+")) {
            stringValue = Strings.substring(stringValue, 1);
        }

        if (stringValue.length() > length - 1) {
            throw new RuntimeException("Value " + value + " too large for amount field of length " + length);
        }

        String sign = negative ? "-" : "+";
        return sign + Strings.leftPad(stringValue, length - 1, "0");
    }

    static BigDecimal toEuroAmount(String eurocentAmount) {
        if (Strings.isBlank(eurocentAmount) || !Strings.isNumericSigned(eurocentAmount)) {
            return null;
        }
        BigDecimal bigDecimalValue;
        try {
            bigDecimalValue = new BigDecimal(eurocentAmount);
        } catch (Exception e) {
            throw new RuntimeException("Value " + eurocentAmount + " is not numeric", e);
        }

        bigDecimalValue = bigDecimalValue.movePointLeft(2);
        bigDecimalValue = bigDecimalValue.setScale(2, RoundingMode.DOWN);
        return bigDecimalValue;
    }

    static <T extends Enum<?>> T toEnum(Class<T> targetEnumClass, Enum<?> sourceEnum) {
        if (sourceEnum == null) return null;
        for (T enumValue : targetEnumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(sourceEnum.name())) return enumValue;
        }
        throw new RuntimeException("No matching enum");
    }

    static <T extends Enum<?>> T toEnum(Class<T> targetEnumClass, String enumName) {
        if (Strings.isBlank(enumName)) return null;
        for (T enumValue : targetEnumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(enumName)) return enumValue;
        }
        throw new RuntimeException("No matching enum");
    }

    static <T extends Enum<?>> T toEnumSafe(Class<T> targetEnumClass, String enumName) {
        if (Strings.isBlank(enumName)) return null;
        for (T enumValue : targetEnumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(enumName)) return enumValue;
        }
        return null;
    }

    static Long concatLong(Number... numbers) {
        StringBuilder concatenatedString = new StringBuilder();
        for (Number number : numbers) {
            if (number != null) {
                concatenatedString.append(number);
            }
        }
        return toLong(concatenatedString.toString());
    }

    static Integer concatInteger(Number... numbers) {
        StringBuilder concatenatedString = new StringBuilder();
        for (Number number : numbers) {
            if (number != null) {
                concatenatedString.append(number);
            }
        }
        return toInteger(concatenatedString.toString());
    }
}
