package com.example.be.controllers;


import com.example.be.Api.ApiResponse;
import com.example.be.dto.CourseDto;
import com.example.be.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;



    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Integer id){
        return ResponseEntity.ok().body(courseService.getCourse(id));
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody CourseDto courseDto){
        courseService.addCourse(courseDto);
        return ResponseEntity.ok().body(new ApiResponse("curse added"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@Valid @RequestBody CourseDto courseDto,@PathVariable Integer id){
        courseService.updateCourse(id,courseDto);
        return ResponseEntity.ok().body(new ApiResponse("curse updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleterious(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok().body(new ApiResponse("curse deleted"));
    }
}
