package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
    }

    public void savereview(View v){
        Intent intent = getIntent();
        String user = intent.getStringExtra("User");
        String location = intent.getStringExtra("Location");
        EditText slot1 = findViewById(R.id.editreview);
        String review = slot1.getText().toString();
        if(!review.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("REVIEW", review);
            values.put("USER", user);
            values.put("LOCATION", location);
            try {
                Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI4,values);
            } catch (Exception e) {
                Log.e(null,"Error in inserting review");
            }finally {
               finish();
            }
        }
        else{
            Toast.makeText(this,"Please Type a Review",Toast.LENGTH_SHORT).show();
        }
    }

}
