package com.akhmadov.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    AudioManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = MediaPlayer.create(this, R.raw.music);
        aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        SeekBar volumeSeek = (SeekBar) findViewById(R.id.volumeSeek);
        final SeekBar seekConfiguration = (SeekBar) findViewById(R.id.seekConfiguration);

        volumeSeek.setMax(aManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeek.setProgress(aManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                aManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekConfiguration.setMax(mPlayer.getDuration());
        seekConfiguration.setProgress(mPlayer.getCurrentPosition());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekConfiguration.setProgress(mPlayer.getCurrentPosition());
            }
        }, 0, 200);


        seekConfiguration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("myLogs", "Stop tracking " + seekBar.getProgress());
                mPlayer.seekTo(seekBar.getProgress());
            }
        });
    }


    public void startResumeOnClick(View view) {
        mPlayer.start();
    }

    public void pauseOnClick(View view) {
        mPlayer.pause();
    }
}
