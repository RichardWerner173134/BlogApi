package com.blog.api.api.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {
  private String username;
  private String password;
}
