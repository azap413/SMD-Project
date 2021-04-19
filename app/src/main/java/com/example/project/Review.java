package com.example.project;

public class Review {
    private int rid = 0;
    private String review;
    private String location;
    private String user;

    public void setRid(int rid_){
        rid = rid_;
    }
    public void setReview(String review_){
        review = review_;
    }
    public void setLocation(String location_){
        location = location_;
    }
    public void setUser(String user_){
        user = user_;
    }
    public int getRid() {
        return rid;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public String getUser() {
        return user;
    }
}
