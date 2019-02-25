package com.nakhmadov.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserFeedActivity extends AppCompatActivity {

    String username;
    LinearLayout activityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        getSupportActionBar().setTitle(username + "'s Feed");

        activityLayout = (LinearLayout) findViewById(R.id.userFeedActivityLayout);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.whereEqualTo("username", username);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects != null && e == null) {
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null){
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        ImageView imageView = new ImageView(getApplicationContext());

                                        imageView.setImageBitmap(bitmap);
                                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                        activityLayout.addView(imageView);
                                    }
                                }
                            });
                        }
                    } else {
                        Toast.makeText(UserFeedActivity.this, "This user doesn't have any feeds", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UserFeedActivity.this, "Some error occured!" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
