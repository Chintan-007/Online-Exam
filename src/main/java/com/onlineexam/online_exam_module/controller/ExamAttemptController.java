package com.onlineexam.online_exam_module.controller;


import com.onlineexam.online_exam_module.dto.ExamResultDTO;
import com.onlineexam.online_exam_module.dto.ExamSummaryDTO;
import com.onlineexam.online_exam_module.model.AttemptedQuestion;
import com.onlineexam.online_exam_module.model.ExamAttempt;
import com.onlineexam.online_exam_module.service.AttemptedQuestionService;
import com.onlineexam.online_exam_module.service.ExamAttemptService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-attempts")
public class ExamAttemptController {

    @Autowired
    private ExamAttemptService examAttemptService;

    @Autowired
    private AttemptedQuestionService attemptedQuestionService;

    // Fetch an exam attempt by its ID
    @GetMapping("/{examAttemptId}")
    public ExamAttempt getExamAttemptById(@PathVariable(name="examAttemptId") int examAttemptId) {
        return examAttemptService.getExamAttemptById(examAttemptId);
    }
    
    // Start an exam attempt
    @PostMapping("/start")
    public ExamAttempt startExam(
            @RequestParam(name = "examId") int examId,
            @RequestParam(name = "userId") int userId) {
        return examAttemptService.startExam(examId, userId);
    }

    // Submit an answer for a MCQs
    @PostMapping("/{examAttemptId}/submit-answer")
    public AttemptedQuestion submitAnswer(
            @PathVariable(name = "examAttemptId") int examAttemptId,
            @RequestParam(name = "questionId") int questionId,
            @RequestParam(name = "selectedAnswer") String selectedAnswer) {
        return attemptedQuestionService.saveAttemptedQuestion(examAttemptId, questionId, selectedAnswer);
    }
    
 // Submit an answer for a Programming question
    @PostMapping("/{examAttemptId}/submit-code")
    public AttemptedQuestion submitCode(
            @PathVariable(name = "examAttemptId") int examAttemptId,
            @RequestParam(name = "programmingQuestionId") int programmingQuestionId,
            @RequestBody String code) {
        return attemptedQuestionService.saveAttemptedProgrammingQuestion(examAttemptId, programmingQuestionId, code);
    }
    
    //Finalize/Submit the exam
    @PostMapping("/{examAttemptId}/finalize")
    public ExamAttempt finalize(@PathVariable(name = "examAttemptId") int examAttemptId) {
    	return examAttemptService.finalizeExamAttempt(examAttemptId);
    }
    
    
    //Get Result of exam
    @GetMapping("/{examAttemptId}/result")
    public ExamAttempt getResult(@PathVariable(name = "examAttemptId") int examAttemptId) {
        return examAttemptService.getExamAttemptById(examAttemptId);
    }
    
    
    //Get the summary of all the exams
    @GetMapping("/summary")
    public List<ExamSummaryDTO> getExamSummaries() {
        return examAttemptService.getExamSummaries();
    }

    
    //Get the summary of a specific exam
    @GetMapping("/exam/{examId}")
    public List<ExamResultDTO> getExamResults(@PathVariable(name = "examId") int examId) {
        return examAttemptService.getExamResultsByExamId(examId);
    }

    

}
