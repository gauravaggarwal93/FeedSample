package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Gaurav on 10/7/17.
 */

public class Utils {

    public static void runOnThread(Runnable runnable){
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
    public static boolean isEmpty(JSONArray array) {
        return array == null || array.length() == 0;
    }

    public static Date getDate(long timestamp) {
        return new Date(timestamp * 1000L);
    }

}
