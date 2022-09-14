package com.blog.api.api.database;

import com.blog.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StartupScript {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initImages(){
        userService.initUserWithImage();
    }
}
