package com.axis.caravela.commons.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class StringUtils.
 */
public class Strings extends org.apache.commons.lang3.StringUtils {

    /**
     * Alias for equals to avoid confusion with object's equals method
     */
    public static boolean areEqual(String str1, String str2) {
        return equals(str1, str2);
    }

    /**
     * Returns true if the given value str1 equals any of the other values.
     */
    public static boolean equalsAny(String str1, String... strOther) {
        for (String strX : strOther) {
            if (equals(str1, strX)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given values are not equal.
     */
    public static boolean notEquals(String str1, String str2) {
        return !equals(str1, str2);
    }

    /**
     * Returns true if the given value str1 is not equal to any of the other values.
     */
    public static boolean notEqualsAny(String str1, String... strOther) {
        return !equalsAny(str1, strOther);
    }

    /**
     * Returns true if the given value str1 does not end with the value str2.
     */
    public static boolean notEndsWith(String str1, String str2) {
        return !endsWith(str1, str2);
    }

    /**
     * Returns true if the given value contains only spaces or zeros.
     */
    public static boolean containsOnlySpacesOrZeros(String value) {
        return containsOnly(value, " 0");
    }

    /**
     * Returns true if the given value does not contain only spaces or zeros.
     */
    public static boolean containsNotOnlySpacesOrZeros(String value) {
        return !containsOnlySpacesOrZeros(value);
    }

    /**
     * Checks if the given value string with any of the given prefixes.
     */
    public static boolean startsWithAny(String str, String... prefixes) {
        for (String prefix : prefixes) {
            if (startsWith(str, prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an array of Strings if parameters were finded
     * between 'beginChar' and 'endChar' as delimiters into gived string 'value'
     */
    public static String[] extractParameters(String value, char beginChar, char endChar) {
    	String[] tab = new String[0];
    	if (value != null) {
    		// find indexes
    		List<Integer> beginIndex = new ArrayList<>();
    		List<Integer> endIndex = new ArrayList<>();
    		char[] tabChar = value.toCharArray();
    		for (int i = 0 ; i < value.length() ; i++) {
    			if ( tabChar[i] == beginChar) {
    				beginIndex.add(i+1);
    			} else if ( tabChar[i] == endChar) {
    				endIndex.add(i);
    			}
    		}
    		// get parameters
    		if (beginIndex.size() == endIndex.size()){
    			tab = new String[beginIndex.size()];
    			for (int i = 0 ; i < beginIndex.size() ; i++) {
    				tab[i] = value.substring(beginIndex.get(i), endIndex.get(i));
    			}
    		}
    	}
    	return tab;
    }

    /**
     * Checks is the given value is numeric with an optional leading sign.
     */
    public static boolean isNumericSigned(String value) {
        if (startsWithAny(value, "+", "-")) {
            return isNumeric(substring(value, 1));
        }
        return isNumeric(value);
    }

    /**
     * Returns true if the given value is not numeric.
     */
    public static boolean isNotNumeric(String value) {
        return !isNumeric(value);
    }

    /**
     * Checks if the length of the given value not equals to the given length.
     */
    public static boolean lengthNotEquals(String value, Integer length) {
        return !lengthEquals(value, length);
    }

    /**
     * Checks if the length of the given value equals the given length.
     */
    public static boolean lengthEquals(String value, Integer length) {
        return length(value) == length;
    }

    /**
     * Checks if the length of the given value equals any of the given lengths.
     */
    public static boolean lengthEqualsAny(String value, Integer... lengths) {
        return Arrays.asList(lengths).contains(length(value));
    }

    /**
     * Checks if the length of the given value not equals any of the given lengths.
     */
    public static boolean lengthNotEqualsAny(String value, Integer... lengths) {
        return !lengthEqualsAny(value, lengths);
    }

    public static boolean lengthMoreThan(String value, Integer length) {
        return length(value) > length;
    }

    /**
     * Increments the given numeric string with the given increment.
     */
    public static String incrementNumericString(String numericString, Integer increment) {
        Long numericValue = Long.valueOf(numericString);
        return String.valueOf(numericValue + increment);
    }

    /**
     * Returns the given value without any of the characters to remove.
     */
    public static String removeAny(String value, String removeValues) {
        String strippedValue = value;
        for (char charToRemove : removeValues.toCharArray()) {
            strippedValue = remove(strippedValue, charToRemove);
        }
        return strippedValue;
    }

    public static String removeSpaces(String value) {
        return Strings.replace(value, " ", "");
    }

    /**
     * Returns the concatenation of the given pars, separated with a space.
     * @param parts
     */
    public static String joinWithSpaces(String... parts) {
        return join(Arrays.asList(parts), " ");
    }

    /**
     * Splits the given string with the given separator char to a list.
     */
    public static List<String> splitToList(String str, String separatorChar) {
        String[] split = split(str, separatorChar);
        if (split == null || split.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(split).collect(Collectors.toList());
    }

    /**
     * Returns the character at the given index as a string
     */
    public static String stringAtIndex(String str, int index) {
        if (str == null || index >= str.length()) {
            return null;
        }
        return "" + str.charAt(index);
    }


    /**
     * Returns the number (Long) as a string with, minimal, the specified number of digits
     */
    public static String toStringWithMinDigits(Long number, int numberOfDigits){
        StringBuilder numberString = new StringBuilder();
        numberString.append(number.toString());
        int i = numberString.length();
        while(i++ < numberOfDigits) numberString.insert(0, "0");
        return numberString.toString();
    }

    /**
     * Returns the number (Integer) as a string with, minimal, the specified number of digits
     */
    public static String toStringWithMinDigits(Integer number, int numberOfDigits){
        return toStringWithMinDigits(Long.valueOf(number), numberOfDigits);
    }

    public static boolean isNotAlphanumeric(String value) {
        return !isAlphanumeric(value);
    }

    /**
     * Replaces leading Zero only if its a numeric. Not with alphanumeric
     * @param str
     * @return String without leading zeros
     */
    public static String replaceLeadingZero(String str){
        return str.replaceAll("^0+(?=\\d+$)", "");
    }

    /**
     * Adds 0 prefix to a string for a given length or <code>null</code> if the
     * string is blank.
     *
     * @param str is the String to modify
     * @param length is the difference between <code>str.length()</code> and the wanted number of zeros as prefix.
     * @return the modified string with added 0's as prefix.
     * @throws IllegalArgumentException
     */
    public static String zeroPaddedOrNullIfBlank(String str, int length) {
        boolean isBlank = isBlank(str);

        if(isBlank){
            return null;
        }

        if (length < 1){
            throw new IllegalArgumentException("Length should be greater than 0.");
        }

        if (str.length() > length) {
            throw new IllegalArgumentException("Length should be greater or have the same length as string.");
        }

        return Conversions.toStringZeroPadded(str, length);
    }

    public static String concat(Object... values) {
        return join(values, "");
    }
}
