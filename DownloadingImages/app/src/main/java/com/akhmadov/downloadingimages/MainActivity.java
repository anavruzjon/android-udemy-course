package com.akhmadov.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = (ImageView) findViewById(R.id.imageView);
    }

    public void downloadImage(View view) {
        // download Image from https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png
        Log.d("myLogs", "The button has been tapped");

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            downloadedImage.setImageBitmap(myImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {



        @Override
        protected Bitmap doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            URL url;
            Bitmap myBitmap;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return null;
        }
    }
}
