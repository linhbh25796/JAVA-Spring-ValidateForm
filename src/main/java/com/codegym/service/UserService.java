package com.codegym.service;

import com.codegym.model.User;
import com.codegym.repository.UserRepository;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void save(User user);
}
