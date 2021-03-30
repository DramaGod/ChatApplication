package com.example.demo.model;

import com.example.demo.WebEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "message")
public class Message extends  WebEntity{


    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}