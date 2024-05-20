package com.example.be.services;

import com.example.be.Api.ApiException;
import com.example.be.config.JwtService;
import com.example.be.dto.CourseDto;
import com.example.be.mapper.CourseMapper;
import com.example.be.models.Course;

import com.example.be.repos.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final JwtService jwtService;

    private static final String NOT_FOUND_MESSAGE = "not found";



    public List<CourseDto> getAllCourses(){
        return courseMapper.toDTO(courseRepo.findAll());
    }

    public CourseDto getCourse(Integer id) {
        Course course = courseRepo.findCourseById(id).orElseThrow(() -> new ApiException(NOT_FOUND_MESSAGE));
        return courseMapper.toDTO(course);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void  addCourse(CourseDto courseDto){

        courseDto.setAddedBy(jwtService.getUserId());
        courseRepo.save(courseMapper.toEntity(courseDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateCourse(Integer id, CourseDto courseDto)  {
        Course course = courseRepo.findCourseById(id).orElseThrow(() -> new ApiException(NOT_FOUND_MESSAGE));

        course.setName(courseDto.getName());
        course.setNumber(courseDto.getNumber());
        course.setLevel(courseDto.getLevel());

        courseRepo.save(course);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCourse(Integer id)  {
        Course course = courseRepo.findCourseById(id).orElseThrow(() -> new ApiException(NOT_FOUND_MESSAGE));

        courseRepo.delete(course);

    }
}
