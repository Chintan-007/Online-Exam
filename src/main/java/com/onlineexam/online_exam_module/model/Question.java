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

    @Column(nullable = false)
    private String category; // Logical, Technical, Programming

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String options; // JSON-like structure: ["Option1", "Option2", ...]

    @Column(nullable = false)
    private String correctAnswer;
}
