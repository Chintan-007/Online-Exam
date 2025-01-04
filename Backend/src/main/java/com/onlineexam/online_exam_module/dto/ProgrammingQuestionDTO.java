package com.onlineexam.online_exam_module.dto;

import com.onlineexam.online_exam_module.model.ProgrammingQuestion;

import lombok.Data;

@Data
public class ProgrammingQuestionDTO {
    private int id;
    private String questionText;
    private String referenceAnswer;
    private String difficultyLevel;

    public ProgrammingQuestionDTO(ProgrammingQuestion programmingQuestion) {
        this.id = programmingQuestion.getId();
        this.questionText = programmingQuestion.getQuestionText();
        this.referenceAnswer = programmingQuestion.getReferenceAnswer();
        this.difficultyLevel = programmingQuestion.getDifficultyLevel();
    }
    
}