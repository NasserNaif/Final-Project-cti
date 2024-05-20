package com.example.be.mapper;


import com.example.be.dto.CourseDto;
import com.example.be.models.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {

    CourseDto toDTO(Course user);

    List<CourseDto> toDTO(List<Course> courses);

    Course toEntity(CourseDto courseDto);

}
