package com.example.be.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String url;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Lecture lecture;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private User user;

    @OneToOne(cascade = CascadeType.DETACH, mappedBy = "comment")
    private Attachmant attachmant;
}
