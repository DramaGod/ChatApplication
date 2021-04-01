package com.example.demo.model;

import com.example.demo.WebEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "chatTable")
public class chatTable extends WebEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id")
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reciever_id")
    private User reciever_id;

    public User getSender_id() {
        return sender_id;
    }

    public void setSender_id(User sender_id) {
        this.sender_id = sender_id;
    }

    public User getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(User reciever_id) {
        this.reciever_id = reciever_id;
    }
}