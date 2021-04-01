package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.registration.UserRegister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("user dont found");
        }
        return user;

    }

    @Override
    public List<User> getUserList() {

        return userRepository.findAll();

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("Dont Found "+username);
        }

        return  user;
    }

}
