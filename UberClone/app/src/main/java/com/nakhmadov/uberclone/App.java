package com.nakhmadov.uberclone;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("95014ac76487e9b0f40209a9186950333fce9887")
                .clientKey("c1004cd99ac80f9a44050205595c3449ecef4d06")
                .server("http://13.59.108.52:80/parse/")
                .build()
        );



        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
