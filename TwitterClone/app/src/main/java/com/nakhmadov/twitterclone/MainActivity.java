package com.nakhmadov.twitterclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_LOGIN = 100;
    public static final int REQUEST_CODE_SIGNUP = 101;
    public static final int REQUEST_CODE_SIGNUP_LOGIN = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        //ParseUser.logOut();

        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), SuccessfullyLoginActivity.class);
            startActivity(intent);
            finish();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void login(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, REQUEST_CODE_LOGIN);
    }

    public void signUp(View view) {
        Intent i = new Intent(this, CreateAnAccountActivity.class);
        startActivityForResult(i, REQUEST_CODE_SIGNUP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOGIN) {


                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SuccessfullyLoginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
            if (requestCode == REQUEST_CODE_SIGNUP) {
                Intent i = new Intent(this, LoginActivity.class);
                startActivityForResult(i, REQUEST_CODE_SIGNUP_LOGIN);
            }
            if (requestCode == REQUEST_CODE_SIGNUP_LOGIN) {
                Intent intent = new Intent(getApplicationContext(), SuccessfullyLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
