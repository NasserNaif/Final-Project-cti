package com.example.be.services;

import com.example.be.Api.ApiException;
import com.example.be.models.Course;
import com.example.be.models.Lecture;
import com.example.be.repos.CourseRepo;
import com.example.be.repos.LectureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LectureService {

    private static final String NOT_FOUND_COURSE = "Course not found";
    private static final String NOT_FOUND_LECTURE = "Course not found";


    private final LectureRepo lectureRepo;

    private final CourseRepo courseRepo;



    public Set<Lecture> getAllLecture(Integer id){
        Optional<Course> course = Optional.ofNullable(courseRepo.findCourseById(id).orElseThrow(() -> new ApiException(NOT_FOUND_COURSE)));
        if (course.isPresent()){
            return course.get().getLectures();

        }
        return new HashSet<>();
    }

    public Lecture getLecture(Integer id){
        if (lectureRepo.findLectureById(id).isPresent()){
            return lectureRepo.findLectureById(id).get();
        }
        throw new ApiException(NOT_FOUND_LECTURE);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void addLecture(Integer id,Lecture lecture){
        Optional<Course> course = Optional.ofNullable(courseRepo.findCourseById(id).orElseThrow(() -> new ApiException(NOT_FOUND_COURSE)));

        if (course.isPresent()){
            Lecture lecture1 = new Lecture(null, lecture.getName(), course.get(),null);

            lectureRepo.save(lecture1);
        }
        throw new ApiException(NOT_FOUND_COURSE);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateLecture(Integer id,Lecture lecture){
        Optional<Lecture> lecture1 = lectureRepo.findLectureById(id);
        if (lecture1.isPresent()){
            lecture1.get().setName(lecture.getName());
            lectureRepo.save(lecture1.get());
        }

        throw new ApiException(NOT_FOUND_LECTURE);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteLecture(Integer id){
        Optional<Lecture> lecture1 = lectureRepo.findLectureById(id);
        if (lecture1.isEmpty()){
            throw new ApiException(NOT_FOUND_LECTURE);
        }
        lecture1.ifPresent(lectureRepo::delete);

    }
}
