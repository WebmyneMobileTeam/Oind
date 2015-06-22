package com.android.oindexter.helpers;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

/**
 * Created by Android on 01-06-2015.
 */
public class OindexterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(getApplicationContext(), "mCeu1NZZxuMe2EZq6yvTJ0UIbbvLC5FCMNE28knc", "Fcrh6PPlQWtgPxC5xTSuQVTqohrS9lvXcYl1aUAb");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);



    }
}
