package com.example.be.dto;


import com.example.be.models.CourseImage;
import com.example.be.models.Lecture;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {


    private Integer id;

    @NotEmpty
    private String name;
    private String teacher;



    @NotEmpty
    private String number;

    @NotNull
    private Integer level;

    private Integer addedBy;


    private Set<Lecture> lectures;

    private CourseImage courseImage;
}
