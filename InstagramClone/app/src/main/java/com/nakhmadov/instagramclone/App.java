package com.nakhmadov.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8c5a7f89d45b9593b90a272a5f589c87dcccdec6")
                .clientKey("4783a6cda2d62a5f73d239b158428f6d9b3eb617")
                .server("http://18.191.134.106:80/parse/")
                .build()
        );
    }
}
