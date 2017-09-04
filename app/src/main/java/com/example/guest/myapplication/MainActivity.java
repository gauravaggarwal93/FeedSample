package com.example.guest.myapplication;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import fragments.BaseFragment;
import fragments.FlightSearchFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY";
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFlightSearchFragment();
    }

    private void startFlightSearchFragment() {
        FlightSearchFragment flightSearchFragment = new FlightSearchFragment();
        startFragmentTransition(flightSearchFragment);
    }

    public void startFragmentTransition(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentByTag(((BaseFragment) fragment).getFragmentTag()) == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment, ((BaseFragment) fragment).getFragmentTag());
            fragmentTransaction.addToBackStack(((BaseFragment) fragment).getFragmentTag());
            fragmentTransaction.commit();
        }else {
            Log.d(TAG, "Existing Fragment found");
        }

    }

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }
}
