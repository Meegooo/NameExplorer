package meegoo.nameExplorer.util;


import java.util.Calendar;
import java.util.TimeZone;

public class TimeHelper {
    private static Calendar currentCalendar;
    private static String monthStringFromInt;
    private static int monthIntFromString;
    private static String dayStringFromInt;
    private static int monthInt;
    public static int getCurrentMonth() {

        currentCalendar = Calendar.getInstance();
        monthInt = currentCalendar.get(Calendar.MONTH);
        return monthInt;
    }

    public static String getMonthStringFromInt(int monthInt) {
        switch (monthInt){

            case 0:  monthStringFromInt = "January"; break;
            case 1:  monthStringFromInt = "February"; break;
            case 2:  monthStringFromInt = "March"; break;
            case 3:  monthStringFromInt = "April"; break;
            case 4:  monthStringFromInt = "May"; break;
            case 5:  monthStringFromInt = "June"; break;
            case 6:  monthStringFromInt = "July"; break;
            case 7:  monthStringFromInt = "August"; break;
            case 8:  monthStringFromInt = "September"; break;
            case 9:  monthStringFromInt = "October"; break;
            case 10: monthStringFromInt  = "November"; break;
            case 11: monthStringFromInt  = "December"; break;
        }
        return monthStringFromInt;
    }
    public static String getDayStringFromInt(int dayInt) {
        switch (dayInt){

            case 2:  dayStringFromInt = "Monday"; break;
            case 3:  dayStringFromInt = "Tuesday"; break;
            case 4:  dayStringFromInt = "Wednesday"; break;
            case 5:  dayStringFromInt = "Thursday"; break;
            case 6:  dayStringFromInt = "Friday"; break;
            case 7:  dayStringFromInt = "Saturday"; break;
            case 1:  dayStringFromInt = "Sunday"; break;

        }
        return dayStringFromInt;
    }

    public static String getMonthIntFromString(String monthString) {
        switch (monthString){

            case "January" :    monthIntFromString  = 0;   break;
            case "February":    monthIntFromString  = 1;   break;
            case "March"   :    monthIntFromString  = 2;   break;
            case "April"   :    monthIntFromString  = 3;   break;
            case "May"     :    monthIntFromString  = 4;   break;
            case "June"    :    monthIntFromString  = 5;   break;
            case "July"    :    monthIntFromString  = 6;   break;
            case "August"  :    monthIntFromString  = 7;   break;
            case "September":   monthIntFromString  = 8;   break;
            case "October" :    monthIntFromString  = 9;   break;
            case "November":    monthIntFromString  = 10;   break;
            case "December":    monthIntFromString  = 11;   break;
        }
        return monthStringFromInt;
    }

    public static String parseUnixTimestampIntoString(long timestamp) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - calendar.get(Calendar.DST_OFFSET));
        String output, output_day, hour,minute,second;
        output_day =
                getDayStringFromInt(calendar.get(Calendar.DAY_OF_WEEK)).substring(0,3) + ", "
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + getMonthStringFromInt(calendar.get(Calendar.MONTH)).substring(0,3) + " "
                + calendar.get(Calendar.YEAR);
        hour = (((Integer)(calendar.get(Calendar.HOUR_OF_DAY))).toString().length() == 1) ? ("0" + ((Integer)(calendar.get(Calendar.HOUR_OF_DAY))).toString()) : (((Integer)(calendar.get(Calendar.HOUR_OF_DAY))).toString());
        minute = (((Integer)(calendar.get(Calendar.MINUTE))).toString().length() == 1) ? ("0" + ((Integer)(calendar.get(Calendar.MINUTE))).toString()) : (((Integer)(calendar.get(Calendar.MINUTE))).toString());
        second = (((Integer)(calendar.get(Calendar.SECOND))).toString().length() == 1) ? ("0" + ((Integer)(calendar.get(Calendar.SECOND))).toString()) : (((Integer)(calendar.get(Calendar.SECOND))).toString());
        output = output_day + " " + hour + ":" + minute + ":" + second + " GMT";




        return output;

    }

}
