package com.nakhmadov.twitterclone;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{


    RecyclerView recyclerView;
    TweetListAdapter mAdapter;
    ArrayList<Tweet> tweets;

    ViewGroup container;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();

        /*Bitmap bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic1);
        Tweet tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");


        ImageView imageView = (ImageView) container.findViewById(R.id.account_profile_image);
        TextView nameTV = (TextView) container.findViewById(R.id.account_name);
        TextView usernameTV = (TextView) container.findViewById(R.id.account_username);
        TextView tweetTV = (TextView) container.findViewById(R.id.account_tweet);


//        imageView.setImageBitmap(bitmap);
        nameTV.setText("Navruzjon Akhmadov Jr.");
        usernameTV.setText("@nav_akhmadov");
        tweetTV.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
*/


        //recyclerView = (RecyclerView) container.findViewById(R.id.home_recyclerView);

        RecyclerView recyclerView = (RecyclerView) container.findViewById(R.id.home_recyclerView);



        tweets = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic1);
        Tweet tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic2);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);


        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);
        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic3);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic4);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.pic5);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);

        bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getContext()).getResources(), R.drawable.picture);
        tweet = new Tweet(bitmap, "Navruzjon Akhmadov", "@akhmadov",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

        tweets.add(tweet);


        mAdapter = new TweetListAdapter(getContext(), tweets);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

}
