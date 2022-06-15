package com.blog.api.api.controller;

import com.blog.api.api.model.User;
import com.blog.api.api.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllUsers(){
        String content = new Gson().toJson(userService.getAll());
        logger.info("GET - /users");
        return new ResponseEntity(content, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{username}/img",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getProfilbild(@PathVariable String username){
        // TODO logging
        Optional<User> user = userService.getUser(username);
        if(user.isPresent()){
            return new ByteArrayResource(user.get().getProfilBild());
        }

        throw new UsernameNotFoundException("No User with username " + username + " found");
    }
}
