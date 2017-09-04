package application;

import android.app.Application;


/**
 * Created by Gaurav on 9/2/17.
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
