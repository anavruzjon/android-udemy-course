package com.akhmadov.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button firstCelebrity, secondCelebrity, thirdCelebrity, fourthCelebrity;
    ImageView imageCelebrity;

    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int currentCorrectPosition;
    Button[] buttonsPosition;
    int chooseCelebrity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstCelebrity = (Button) findViewById(R.id.firstCelebrity);
        secondCelebrity = (Button) findViewById(R.id.secondCelebrity);
        thirdCelebrity = (Button) findViewById(R.id.thirdCelebrity);
        fourthCelebrity = (Button) findViewById(R.id.fourthCelebrity);

        buttonsPosition = new Button[]{firstCelebrity, secondCelebrity, thirdCelebrity, fourthCelebrity};

        imageCelebrity = (ImageView) findViewById(R.id.imageCelebrity);

        GetSourceCodeTask getCodeTask = new GetSourceCodeTask();

        StringBuilder code = new StringBuilder("");

        try {
            code = getCodeTask.execute("http://www.posh24.se/kandisar").get();
            //Log.d("myLogs", code.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String code2String = code.toString();

        String[] splitCode = code2String.split("<div class=\"listedArticles\">");

        Pattern p = Pattern.compile("<img src=\"(.*?)\"");
        Matcher m = p.matcher(splitCode[0]);

        while (m.find()) {
            urls.add(m.group(1));
        }

        p = Pattern.compile("alt=\"(.*?)\"");
        m = p.matcher(splitCode[0]);

        while (m.find()) {
            names.add(m.group(1));
        }

        for (int i = 0; i < urls.size(); i++) {
            Log.d("myLogs", urls.get(i) + " : " + names.get(i));
        }
        Log.d("myLogs", ((Integer) (names.size())).toString());


        generateImageAndNames();


    }

    private void generateImageAndNames() {
        Random random = new Random();
        chooseCelebrity = random.nextInt(urls.size());

        try {
            imageCelebrity.setImageBitmap(new GetImageTask().execute(urls.get(chooseCelebrity)).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentCorrectPosition = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == currentCorrectPosition)
                buttonsPosition[i].setText(names.get(chooseCelebrity));
            else {
                int randomNames = random.nextInt(names.size());
                while (randomNames == chooseCelebrity)
                    randomNames = random.nextInt(names.size());
                buttonsPosition[i].setText(names.get(randomNames));
            }
        }
    }

    public void chosenAnswerOnClick(View view) {

        int id1 = buttonsPosition[currentCorrectPosition].getId();
        int id2 = ((Button) view).getId();
        String rightName = buttonsPosition[currentCorrectPosition].getText().toString();
        if (id1 == id2){
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
            generateImageAndNames();
        } else {
            Toast.makeText(this, "Wrong! The correct answer is " + rightName, Toast.LENGTH_SHORT).show();
            generateImageAndNames();
        }

    }


    public class GetSourceCodeTask extends AsyncTask<String, Void, StringBuilder> {

        @Override
        protected StringBuilder doInBackground(String... urls) {
            StringBuilder result = new StringBuilder("");

            URL url;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            InputStreamReader reader;
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

                return result;

            } catch (Exception e) {
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
    }

    public class GetImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {

                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();

                Bitmap image = BitmapFactory.decodeStream(inputStream);
                return image;

            } catch (Exception e) {
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
                urlConnection.disconnect();

            }

        }
    }


}
