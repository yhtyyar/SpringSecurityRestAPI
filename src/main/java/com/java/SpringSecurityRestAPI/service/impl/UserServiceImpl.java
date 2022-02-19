package com.java.SpringSecurityRestAPI.service.impl;

import com.java.SpringSecurityRestAPI.model.User;
import com.java.SpringSecurityRestAPI.repository.UserRepository;
import com.java.SpringSecurityRestAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public User create(User user) {

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        // password encoding
        newUser.setPassword(BCrypt.gensalt(user.getPassword(), 12));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        newUser.setEventList(user.getEventList());

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        log.info("Update User {}", user);
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        log.info("Get User by Id {}", id);
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete User by Id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        log.info("Get all Users");
        return userRepository.findAll();
    }
}
