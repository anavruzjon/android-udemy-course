package com.nakhmadov.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean signUpMode = true;
    EditText usernameET;
    EditText passwordET;
    ConstraintLayout layout;
    ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);

        layout = (ConstraintLayout) findViewById(R.id.backgroundLayout);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);

        layout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        passwordET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    signUp(v);
                }

                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null){
            showUserList();
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }

    public void signUp(View view) {



        if (signUpMode) {
            signUpUser(usernameET.getText().toString(), passwordET.getText().toString());
        } else {
            loginUser(usernameET.getText().toString(), passwordET.getText().toString());
        }
    }

    public void signUpModeOnClick(View view) {
        Button signUpButton = (Button) findViewById(R.id.singUpButton);
        if (signUpMode) {
            signUpButton.setText("Login");
            ((TextView) view).setText("or, Signup");
            signUpMode = false;
        } else {
            signUpButton.setText("Sign Up");
            ((TextView) view).setText("or, Login");
            signUpMode = true;
        }
    }


    public void loginUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        if (username.equals("") || password.equals(""))
            Toast.makeText(MainActivity.this, "A username and password are required!", Toast.LENGTH_LONG).show();
        else {
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null && e == null){
                        Log.d("MyLogs", "Login Successful");
                        showUserList();
                    }
                    else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void signUpUser(String username, String password) {
        if (username.equals("") || password.equals(""))
            Toast.makeText(MainActivity.this, "A username and password are required!", Toast.LENGTH_LONG).show();
        else {
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("MyLogs", "Successful");
                        showUserList();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backgroundLayout || v.getId() == R.id.logoImageView){
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showUserList(){
        Intent intent = new Intent(MainActivity.this, UserListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
