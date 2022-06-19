package com.axis.caravela.commons.control;


import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author t51sagbse Benny Segers
 * @version %PR%
 */
public class Enums {

    /**
     * Returns an enumeration value of the given enum class matching the given name.
     */
    public static <T extends Enum> T getFromName(String name, Class<T> enumClass) {
        if (Strings.isBlank(name)) {
            return null;
        }
        for (T enumValue : enumClass.getEnumConstants()) {
            if (Strings.equalsIgnoreCase(name, enumValue.name())) {
                return enumValue;
            }
        }
        return null;
    }




    /**
     * Returns the name of the given enum value.
     */
    public static String getName(Enum enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }


    /**
     * Checks if the given enum value is equal to any of the other enum values.
     */
    public static <T extends Enum> boolean equalsAny(T enumValue, T... otherEnumValues) {
        return Arrays.asList(otherEnumValues).contains(enumValue);
    }

    /**
     * Checks if the given enum value is not equal to any of the other enum values.
     */
    public static <T extends Enum> boolean equalsNone(T enumValue, T... otherEnumValues) {
        return !equalsAny(enumValue, otherEnumValues);
    }

    private static String getLookupCode(String code) {
        String lookupCode = code;
        if (Strings.length(code) > 1 && Strings.isNumeric(code)) {
            lookupCode = Conversions.toString(Conversions.toInteger(code));
        }
        return lookupCode;
    }

    private static <T extends Enum> Field getField(T enumValue) {
        try {
            return enumValue.getDeclaringClass().getDeclaredField(enumValue.name());
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }
}
