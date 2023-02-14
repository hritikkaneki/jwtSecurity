package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User addUser(User user);

    List<User> fetchAllUsers();
}
