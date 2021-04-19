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

public class ReviewAdapter extends ArrayAdapter<Review> {
    private ArrayList<Review> Reviews;
    private Context context;
    public ReviewAdapter(Context context, int resource, ArrayList<Review> s){
        super(context,resource,s);
        this.Reviews = s;
        this.context = context;
    }

    @Nullable
    @Override
    public Review getItem(int position) {
        return Reviews.get(position);
    }

    @Override
    public int getCount(){
        return Reviews.size();
    }

    @Override
    public View getView(int index, View convertView,ViewGroup parent) {
        final Review review = getItem(index);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.onereview,parent,false);
        }
        TextView slot1= convertView.findViewById(R.id.reviewbox);
        slot1.setText("'"+review.getReview()+"'");
        TextView slot2 = convertView.findViewById(R.id.reviewuser);
        slot2.setText(review.getUser());
        return convertView;
    }
}
