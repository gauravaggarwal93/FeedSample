package utils;

/**
 * Created by Gaurav on 9/4/17.
 */

public class TimeUtils {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    public static String getTime(long timestamp) {
        StringBuffer text = new StringBuffer("");
        if (timestamp > DAY) {
            timestamp %= DAY;
        }
        if (timestamp > HOUR) {
            text.append(timestamp / HOUR).append(" : ");
            timestamp %= HOUR;
        }
        if (timestamp > MINUTE) {
            text.append(timestamp / MINUTE);
            timestamp %= MINUTE;
        }
        if (timestamp > SECOND) {
            timestamp %= SECOND;
        }
        text.append(timestamp + " timestamp");
        return text.toString();
    }
}
