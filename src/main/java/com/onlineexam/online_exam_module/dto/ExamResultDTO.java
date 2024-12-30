package com.onlineexam.online_exam_module.dto;

import lombok.Data;

@Data
public class ExamResultDTO {
    private String studentName;
    private double score;
    private boolean passed;
}

