package com.onlineexam.online_exam_module.dto;

import lombok.Data;

@Data
public class ExamSummaryDTO {
    private String examName;
    private int totalAttempts;
    private double averageScore;
    private double passingPercentage;
}

