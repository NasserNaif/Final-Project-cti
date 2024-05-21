package com.example.be.services;

import com.example.be.Api.ApiException;
import com.example.be.config.JwtService;
import com.example.be.dto.CourseDto;
import com.example.be.mapper.CourseMapper;
import com.example.be.models.Course;

import com.example.be.models.CourseImage;
import com.example.be.repos.CourseRepo;
import com.example.be.repos.ImageRepo;
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
    private final ImageRepo imageRepo;

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


        Course course = courseMapper.toEntity(courseDto);
        course.setAddedBy(3);
        if (courseDto.getCourseImage() != null){
            CourseImage courseImage = new CourseImage();

            courseImage.setBase64(courseDto.getCourseImage().getBase64());
            courseImage.setFileName(courseDto.getCourseImage().getFileName());
            courseImage.setCourse(course);
            course.setCourseImage(courseImage);
        }

        courseRepo.save(course);



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
