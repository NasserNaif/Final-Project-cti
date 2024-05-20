package com.example.be.repos;

import com.example.be.models.Comment;
import com.example.be.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

    Optional<Comment> findCommentById(Integer id);

    boolean existsCommentByIdAndUser(Integer id,User user);

    void deleteCommentById(Integer integer);
}
