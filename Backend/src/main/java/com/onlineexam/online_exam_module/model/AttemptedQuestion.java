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
    @JoinColumn(name = "question_id")
    private Question mcqQuestion;
    
    @ManyToOne
    @JoinColumn(name = "programming_question_id")
    private ProgrammingQuestion programmingQuestion;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String answer;

    @Column
    private boolean isCorrect;    
    
}
