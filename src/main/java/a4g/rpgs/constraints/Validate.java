package a4g.rpgs.constraints;

import java.util.List;
import java.util.Set;

public final class Validate {
    /* Object validation */
    public static <T> T isNotNull(T object, String argumentName) throws IllegalArgumentException {
        if (object == null)
            throw new IllegalArgumentException(argumentName + " cannot be null");

        return object;
    }

    /* Number validation */
    public static <T extends Number> T isPositive(T number, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() <= 0)
            throw new IllegalArgumentException(argumentName + " needs to be a positive number (higher than 0)");

        return number;
    }
    public static <T extends Number> T isPositiveOrZero(T number, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() < 0)
            throw new IllegalArgumentException(argumentName + " needs to be zero or a positive number (0 or higher)");

        return number;
    }

    public static <T extends Number> T isNegative(T number, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() >= 0)
            throw new IllegalArgumentException(argumentName + " needs to be a negative number (lower than 0)");

        return number;
    }
    public static <T extends Number> T isNegativeOrZero(T number, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() > 0)
            throw new IllegalArgumentException(argumentName + " needs to be zero or a negative number (0 or lower)");

        return number;
    }

    public static <T extends Number> T Max(T number, double maximumValue, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() > maximumValue)
            throw new IllegalArgumentException(argumentName + " needs to be a number lower or equal to " + maximumValue);

        return number;
    }
    public static <T extends Number> T Min(T number, double minimumValue, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() < minimumValue)
            throw new IllegalArgumentException(argumentName + " needs to be a number higher or equal to " + minimumValue);

        return number;
    }
    public static <T extends Number> T inRange(T number, double minimumValue, double maximumValue, String argumentName) throws IllegalArgumentException {
        if (number == null || number.doubleValue() < minimumValue || number.doubleValue() > maximumValue)
            throw new IllegalArgumentException(argumentName + " needs to be a number between " + minimumValue + " and " + maximumValue);

        return number;
    }

    /* String validation */
    public static  <T extends String> T isNotEmpty(T string, String argumentName) throws IllegalArgumentException {
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException(argumentName + " must not be empty");

        return string;
    }

    public static  <T extends String> T isNotBlank(T string, String argumentName) throws IllegalArgumentException {
        if (string == null || string.isBlank())
            throw new IllegalArgumentException(argumentName + " must not be blank");

        return string;
    }

    /* Arrays/Lists */
    public static <T extends List> T isNotEmpty(T list, String argumentName) throws IllegalArgumentException {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException(argumentName + " must not be empty");

        return list;
    }

    public static <T extends Set> T isNotEmpty(T list, String argumentName) throws IllegalArgumentException {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException(argumentName + " must not be empty");

        return list;
    }

    public static <T> T[] isNotEmpty(T[] array, String argumentName) throws IllegalArgumentException {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException(argumentName + " must not be empty");

        return array;
    }
}
