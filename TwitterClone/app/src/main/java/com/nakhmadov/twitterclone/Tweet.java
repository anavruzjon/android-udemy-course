package com.nakhmadov.twitterclone;

import android.graphics.Bitmap;

public class Tweet {

    Bitmap bitmap;
    String name;
    String username;
    String tweet;

    public Tweet(Bitmap bitmap, String name, String username, String tweet) {
        this.bitmap = bitmap;
        this.name = name;
        this.username = username;
        this.tweet = tweet;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
