package com.example.demo.controller;

import com.example.demo.model.Friend_request;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.registration.SendMessage;
import com.example.demo.repository.OpenService;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserService;
import io.swagger.annotations.Api;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "User")
@RestController
public class RequestController extends PrivateController {

    private  final  UserService userService;
    private final OpenService openService;


    public RequestController(UserService userService, OpenService openService) {
        this.userService = userService;

        this.openService = openService;

    }

    @PostMapping("/send/request/{username}")
    public boolean getUsers(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {
        User user1 = userService.findByEmail(userDetails.getUsername());
        openService.SendRequest(username, user1);
        return true;
    }


    @PostMapping("/confirm/request/{username}")
    public boolean confirmRequest(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String username) {

        User user1 = userService.findByEmail(userDetails.getUsername());
        openService.ConfirmRequest(username, user1);
        return true;
    }

    @PostMapping("/send/message/{username}")
    public boolean sendMessage(@AuthenticationPrincipal UserDetails userDetails,@RequestBody SendMessage sendMessage, @PathVariable String username) {

        User user1 = userService.findByEmail(userDetails.getUsername());
        openService.SendMessage(username, user1, sendMessage);
        return true;
    }



}
