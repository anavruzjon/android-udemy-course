package com.nakhmadov.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            //myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            /*myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Rob', 34)");
            myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Navruzjon', 22)");
            myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Alice', 25)");
            myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('John', 43)");
            myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Ralpie', 15)");
            myDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Kristen', 21)");*/
            //myDatabase.execSQL("DELETE FROM users WHERE name='Rob'");

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS NewUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            /*myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Rob', 34)");
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Navruzjon', 22)");
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Alice', 25)");
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('John', 43)");
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Ralpie', 15)");
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Kristen', 21)");*/
            myDatabase.execSQL("INSERT INTO NewUsers(name, age) VALUES ('Kristen', 21)");
            myDatabase.execSQL("DELETE FROM NewUsers WHERE id = 2");

            Cursor c = myDatabase.rawQuery("SELECT * FROM NewUsers", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (c != null){
                Log.d("myLogs", c.getString(nameIndex));
                Log.d("myLogs", String.valueOf(c.getInt(ageIndex)));
                Log.d("myLogs", String.valueOf(c.getInt(idIndex)));

                c.moveToNext();
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        /*try {
            SQLiteDatabase events = (SQLiteDatabase) this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            events.execSQL("create table if not exists events(event VARCHAR, year INT(4))");
            events.execSQL("insert into events(event, year) values('End of WW2', 1945)");
            events.execSQL("insert into events(event, year) values('Wham split up', 1986)");

            Cursor c = events.rawQuery("select * from events", null);
            int eventIndex = c.getColumnIndex("event");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();
            while (c != null) {
                Log.d("myLogs", "Result event: " + c.getString(eventIndex));
                Log.d("myLogs", "Result year: " + String.valueOf(c.getInt(yearIndex)));
                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
