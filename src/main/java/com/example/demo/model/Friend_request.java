package com.example.demo.model;

import com.example.demo.WebEntity;

public class Friend_request extends WebEntity {


    private double user_id;

    private double friend_id;

    private boolean type;

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getUser_id() {
        return user_id;
    }

    public void setUser_id(double user_id) {
        this.user_id = user_id;
    }

    public double getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(double friend_id) {
        this.friend_id = friend_id;
    }

}
