package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void insertrecord(View v){
        EditText slot1 = findViewById(R.id.nameedit);//get the whole tag
        EditText slot2 = findViewById(R.id.passedit);
        String name = slot1.getText().toString();//getting the text
        String pass = slot2.getText().toString();
        if(!(pass.isEmpty()) && !(name.isEmpty())) {
            if(getContentResolver().query(MyContentProvider.CONTENT_URI1,null,"USER_NAME = " +"'" + name +"'",null,null).getCount() == 0) {
                ContentValues values = new ContentValues();
                values.put("PASSWORD", pass);
                values.put("USER_NAME", name);
                Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI1, values);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast status = Toast.makeText(this,"User Already exists", Toast.LENGTH_SHORT);
                status.show();
            }
        }else {
            Toast status = Toast.makeText(this,"One or more fields is empty", Toast.LENGTH_SHORT);
            status.show();
        }
    }
    public void showpassword(View v){
        CheckBox box = (CheckBox)findViewById(R.id.checkbox1);
        EditText text = findViewById(R.id.passedit);
        if(box.isChecked()){
            text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else text.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
}
