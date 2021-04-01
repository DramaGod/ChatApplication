package com.example.demo.repository;

import com.example.demo.model.Friend_request;
import com.example.demo.model.User;
import com.example.demo.registration.SendMessage;
import com.example.demo.registration.UserRegister;

public interface OpenService {

    boolean registerUser(UserRegister userRegister);

    boolean SendRequest (String username, User user1);
    boolean ConfirmRequest(String username, User user1);

    void SendMessage(String username, User user1, SendMessage sendMessage);
}
