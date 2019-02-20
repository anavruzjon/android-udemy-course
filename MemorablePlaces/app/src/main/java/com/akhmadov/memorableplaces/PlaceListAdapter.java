package com.akhmadov.memorableplaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {

    private ArrayList<String> places;
    private LayoutInflater mInflater;

    public PlaceListAdapter(Context context, ArrayList<String> places) {
        this.places = places;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.places_list_item, viewGroup, false);
        return new PlaceViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        String placeCurrent = places.get(i);
        placeViewHolder.place.setText(placeCurrent);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{

        public final TextView place;

        PlaceListAdapter mAdapter;

        public PlaceViewHolder(@NonNull View itemView, PlaceListAdapter adapter) {
            super(itemView);
            place = itemView.findViewById(R.id.placeTextView);
            this.mAdapter = adapter;
        }
    }
}
