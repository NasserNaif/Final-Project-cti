package com.example.be.services;

import com.example.be.Api.ApiException;
import com.example.be.config.JwtService;
import com.example.be.models.Comment;
import com.example.be.models.Lecture;
import com.example.be.models.User;
import com.example.be.repos.CommentRepo;
import com.example.be.repos.LectureRepo;
import com.example.be.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {
    private static final String NOT_FOUND_COURSE = "Course not found";
    private static final String NOT_FOUND_LECTURE = "Comment not found";

    private static final String NOT_AUTHORIZED = "Not authorized ";


    private final LectureRepo lectureRepo;

    private final CommentRepo commentRepo;
    private final JwtService jwtService;
    private final LectureService lectureService;
    private final UserRepo userRepo;


    public Set<Comment> getAllComment(Integer id){
        return lectureService.getLecture(id).getComments();
    }

    public Comment getComment(Integer id){

        if (commentRepo.findCommentById(id).isEmpty()){
            throw new ApiException(NOT_FOUND_LECTURE);

        }
        return commentRepo.findCommentById(id).get();
    }


    public void addComment(Integer lectureId, Comment comment){
        Optional<User> user = Optional.ofNullable(userRepo.findUserById(jwtService.getUserId()).orElseThrow(() -> new ApiException("User not found")));

        Lecture lecture = lectureService.getLecture(lectureId);

        if (user.isPresent()){
            comment.setUser(user.get());
            comment.setLecture(lecture);
            commentRepo.save(comment);
        }
    }

    public void updateComment(Integer id, Comment newComment){

        if (commentRepo.existsCommentByIdAndUser(id, userRepo.findUserById(jwtService.getUserId()).get())){
            throw new ApiException(NOT_AUTHORIZED);
        }

        Optional<Comment> comment = Optional.ofNullable(commentRepo.findCommentById(id).orElseThrow(() -> new ApiException(NOT_FOUND_LECTURE)));

        comment.get().setTitle(newComment.getTitle());
        comment.get().setUrl(newComment.getUrl());

        commentRepo.save(comment.get());
    }


    public void deleteComment(Integer id){

        if (commentRepo.existsCommentByIdAndUser(id, userRepo.findUserById(jwtService.getUserId()).get())){
            throw new ApiException(NOT_AUTHORIZED);
        }

        commentRepo.deleteCommentById(id);

    }


}
