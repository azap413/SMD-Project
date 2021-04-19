package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setusename(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getusername() {
        String usename = prefs.getString("username","");
        return usename;
    }
    public void setpassword(String password){
        prefs.edit().putString("password", password).commit();
    }
    public String getpassword() {
        String password = prefs.getString("password","");
        return password;
    }

}
