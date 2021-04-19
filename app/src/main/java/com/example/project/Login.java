package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.project.MyContentProvider.CONTENT_URI1;

public class Login extends AppCompatActivity {
    public Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Login(View v){
        EditText box1 = findViewById(R.id.nameedit1);
        EditText box2 = findViewById(R.id.passedit1);
        String username = box1.getText().toString();
        String password = box2.getText().toString();
        if( username.isEmpty() || password.isEmpty()) {
            Toast status = Toast.makeText(this, "One or more fields is empty", Toast.LENGTH_SHORT);
            status.show();
        }
        else {
            try {
                Cursor cursor = getContentResolver().query(CONTENT_URI1, null, "USER_NAME = " +"'" + username +"'" + " AND PASSWORD = " + "'"+password+"'", null, null);
                if(cursor.getCount() ==1) {
                    session = new Session(getApplicationContext());
                    session.setusename(username);
                    session.setpassword(password);
                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    startActivity(intent);
                }else{
                    Toast status = Toast.makeText(this, "Invalid username or pass", Toast.LENGTH_SHORT);
                    status.show();
                }
            } catch (SQLException e) {
                Log.e(null,"Error in SQL");
            }
        }

    }
}
