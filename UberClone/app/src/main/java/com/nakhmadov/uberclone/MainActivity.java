package com.nakhmadov.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    Switch riderDriverSwitch;
    Boolean isRider = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        riderDriverSwitch = (Switch) findViewById(R.id.riderDriverSwitch);
        isRider = !riderDriverSwitch.isChecked();

        if (ParseUser.getCurrentUser() == null) {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.d("myLogs", "Anonymous login successful");

                    } else {
                        Log.d("myLogs", "There was some error! ->" + e.getMessage());
                    }
                }
            });
        } else {
            if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {
                String userType = "rider";
                if (!isRider) {
                    userType = "driver";
                    Log.d("myLogs", "Redirecting as driver");
                } else {
                    Log.d("myLogs", "Redirecting as rider");
                }
                redirectActivity();
            }
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void getStarted(View view) {
        isRider = !riderDriverSwitch.isChecked();

        String userType = "rider";
        if (!isRider) {
            userType = "driver";
            Log.d("myLogs", "Redirecting as driver");
        } else {
            Log.d("myLogs", "Redirecting as rider");
        }
        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                redirectActivity();
            }
        });

    }

    public void redirectActivity(){
        if (ParseUser.getCurrentUser().get("riderOrDriver").equals("rider")){
            Intent intent = new Intent(getApplicationContext(), RiderActivity.class);
            startActivity(intent);
        } else {

        }
    }
}
