package com.akhmadov.gridlayoutdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int [] paths = {R.raw.goodevening, R.raw.doyouspeakenglish, R.raw.hello, R.raw.howareyou,
            R.raw.ilivein, R.raw.mynameis, R.raw.please, R.raw.welcome};

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onItemClicked(View view) {
        int clickedPosition = getClickedPosition(view.getId());
        mediaPlayer = MediaPlayer.create(MainActivity.this, paths[clickedPosition]);
        mediaPlayer.start();
    }

    public int getClickedPosition(int id){
        int position = 0;
        switch (id){
            case R.id.goodEveningBtn:
                position = 0;
                break;
            case R.id.doYouSpeakEnglishBtn:
                position = 1;
                break;
            case R.id.helloBtn:
                position = 2;
                break;
            case R.id.howAreYouBtn:
                position = 3;
                break;
            case R.id.iLiveInBtn:
                position = 4;
                break;
            case R.id.myNameIsBtn:
                position = 5;
                break;
            case R.id.pleaseBtn:
                position = 6;
                break;
            case R.id.welcomeBtn:
                position = 7;
                break;
        }
        return position;
    }

}
