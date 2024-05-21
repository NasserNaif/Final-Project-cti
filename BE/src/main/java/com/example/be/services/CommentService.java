package com.example.be.services;

import com.example.be.Api.ApiException;
import com.example.be.config.JwtService;
import com.example.be.models.Attachmant;
import com.example.be.models.Comment;
import com.example.be.models.Lecture;
import com.example.be.models.User;
import com.example.be.repos.AttachmentRepo;
import com.example.be.repos.CommentRepo;
import com.example.be.repos.LectureRepo;
import com.example.be.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private static final String NOT_FOUND_COURSE = "User not found";
    private static final String NOT_FOUND_LECTURE = "Lecture not found";

    private static final String NOT_AUTHORIZED = "Not authorized ";


    private final LectureRepo lectureRepo;

    private final CommentRepo commentRepo;
    private final JwtService jwtService;
    private final LectureService lectureService;
    private final UserRepo userRepo;
    private final AttachmentRepo attachmentRepo;


    public List<Comment> getAllComment(Integer id){
        return commentRepo.findCommentsByLecture(lectureService.getLecture(id));
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

        Comment comment1 = new Comment();

        if (user.isPresent()){
            if (lecture == null){
                throw new ApiException(NOT_FOUND_LECTURE);
            }
            comment1.setTitle(comment.getTitle());
            comment1.setUrl(comment.getUrl());
            comment1.setUser(user.get());
            comment1.setLecture(lecture);

            if (comment.getAttachmant() != null){



                Comment savedComment = commentRepo.save(comment1);
                Attachmant attachmant = new Attachmant();

                attachmant.setBase64(comment.getAttachmant().getBase64());
                attachmant.setFileName(comment.getAttachmant().getFileName());
                attachmant.setComment(savedComment);
                Attachmant savedAttachment = attachmentRepo.save(attachmant);

            }else {
                commentRepo.save(comment1);
            }



        }else {
            throw new ApiException(NOT_FOUND_COURSE);
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
