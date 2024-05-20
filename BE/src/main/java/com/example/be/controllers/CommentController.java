package com.example.be.controllers;


import com.example.be.Api.ApiResponse;
import com.example.be.models.Comment;
import com.example.be.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<Set<Comment>> getAllCourses(@PathVariable Integer id){
        return ResponseEntity.ok().body(commentService.getAllComment(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Comment> getCourse(@PathVariable Integer id){
        return ResponseEntity.ok().body(commentService.getComment(id));
    }


    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody Comment courseDto, @PathVariable Integer id){
        commentService.addComment(id,courseDto);
        return ResponseEntity.ok().body(new ApiResponse("comment added"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@Valid @RequestBody Comment courseDto,@PathVariable Integer id){
        commentService.updateComment(id,courseDto);
        return ResponseEntity.ok().body(new ApiResponse("comment updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleterious(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().body(new ApiResponse("comment deleted"));
    }
}
