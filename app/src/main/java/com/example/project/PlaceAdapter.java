package com.example.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> implements Filterable {
    private ArrayList<Place> Places;
    private ArrayList<Place> filteredPlaces;
    private Context context;
    private Filter filter;
    ImageView image;
    public PlaceAdapter(Context context, int resource, ArrayList<Place> s){
        super(context,resource,s);
        this.Places = s;
        this.filteredPlaces = s;
        this.context = context;
    }

    @Nullable
    @Override
    public Place getItem(int position) {
        return Places.get(position);
    }

    @Override
    public int getCount(){
        return Places.size();
    }

    @Override
    public View getView(int index, View convertView,ViewGroup parent) {
        final Place place = getItem(index);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row,parent,false);
        }
        TextView slot1 = convertView.findViewById(R.id.placename);
        image = convertView.findViewById(R.id.imgholder);
        slot1.setText(place.getName());
        Picasso.with(getContext()).load(place.getPicURI()).fit().into(image);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LocationPage.class);
                intent.putExtra("Location",place.getName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
