package com.onlineexam.online_exam_module.model;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exams")
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private int duration; // Duration in minutes

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonManagedReference // Prevents infinite recursion
    private List<ExamQuestion> examQuestions;
}

