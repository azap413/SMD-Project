package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static com.google.android.gms.ads.AdSize.BANNER;

public class HomePage extends AppCompatActivity {
    ArrayList<Place> Places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        AdView adView = new AdView(this);
        adView.setLayoutParams(new AdView.LayoutParams(AdView.LayoutParams.MATCH_PARENT, AdView.LayoutParams.WRAP_CONTENT));
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.setAdSize(BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.loadAd(adRequest);
        layout.addView(adView);
        Places = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI3,null,null,null,null);
        cursor.moveToFirst();
        Place temp;
        int i =0;
        while(i< cursor.getCount()) {
            temp = new Place();
            temp.setName(cursor.getString((cursor.getColumnIndexOrThrow("PLACE_NAME"))));
            temp.setPicURI(cursor.getString((cursor.getColumnIndexOrThrow("URL"))));
            Places.add(temp);
            cursor.move(1);
            i++;
        }
        PlaceAdapter adapter = new PlaceAdapter(this,R.layout.row,Places);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ListView view = new ListView(this);
        view.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT));
        view.setAdapter(adapter);
        layout.addView(view);
        setContentView(layout);
    }
}
