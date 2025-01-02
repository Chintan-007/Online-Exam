package com.onlineexam.online_exam_module.dto;

import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.model.Question.Category;

import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String questionText;
    private Category category;
    private String options;
    private String correctAnswer;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.category = question.getCategory();
        this.options = question.getOptions();
        this.correctAnswer = question.getCorrectAnswer();
    }
}
