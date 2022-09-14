package com.blog.api.api.service;

import com.blog.api.api.model.User;
import com.blog.api.api.model.dao.UserDAO;
import com.blog.api.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public User getZeroUser(){
        return userRepository.findById("zero_user").get();
    }

    public void initUserWithImage(){
        List<User> all = userRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        for(User user : all){
            if(user.getProfilBild() != null && user.getProfilBild().length == 0) {
                logger.info("Correcting Empty image for username: " + user.getUsername());
                byte[] img = restTemplate.getForObject("https://picsum.photos/400/600", byte[].class);
                user.setProfilBild(img);
                userRepository.save(user);
            }
        }
    }
}
