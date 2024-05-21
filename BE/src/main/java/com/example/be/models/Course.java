package com.example.be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String number;

    private String teacher;

    private Integer level;

    @Column(nullable = false)
    private Integer addedBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Lecture> lectures;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
    @JoinColumn(name = "image_id",referencedColumnName = "id")
    private CourseImage courseImage;
}
