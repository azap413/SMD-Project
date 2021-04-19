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

public class LocationPage extends AppCompatActivity {
    String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_page);
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
        ArrayList<Review> reviews = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI4,null,"LOCATION = "+"'"+location+"'",null,null);
        cursor.moveToFirst();
        Review temp;
        int i =0;
        while(i< cursor.getCount()) {
            temp = new Review();
            temp.setLocation(cursor.getString((cursor.getColumnIndexOrThrow("LOCATION"))));
            temp.setReview(cursor.getString((cursor.getColumnIndexOrThrow("REVIEW"))));
            temp.setUser(cursor.getString((cursor.getColumnIndexOrThrow("USER"))));
            reviews.add(temp);
            cursor.move(1);
            i++;
        }
        ReviewAdapter adapter = new ReviewAdapter(this,R.layout.onereview,reviews);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ListView view = new ListView(this);
        view.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.WRAP_CONTENT, ListView.LayoutParams.WRAP_CONTENT));
        view.setAdapter(adapter);
        TextView title = new TextView(this);
        title.setText("About " + location);
        title.setTextSize(25);
        layout.addView(title);
        layout.addView(view);
        final Session session = new Session(getApplicationContext());
        Button addreview = new Button(this);
        addreview.setText("Add Review");
        addreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AddReview.class);
                intent.putExtra("Location",location);
                intent.putExtra("User",session.getusername());
                startActivity(intent);
            }
        });
        layout.addView(addreview);
        Button bookhotel = new Button(this);
        bookhotel.setText("Book a Hotel");
        bookhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),HotelPage.class);
                intent.putExtra("Location",location);
                intent.putExtra("User",session.getusername());
                startActivity(intent);
            }
        });
        layout.addView(bookhotel);
        setContentView(layout);
    }
}
