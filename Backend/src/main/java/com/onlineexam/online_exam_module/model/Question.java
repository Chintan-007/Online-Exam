package com.onlineexam.online_exam_module.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String questionText;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // Logical, Technical, Programming

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String options; // JSON-like structure: ["Option1", "Option2", ...]

    @Column(nullable = false)
    private String correctAnswer;
    
    public enum Category {
        LOGICAL, TECHNICAL, PROGRAMMING
    }
}
