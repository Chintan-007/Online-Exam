package com.onlineexam.online_exam_module.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "programming_questions")
@Data
public class ProgrammingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String questionText;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String referenceAnswer; // Reference answer for admin/examiner

    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String difficultyLevel; // e.g., EASY, MEDIUM, HARD
}