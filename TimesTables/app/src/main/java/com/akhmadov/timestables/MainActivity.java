package com.akhmadov.timestables;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView timeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeList = (ListView) findViewById(R.id.timeList);


        final SeekBar timeSeek = (SeekBar) findViewById(R.id.timeSeek);

        timeSeek.setMax(20);
        timeSeek.setProgress(10);
        generateTimesTable(10);

        timeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;
                if (progress < min){
                    timesTable = min;
                    timeSeek.setProgress(timesTable);
                }
                else {
                    timesTable = progress;
                }
                generateTimesTable(timesTable);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void generateTimesTable(int timesTable){

        ArrayList<String> timesString = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, timesString);

        for (int i = 1; i <= 10; i++){
            timesString.add(Integer.toString(timesTable * i));
        }

        timeList.setAdapter(arrayAdapter);

    }
}
