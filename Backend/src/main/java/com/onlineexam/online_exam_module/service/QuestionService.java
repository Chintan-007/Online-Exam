package com.onlineexam.online_exam_module.service;

import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    
    //Add a new question
    public Question addQuestion(Question question) {
    	return questionRepository.save(question);
    }
    
    
    //Get all question
    public List<Question> getAllQuestions(){
    	return questionRepository.findAll();
    }
    
    
    //Get a question by id
    public Question getQuestionById(int id) {
    	return questionRepository.findById(id)
    			.orElseThrow(()-> new IllegalArgumentException("Question not found with ID: "+id));
    }
    
    
    //Update a question
    public Question updateQuestion(int id, Question updatedQuestion) {
    	Question existingQuestion = getQuestionById(id);
    	existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
    	existingQuestion.setCategory(updatedQuestion.getCategory());
    	existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
    	existingQuestion.setOptions(updatedQuestion.getOptions());
    	
    	return questionRepository.save(existingQuestion);
    }
    
    
    //Delete a question
    public void deleteQuestion(int id) {
    	Question question = getQuestionById(id);
    	questionRepository.delete(question);
    }
    
}
