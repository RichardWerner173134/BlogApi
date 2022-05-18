package com.blog.api.api.controller;

import com.blog.api.api.model.User;
import com.blog.api.api.service.BeitragViewService;
import com.blog.api.api.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){
        // TODO logging

        return new Gson().toJson(userService.getAll());
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
