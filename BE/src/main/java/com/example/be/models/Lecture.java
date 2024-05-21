package com.example.be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    @JsonIgnore
    private Set<Comment> comments;
}
