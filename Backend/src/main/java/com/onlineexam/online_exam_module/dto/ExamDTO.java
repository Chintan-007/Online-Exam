package com.onlineexam.online_exam_module.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ExamDTO {
    private int id;
    private String name;
    private String createdBy;
    private LocalDateTime createdDate;
    private int duration;
    private double passingPercentage;
    private List<QuestionDTO> examQuestions;
    private List<ProgrammingQuestionDTO> programmingQuestions;
}
