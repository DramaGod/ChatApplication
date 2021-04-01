package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User,Long>{

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(double user_id);
}
