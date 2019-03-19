package com.nakhmadov.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView message = (TextView) findViewById(R.id.message);
        Intent intent = getIntent();
        message.setText("Profile " + intent.getStringExtra("username"));

    }
}
