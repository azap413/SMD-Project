package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static com.google.android.gms.ads.AdSize.BANNER;

public class HotelPage extends AppCompatActivity {
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_page);
        Intent intent = getIntent();
        location = intent.getStringExtra("Location");
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        outstate.putString("CurrentLocation",location);
        super.onSaveInstanceState(outstate);
    }
    @Override
    protected void onRestoreInstanceState(Bundle outstate){
        location = outstate.getString("CurrentLocation");
        super.onRestoreInstanceState(outstate);
    }


    @Override
    protected void onStart() {
        super.onStart();
        LinearLayout layout = new LinearLayout(this);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        AdView adView = new AdView(this);
        adView.setLayoutParams(new AdView.LayoutParams(AdView.LayoutParams.MATCH_PARENT, AdView.LayoutParams.WRAP_CONTENT));
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.setAdSize(BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.loadAd(adRequest);
        layout.addView(adView);
        ArrayList<Hotel> hotels = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI2,null,"LOCATION = "+"'"+location+"'",null,null);
        cursor.moveToFirst();
        Hotel temp;
        int i =0;
        while(i< cursor.getCount()) {
            temp = new Hotel();
            temp.setLocation(cursor.getString((cursor.getColumnIndexOrThrow("LOCATION"))));
            temp.setName(cursor.getString((cursor.getColumnIndexOrThrow("HOTEL_NAME"))));
            hotels.add(temp);
            cursor.move(1);
            i++;
        }
        HotelAdapter adapter = new HotelAdapter(this,R.layout.onehotel,hotels);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ListView view = new ListView(this);
        view.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT));
        view.setAdapter(adapter);
        TextView title = new TextView(this);
        title.setText("Hotels in " + location);
        title.setTextSize(25);
        layout.addView(title);
        layout.addView(view);
        setContentView(layout);
    }
}
