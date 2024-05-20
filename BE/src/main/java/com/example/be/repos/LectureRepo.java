package com.example.be.repos;

import com.example.be.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LectureRepo extends JpaRepository<Lecture,Integer> {

    Optional<Lecture> findLectureById(Integer id);
}
