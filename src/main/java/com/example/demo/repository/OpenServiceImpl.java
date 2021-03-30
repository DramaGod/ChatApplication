package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.registration.UserRegister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OpenServiceImpl implements OpenService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public OpenServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public boolean registerUser(UserRegister userRegister)  {

        User user=new User();

        user.setFirstname(userRegister.getFirstname());
        user.setLastname(userRegister.getLastname());
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setROLE("USER");
        user.setPassword (
                passwordEncoder.encode(userRegister.getPassword())
        );
        userRepository.save(user);


        return true;
    }


}
