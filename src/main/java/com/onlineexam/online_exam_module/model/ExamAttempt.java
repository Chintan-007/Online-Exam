package com.onlineexam.online_exam_module.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exam_attempts")
@Data
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime attemptDate;

    @Column(nullable = false)
    private int score;

    @OneToMany(mappedBy = "examAttempt", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AttemptedQuestion> attemptedQuestions;
}
