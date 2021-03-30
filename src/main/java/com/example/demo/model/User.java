package com.example.demo.model;

import com.example.demo.WebEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends WebEntity implements UserDetails {


    @Column(name = "firstname")
    protected String firstname;

    @Column(name = "lastname")
    protected String lastname;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @Email
    @Column(name = "email")
    protected String email;

    @Column(name = "ROLE")
    protected String ROLE;



   /* @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @JsonIgnore
    protected Message message; */


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_chat",
            joinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reciever_id", referencedColumnName = "id")
    )
    @JsonIgnore
    protected List<User> recievers;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_chat",
            joinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id")
    )
    @JsonIgnore
    protected List<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    @JsonIgnore
    protected List<User> friends;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        String ROLE_PREFIX="ROLE_";
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + ROLE));
        return list;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getROLE() {
        return ROLE;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setMail(String mail) {
        this.email = email;
    }

    public List<User> getRecievers() {
        return recievers;
    }

    public void setRecievers(List<User> recievers) {
        this.recievers = recievers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
