package com.onlineexam.online_exam_module.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "attempted_questions")
@Data
public class AttemptedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "exam_attempt_id", nullable = false)
    @JsonIgnore
    private ExamAttempt examAttempt;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = true)
    private String selectedAnswer;

    @Column(nullable = false)
    private boolean isCorrect;
    
    
}
