package com.example.demo.repository;

import com.example.demo.model.Friend_request;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.model.chatTable;
import com.example.demo.registration.SendMessage;
import com.example.demo.registration.UserRegister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenServiceImpl implements OpenService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FriendRequestRepository friendRequestRepository;
    private final MessageRepository messageRepository;
    private final chatTableRepository chatTableRepository;

    public OpenServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, FriendRequestRepository friendRequestRepository, MessageRepository messageRepository, com.example.demo.repository.chatTableRepository chatTableRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.friendRequestRepository = friendRequestRepository;
        this.messageRepository = messageRepository;
        this.chatTableRepository = chatTableRepository;
    }


    @Override
    public boolean registerUser(UserRegister userRegister) {

        User user = new User();

        user.setFirstname(userRegister.getFirstname());
        user.setLastname(userRegister.getLastname());
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setROLE("USER");
        user.setPassword(
                passwordEncoder.encode(userRegister.getPassword())
        );
        userRepository.save(user);


        return true;
    }

    @Override
    public boolean SendRequest(String username, User user1) {

        User user = userRepository.findByUsername(username);
        Friend_request friend_request = new Friend_request();
        friend_request.setSender(user1.getId());
        friend_request.setReciever(user.getId());
        friendRequestRepository.save(friend_request);
        user.setNotification("user: " + " " + user.getFirstname() + " " + user.getLastname() + " " + "sent you a request");
        userRepository.save(user);

        return true;

    }

    @Override
    public boolean ConfirmRequest(String username, User user1) {
        User user = userRepository.findByUsername(username);
        List<Friend_request> friend_requestList = new ArrayList<>();
        List<Friend_request> friend_requests = friendRequestRepository.findAll();
        Friend_request friend_request = new Friend_request();

        for (int i = 0; i < friend_requests.size(); i++) {
            if (friend_requests.get(i).getReciever() == user1.getId()) {
                friend_requestList.add(friend_requests.get(i));
            }
        }
        for (int a = 0; a < friend_requests.size(); a++)
            if (friend_requests.get(a).getSender() == user.getId() && friend_requests.get(a).getReciever() == user1.getId()) {
                if (friend_requests.get(a).isType() == false) {
                    friend_requests.get(a).setType(true);
                    friendRequestRepository.save(friend_requests.get(a));
                }
                else {
                    return false;
                }
            }
        return true;
    }

    @Override
    public void SendMessage(String username, User user1, SendMessage sendMessage) {
            User user= userRepository.findByUsername(username);
            chatTable chatTable= new chatTable();

            Message message=new Message();
            message.setText(sendMessage.getText());
            message.setType("text");
            chatTable.setMessage(message);
            chatTable.setSender_id(user1);
            chatTable.setReciever_id(user);
            chatTableRepository.save(chatTable);

            List<Message> messageList= new ArrayList<>();
            messageList.add(message);

            messageRepository.save(message);



    }

}

