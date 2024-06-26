package com.example.be.repos;

import com.example.be.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

    Optional<Course> findCourseById(Integer id);
}
