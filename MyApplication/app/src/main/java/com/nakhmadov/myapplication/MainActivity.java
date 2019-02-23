package com.nakhmadov.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    SQLiteDatabase articleDB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);


        articleDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);
        articleDB.execSQL("CREATE TABLE IF NOT EXISTS articles(id INTEGER PRIMARY KEY, articleId INTEGER, title VARCHAR, content VARCHAR)");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("content", contents.get(position));
                startActivity(intent);
            }
        });

        updateListView();

        DownloadTask task = new DownloadTask();

        try {
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
            // String res = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateListView() {
        Cursor c = articleDB.rawQuery("SELECT * FROM articles", null);
        int contentIndex = c.getColumnIndex("content");
        int titleIndex = c.getColumnIndex("title");

        if (c.moveToFirst()) {
            titles.clear();
            contents.clear();

            do {
                titles.add(c.getString(titleIndex));
                contents.add(c.getString(contentIndex));
            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();
                while (data != -1) {
                    result.append((char) data);
                    data = reader.read();
                }

                JSONArray jsonArray = new JSONArray(result.toString());
                int numberOfItems = 20;
                if (jsonArray.length() < 20)
                    numberOfItems = jsonArray.length();

                articleDB.execSQL("DELETE FROM articles");

                for (int i = 0; i < numberOfItems; i++) {
                    StringBuilder articleInfo = new StringBuilder();
                    String articleId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    inputStream = urlConnection.getInputStream();

                    reader = new InputStreamReader(inputStream);

                    data = reader.read();
                    while (data != -1) {
                        articleInfo.append((char) data);
                        data = reader.read();
                    }

                    JSONObject jsonObject = new JSONObject(articleInfo.toString());
                    String articleTitle = "";
                    String articleURL = "";
                    if (!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        articleTitle = jsonObject.getString("title");
                        articleURL = jsonObject.getString("url");


                        StringBuilder articleContent = new StringBuilder();
                        url = new URL(articleURL);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        inputStream = urlConnection.getInputStream();

                        reader = new InputStreamReader(inputStream);

                        data = reader.read();
                        while (data != -1) {
                            articleContent.append((char) data);
                            data = reader.read();
                        }

                        //Log.d("myLogs", articleContent.toString());

                        String sql = "INSERT INTO articles(articleId, title, content) VALUES (?, ?, ?)";
                        SQLiteStatement statement = articleDB.compileStatement(sql);
                        statement.bindString(1, articleId);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent.toString());
                        statement.execute();

                    }

                }

                return result.toString();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
        }
    }
}
