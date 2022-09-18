package com.blog.api.api.controller;

import com.blog.api.api.model.User;
import com.blog.api.api.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAllUsers() {
        String content = new Gson().toJson(userService.getAllUserDAOs());
        logger.info("GET - /users");
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{username}/img",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity getProfilbild(@PathVariable String username) {
        Optional<User> user = userService.getUser(username);
        logger.info("GET - /users/" + username + "/img");
        logger.info("content: " +  user.get().getProfilBild());
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(user.get().getProfilBild() == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(new ByteArrayResource(user.get().getProfilBild()));
    }

    @RequestMapping(value = "/users/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getUser(@PathVariable String username) {
        String content = new Gson().toJson(userService.getUserDAO(username));
        logger.info("GET - /users/" + username);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}