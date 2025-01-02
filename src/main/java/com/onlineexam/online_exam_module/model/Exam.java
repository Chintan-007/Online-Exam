package com.onlineexam.online_exam_module.model;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


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
    
    @Column(nullable = false)
    private double passingPercentage;
    
    
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamQuestion> examQuestions;
    
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamProgrammingQuestion> examProgrammingQuestions;


}

