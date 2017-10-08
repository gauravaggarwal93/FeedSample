package application;

import android.app.Application;


/**
 * Created by Gaurav on 10/7/17.
 */

public class MyApplicationClass extends Application {

    private static MyApplicationClass sINSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        sINSTANCE = this;
    }

    public static MyApplicationClass getInstance() {
        return sINSTANCE;
    }
}
