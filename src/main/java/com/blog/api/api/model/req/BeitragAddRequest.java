package com.blog.api.api.model.req;

import com.blog.api.api.model.Beitrag;
import com.blog.api.api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class BeitragAddRequest {
    private String title;
    private String content;
    private String author;

    @Autowired
    private UserService userService;

    public Beitrag convertToBeitrag(){
        Beitrag beitrag = new Beitrag();
        beitrag.setTitle(title);
        beitrag.setUsername(userService.getUser(author).get());
        beitrag.setContent(content);
        beitrag.setViews(null);
        beitrag.setCreationTime(Instant.now());
        return beitrag;
    }
}
