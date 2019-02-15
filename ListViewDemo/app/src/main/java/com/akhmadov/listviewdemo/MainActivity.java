package com.akhmadov.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        final ArrayList<String> myFamily = new ArrayList<>();

        myFamily.add("Navruzjon");
        myFamily.add("Shahrom");
        myFamily.add("Dilnoza");
        myFamily.add("Munavvara");
        myFamily.add("Mirali");
        myFamily.add("Mavluda");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, myFamily);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("myLogs", arrayAdapter.getItem(position));
                Toast.makeText(MainActivity.this,
                        "Hello " + arrayAdapter.getItem(position), Toast.LENGTH_LONG).show();

            }
        });

    }
}
