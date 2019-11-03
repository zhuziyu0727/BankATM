import java.util.Arrays;
import java.util.List;

/**
 * Store a specific date with day, month, and year
 */
public class Date {
    // instance variables with private access
    private int day;
    private int month;
    private int year;

    // constructors
    /**
     * The order of assigning the three attributes matters
     * First, assign year, year should be a positive integer
     * Second, assign month, month should be between 1 and 12 (inclusive)
     * At last, assign day, calculate the threshold of day by year and month
     * The order makes sure that when assigning day, year and month must be existing
     */
    public Date(int day, int month, int year) {
        setDate(day, month, year);
    }

    public Date() {
        this(1,1,2000);
    }

    // accessor functions
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // mutator functions
    public void setDate(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void setDay(int day) {
        checkDay(day, month, year);
        this.day = day;
    }

    public void setMonth(int month) {
        checkMonth(month);
        this.month = month;
    }

    public void setYear(int year) {
        checkYear(year);
        this.year = year;
    }

    // private static check functions
    private static void checkDay(int day, int month, int year) {
        int possibleMaxDay = calculateMaxDay(month, year);
        if (day < 1) {
            throw new IllegalArgumentException("Day attribute must be positive.");
        } else if (day > possibleMaxDay) {
            String alertFormat = "Day attribute \"%d\" exceeds the maximum day %d in month %d of year %d";
            String alert = String.format(alertFormat, day, possibleMaxDay, month, year);
            throw new IllegalArgumentException(alert);
        }
    }

    private static void checkMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month attribute must be between 1 and 12.");
        }
    }

    private static void checkYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year attribute must be a positive integer.");
        }
    }

    // static functions
    public static int calculateMaxDay(int month, int year) {
        checkMonth(month);
        checkYear(year);
        Integer[] monthsOf31 = new Integer[]{1, 3, 5, 7, 8, 10, 12};
        Integer[] monthsOf30 = new Integer[]{4, 6, 9, 11};
        List<Integer> monthsOf31List = Arrays.asList(monthsOf31);
        List<Integer> monthsOf30List = Arrays.asList(monthsOf30);
        if (monthsOf31List.contains(month)) {
            return 31;
        } else if (monthsOf30List.contains(month)) {
            return 30;
        } else if (isLeapYear(year)) {
            return 29;
        } else {
            return 28;
        }
    }

    /**
     * If year can be exactly divided by 4, it is a leap year
     * Except if year can be exactly divided by 100, it is not a leap year
     * Except if year can be exactly divided by 400, it is a leap year
     */
    public static boolean isLeapYear(int year) {
        checkYear(year);
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // override function
    @Override
    public String toString() {
        String dateFormat = "%s/%s/%s";
        String date = String.format(dateFormat, day, month, year);
        return date;
    }
}
