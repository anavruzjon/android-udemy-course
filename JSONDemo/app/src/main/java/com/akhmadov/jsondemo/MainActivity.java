package com.akhmadov.jsondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/weather?q=Dushanbe&appid=3b4b49c4a22dfb3e815ada6b194d8cac");
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            InputStreamReader reader = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                inputStream = urlConnection.getInputStream();

                reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }
                System.out.println("This is gated data: " + result.toString());
                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.d("myLogs", "main " + jsonPart.getString("main"));
                    Log.d("myLogs", "description " + jsonPart.getString("description"));
                }


                JSONObject mainInfo = jsonObject.getJSONObject("main");
                double temp = mainInfo.getDouble("temp");
                double temp_min = mainInfo.getDouble("temp_min");
                double temp_max = mainInfo.getDouble("temp_max");
                temp -= 273;
                temp_min -= 273;
                temp_max -= 273;
                Log.d("myLogs", "temp " + Double.toString(temp));
                Log.d("myLogs", "temp_min " + Double.toString(temp));
                Log.d("myLogs", "temp_max " + Double.toString(temp));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }


}
