package com.akhmadov.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekTimer;
    Boolean counterIsActive = false;
    Button startStopButton;
    TextView minutesText;
    TextView secondsText;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekTimer = (SeekBar) findViewById(R.id.seekTime);
        minutesText = (TextView) findViewById(R.id.minutesTimer);
        secondsText = (TextView) findViewById(R.id.secondsTimer);
        startStopButton = (Button) findViewById(R.id.startTopButton);

        seekTimer.setMax(10 * 60);
        seekTimer.setProgress(30);
    }

    @Override
    protected void onResume() {
        super.onResume();
        seekTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minProgress = 1;
                long actualProgress;
                if (progress < minProgress)
                    actualProgress = minProgress;
                else
                    actualProgress = progress;

                updateTimer(actualProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startStopTimer(View view) {

        seekTimer.setEnabled(false);
        int minutes = Integer.parseInt(minutesText.getText().toString());
        final int seconds = Integer.parseInt(secondsText.getText().toString());
        int timer = minutes * 60 + seconds;
        Log.d("myLogs", "In seconds: " + timer);


        if (counterIsActive) {
            resetCounter();
            if (countDownTimer != null)
                countDownTimer.cancel();

        } else {
            countDownTimer = new CountDownTimer(timer * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d("myLogs", "Millis until finished: " + millisUntilFinished / 1000);

                    updateTimer((long)  millisUntilFinished / 1000 );
                }

                @Override
                public void onFinish() {

                    resetCounter();

                    Log.d("myLogs", "Finished");
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                    mediaPlayer.start();

                }
            }.start();


            startStopButton.setText("STOP");
            counterIsActive = true;
        }
    }

    public void resetCounter(){

        startStopButton.setText("START");
        secondsText.setText("30");
        minutesText.setText("0");
        seekTimer.setEnabled(true);
        seekTimer.setProgress(30);
        counterIsActive = false;
    }

    public void updateTimer(long seconds){
        minutesText.setText(Long.toString(seconds / 60));
        if (seconds % 60 < 10)
            secondsText.setText("0" + Long.toString(seconds % 60));
        else
            secondsText.setText(Long.toString(seconds % 60));

    }

}
