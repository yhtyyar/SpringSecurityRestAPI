package com.java.SpringSecurityRestAPI.service;

import com.java.SpringSecurityRestAPI.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    User getById(Long id);

    void deleteById(Long id);

    List<User> getAll();
}
