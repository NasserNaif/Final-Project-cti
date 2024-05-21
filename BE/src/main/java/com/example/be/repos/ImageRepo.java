package com.example.be.repos;

import com.example.be.models.CourseImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo  extends JpaRepository<CourseImage, Long> {
    CourseImage findCourseImageById(Long id);
}
