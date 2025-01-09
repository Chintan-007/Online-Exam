package com.onlineexam.online_exam_module.controller;

import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/mcqs")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    
    //Add a new question 
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
    	return questionService.addQuestion(question);
    }
    
    //Get all questions
    @GetMapping
    public List<Question> getAllQuestions(){
    	return questionService.getAllQuestions();
    }
    
    
    //Get a question by id
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable(name = "id") int id) {
    	return questionService.getQuestionById(id);
    }
    
    
    //Update question
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable(name = "id") int id, @RequestBody Question question) {
    	return questionService.updateQuestion(id, question);
    }
    
    
    //Delete a question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable(name = "id") int id) {
    	questionService.deleteQuestion(id);
    }
}
