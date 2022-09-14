package com.blog.api.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Beitrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Long id;

    @Expose
    @Column
    private String title;

    @Expose
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column
    @Expose
    private Instant creationTime;

    @ManyToOne
    @Expose
    @JoinColumn(name = "author_id")
    private User username;

    @OneToMany(mappedBy = "beitrag")
    private Set<BeitragView> views;
}
