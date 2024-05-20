package com.example.be.controllers;


import com.example.be.Api.ApiResponse;
import com.example.be.models.Lecture;
import com.example.be.services.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;


    @GetMapping("/{id}")
    public ResponseEntity<Set<Lecture>> getAllCourses(@PathVariable Integer id){
        return ResponseEntity.ok().body(lectureService.getAllLecture(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Lecture> getCourse(@PathVariable Integer id){
        return ResponseEntity.ok().body(lectureService.getLecture(id));
    }


    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody Lecture courseDto,@PathVariable Integer id){
        lectureService.addLecture(id,courseDto);
        return ResponseEntity.ok().body(new ApiResponse("lecture added"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCourse(@Valid @RequestBody Lecture courseDto,@PathVariable Integer id){
        lectureService.updateLecture(id,courseDto);
        return ResponseEntity.ok().body(new ApiResponse("curse updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleterious(@PathVariable Integer id){
        lectureService.deleteLecture(id);
        return ResponseEntity.ok().body(new ApiResponse("curse deleted"));
    }
}
