package com.example.project;

import android.net.Uri;

public class Place {
    private String Name = null;
    private String PicURI = null;
    public Place(){ }

    public String getPicURI() {
        return PicURI;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name_){
        Name = name_;
    }
    public void setPicURI(String uri_){
        PicURI = uri_;
    }



}
