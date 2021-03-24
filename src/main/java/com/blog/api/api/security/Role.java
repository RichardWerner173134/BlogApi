package com.blog.api.api.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role {

    ADMIN("admin"),
    USER("user");

    private String name;

    private Role(String name){
        this.name = name;
    }

    public GrantedAuthority getAuthority() {
        return (GrantedAuthority) () -> name;
    }
}
