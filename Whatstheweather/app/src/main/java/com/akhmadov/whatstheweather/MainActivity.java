package com.akhmadov.whatstheweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText cityName;
    TextView weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (EditText) findViewById(R.id.ciyName);
        weatherInfo = (TextView) findViewById(R.id.weatherInfo);
    }

    public void findTheWeather(View view) {
        System.out.println("cityName: " + cityName.getText());
        GetTheWeatherTask task = new GetTheWeatherTask(weatherInfo);
        task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + cityName.getText().toString()
                + "&appid=3b4b49c4a22dfb3e815ada6b194d8cac");

    }

    public class GetTheWeatherTask extends AsyncTask<String, Void, String> {

        WeakReference<TextView> weatherInfo;

        public GetTheWeatherTask(TextView weatherInfo) {
            this.weatherInfo = new WeakReference<>(weatherInfo);
        }

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

                while (data != -1){
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }
                return result.toString();
            } catch (Exception e){
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray array = new JSONArray(weatherInfo);

                StringBuilder message = new StringBuilder();
                for (int i = 0; i < array.length(); i++){
                    JSONObject jsonPart = array.getJSONObject(i);
                    message.append("main: ").append(jsonPart.getString("main")).append("\n");
                    message.append("description: ").append(jsonPart.getString("description")).append("\n");
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
                message.append("temp: ").append(String.format("%.1f", temp)).append("\n");
                message.append("temp_min: ").append(String.format("%.1f", temp_min)).append("\n");
                message.append("temp_max: ").append(String.format("%.1f", temp_max)).append("\n");
                if (message.length() != 0) {
                    this.weatherInfo.get().setVisibility(View.VISIBLE);
                    this.weatherInfo.get().setText(message.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
}
