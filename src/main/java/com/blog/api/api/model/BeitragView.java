package com.blog.api.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(BeitragViewId.class)
@Setter
@Getter
public class BeitragView {

    @Id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "beitrag_id")
    private Beitrag beitrag;

    @Id
    @Column
    private Date date;
}

