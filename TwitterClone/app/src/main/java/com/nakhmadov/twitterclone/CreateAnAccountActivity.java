package com.nakhmadov.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAnAccountActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);
        usernameEditText = (EditText) findViewById(R.id.create_account_username);
        passwordEditText = (EditText) findViewById(R.id.create_account_password);
        emailEditText = (EditText) findViewById(R.id.create_account_email);
        emailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    signUp(v);
                }
                return false;
            }
        });

    }

    public void goBack(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void signUp(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        final String email = emailEditText.getText().toString();

        final TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if (!isEmail(email)) {
            errorMessage.setText(getString(R.string.incorrect_email));
            errorMessage.setVisibility(View.VISIBLE);
        } else {
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Succesfully signed up", Toast.LENGTH_LONG).show();
                        Intent toMainActivity = new Intent();
                        setResult(RESULT_OK, toMainActivity);
                        finish();
                    } else {
                        errorMessage.setText(e.getMessage());
                        errorMessage.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }

    public boolean isEmail(String emailAddress) {
        int emailCharacterCount = 0;
        int dotCharacterCount = 0;
        int emailCharacterPosition = emailAddress.length() + 1;

        for (int i = 0; i < emailAddress.length(); i++) {
            if (emailAddress.charAt(i) == '@') {
                emailCharacterCount++;

                emailCharacterPosition = i;
            }
            if (emailAddress.charAt(i) == '.' && i > emailCharacterPosition + 1) {
                dotCharacterCount++;
            }
        }
        Log.i("myLogs", String.valueOf(emailCharacterCount));

        return dotCharacterCount == 1 && emailCharacterCount == 1 && emailCharacterPosition <= emailAddress.length();
    }

    public void rootOnClick(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
