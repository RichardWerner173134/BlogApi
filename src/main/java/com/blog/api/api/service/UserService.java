package com.blog.api.api.service;

import com.blog.api.api.model.User;
import com.blog.api.api.model.dao.UserDAO;
import com.blog.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String username){
        return userRepository.findById(username);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public boolean isUserPresent(User user){
        return userRepository.findById(user.getUsername()).isPresent();
    }

    public List<UserDAO> getAllUserDAOs() {
        return userRepository.findAll().stream()
                .map(user -> new UserDAO(user.getUsername(), user.getVorname(), user.getNachname(), "http://localhost:8080/users/" + user.getUsername() + "/img"))
                .collect(Collectors.toList());
    }

    public UserDAO getUserDAO(String username){
        Optional<User> userO = userRepository.findById(username);
        if(userO.isPresent()){
            User user = userO.get();
            return new UserDAO(user.getUsername(), user.getVorname(), user.getNachname(), "http://localhost:8080/users/" + username + "/img");
        }
        throw new IllegalStateException("User not found");
    }

    public User getZeroUser(){
        return userRepository.findById("zero_user").get();
    }
}
