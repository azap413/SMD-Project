package com.example.project;

public class Hotel {
    private String Name = null;
    private String Location = null;

    public Hotel(){

    }

    public void setName(String name) {
        Name = name;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLocation() {
        return Location;
    }

    public String getName() {
        return Name;
    }
}
