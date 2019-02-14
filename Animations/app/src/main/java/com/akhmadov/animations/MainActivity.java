package com.akhmadov.animations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fadeBart(View view) {
        /*ImageView bart = (ImageView) findViewById(R.id.bart);
        ImageView homer = (ImageView) findViewById(R.id.homer);

        if (bart.getAlpha() > homer.getAlpha()) {
            bart.animate().alpha(0.0f).setDuration(2000);
            homer.animate().alpha(1.0f).setDuration(2000);
        } else {
            homer.animate().alpha(0.0f).setDuration(2000);
            bart.animate().alpha(1.0f).setDuration(2000);
        }*/

        ImageView bart = (ImageView) findViewById(R.id.bart);

        //bart.animate().translationXBy(-1000f).setDuration(2000);

        //bart.animate().rotation(-180*5f).setDuration(1000);


        if (bart.getScaleX() != 1.0f && bart.getScaleY() != 1.0f){
            bart.animate().scaleX(1.0f).scaleY(1.0f).rotation(360f).setDuration(2000);
        } else {
            bart.animate().scaleX(0.5f).scaleY(0.5f).rotation(180f).setDuration(2000);
        }
    }

}
