package com.nakhmadov.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

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
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void getStarted(View view) {
        isRider = !riderDriverSwitch.isChecked();


    }
}
