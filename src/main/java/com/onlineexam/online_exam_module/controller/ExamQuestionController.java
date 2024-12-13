package com.onlineexam.online_exam_module.controller;


import com.onlineexam.online_exam_module.model.ExamQuestion;
import com.onlineexam.online_exam_module.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-questions")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    // Add a question to an exam
    @PostMapping
    public ExamQuestion addQuestionToExam(
            @RequestParam(name = "examId") int examId,
            @RequestParam(name = "questionId") int questionId) {
        return examQuestionService.addQuestionToExam(examId, questionId);
    }
}

