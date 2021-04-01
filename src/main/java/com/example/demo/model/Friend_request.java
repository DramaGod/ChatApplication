package com.example.demo.model;

import com.example.demo.WebEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "friend_request")
public class Friend_request extends  WebEntity{


    @Column(name = "sender_id")
    private Long sender;

    @Column(name = "reciever_id")
    private Long reciever;

    @Column (name = "type")
    private boolean type;


    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReciever() {
        return reciever;
    }

    public void setReciever(Long reciever) {
        this.reciever = reciever;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
