package com.nakhmadov.twitterclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.TweetHolderView> {

    private ArrayList<Tweet> tweets;
    private LayoutInflater mInflater;
    private Context mContext;

    public TweetListAdapter(Context context, ArrayList<Tweet> tweets) {
        this.tweets = tweets;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TweetHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.tweet_item, parent, false);
        return new TweetHolderView(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetHolderView holder, int position) {
        Bitmap image = tweets.get(position).getBitmap();
        String name = tweets.get(position).getName();
        String username = tweets.get(position).getUsername();
        String tweet = tweets.get(position).getTweet();
        holder.accountImage.setImageBitmap(image);
        holder.accountName.setText(name);
        holder.accountUsername.setText(username);
        holder.accountTweet.setText(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class TweetHolderView extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView accountImage;
        TextView accountName;
        TextView accountUsername;
        TextView accountTweet;

        View itemView;
        TweetListAdapter mAdapter;

        public TweetHolderView(@NonNull View itemView, TweetListAdapter adapter) {
            super(itemView);
            this.itemView = itemView;
            this.mAdapter = adapter;
            accountImage = (ImageView) itemView.findViewById(R.id.account_profile_image);
            accountName = (TextView) itemView.findViewById(R.id.account_name);
            accountUsername = (TextView) itemView.findViewById(R.id.account_username);
            accountTweet = (TextView) itemView.findViewById(R.id.account_tweet);

            accountImage.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.account_profile_image){
                Tweet currentTweet = tweets.get(getAdapterPosition());
                Intent profileActivity = new Intent(mContext, ProfileActivity.class);
                profileActivity.putExtra("username", currentTweet.getUsername());
                mContext.startActivity(profileActivity);
            } else {
                Intent tweetActivity = new Intent(mContext, TweetActivity.class);
                mContext.startActivity(tweetActivity);

            }
        }
    }
}
