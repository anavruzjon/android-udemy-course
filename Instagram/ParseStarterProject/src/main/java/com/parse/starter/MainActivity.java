/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ParseObject score = new ParseObject("Score");
        score.put("username", "rob");
        score.put("score", 22);
        //score.put("objectId", "1");
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.d("myLogs", "Successful");
                }
                else {
                    Log.d("myLogs", "Failed! Error" + e.toString());
                }
            }
        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("OGIZPFZ2Xq", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    object.put("score", 120);
                    object.saveInBackground();

                    Log.d("myLogs", "Username: " + object.getString("username"));
                    Log.d("myLogs", "Score: " + String.valueOf(object.getInt("score")));
                } else {
                    Log.d("myLogs", "Some error: " + e.getStackTrace().toString());

                }
            }


        });*/

        /*ParseObject tweet = new ParseObject("Tweet");

        tweet.put("username", "navruzjon");
        tweet.put("tweet", "This is Navruzjon's tweet");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d("myLogs", "It's done: 1 tweets are added and saved");
            }
        });

        ParseObject tweetNew = new ParseObject("Tweet");

        tweetNew.put("username", "Robbie");
        tweetNew.put("tweet", "This is Robbie's tweet");

        tweetNew.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d("myLogs", "It's done: 1 tweets are added and saved");
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");

        query.getInBackground("alACIL5gEP", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put("username", "Kristen");
                object.saveInBackground();

                Log.d("myLogs", "Tweet: " + object.get("tweet"));
            }
        });
*/

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereEqualTo("username", "Tommy");
        query.setLimit(1);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("myLogs", "Find in background: Retrieved " + objects.size() + " objects");

                    if (objects.size() > 0){
                        for (ParseObject object : objects){
                            Log.d("myLogs", "Object: " + String.valueOf(object.getInt("score")));
                        }
                    }
                }
            }
        });

        */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereGreaterThan("score", 20);
        query.whereLessThan("score", 100);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject object:objects){
                        object.put("score", object.getInt("score") + 50);
                        object.saveInBackground();
                    }
                }
            }
        });


        /*ParseUser user = new ParseUser();
        user.setUsername("akhmadov");
        user.setPassword("myPass");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.d("myLogs", "Sign Up: Successful");
                } else {
                    Log.d("myLogs", "Sign Up: Failed");
                }
            }
        });*/

        /* ParseUser.logInInBackground("akhmadov", "pass", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.d("myLogs", "Login Successful");
                } else {
                    Log.d("myLogs", "Failed: " + e.toString());
                }
            }
        });*/

        /*ParseUser.logOut();

        if (ParseUser.getCurrentUser() != null) {
            Log.d("myLogs", "User logged in: Username: " + ParseUser.getCurrentUser().getUsername());
        } else {
            Log.d("myLogs", "User not logged in");

        }*/
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

}
