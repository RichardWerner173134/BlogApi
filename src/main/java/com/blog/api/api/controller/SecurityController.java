package com.blog.api.api.controller;

import com.blog.api.api.model.User;
import com.blog.api.api.security.AuthenticationRequest;
import com.blog.api.api.security.JwtUtil;
import com.blog.api.api.security.MyUserDetailsService;
import com.blog.api.api.security.Role;
import com.blog.api.api.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@RestController
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    @ResponseBody
    public String authentiate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username and Password");
        }

        final UserDetails userdetails = myUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userdetails);

        return new Gson().toJson(jwt);
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST, consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void register(MultipartHttpServletRequest request) throws Exception {

        Iterator<Map.Entry<String, String[]>> iterator = request.getParameterMap().entrySet().iterator();
        User user = new User();

        while(iterator.hasNext()){
            Map.Entry<String, String[]> param = iterator.next();
            switch(param.getKey()){
                case "username":
                    user.setUsername(String.valueOf(param.getValue()[0]));
                    break;
                case "password":
                    user.setPassword((String.valueOf(param.getValue()[0])));
                    break;
                case "vorname":
                    user.setVorname(String.valueOf(param.getValue()[0]));
                    break;
                case "nachname":
                    user.setNachname(String.valueOf(param.getValue()[0]));
                    break;
            }
        }


        user.setProfilBild(request.getMultiFileMap().getFirst("profilbild").getBytes());


        if(!userService.isUserPresent(user)){
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setAuthList(Arrays.asList(Role.USER.getAuthority()));
            userService.addUser(user);
        } else {
            throw new Exception(String.format("Username %s already exists", user.getUsername()));
        }

    }


}
