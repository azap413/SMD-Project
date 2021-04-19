package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startlogin(View v){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void startsignup(View v){
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
    }
}
