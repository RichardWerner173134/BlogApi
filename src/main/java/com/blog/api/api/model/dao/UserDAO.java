package com.blog.api.api.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {
    private String author;
    private String vorname;
    private String nachname;
    private byte[] profilBild;
}
