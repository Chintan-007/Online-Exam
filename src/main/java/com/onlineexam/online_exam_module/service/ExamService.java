package com.onlineexam.online_exam_module.service;

import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.model.ExamQuestion;
import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.repository.ExamQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamRepository;
import com.onlineexam.online_exam_module.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired 
    private QuestionRepository questionRepository;
    
    @Autowired
    private ExamQuestionRepository examQuestionRepository;
    

    public Exam createExam(String name, String createdBy, int duration, int logicalCount, int technicalCount, int programmingCount) {
    	
    	
    	// Fetch questions by category
    	List<Question> logicalQuestions = questionRepository.findRandomQuestionsByCategory("LOGICAL", logicalCount);
    	List<Question> technicalQuestions = questionRepository.findRandomQuestionsByCategory("TECHNICAL", technicalCount);
    	List<Question> programmingQuestions = questionRepository.findRandomQuestionsByCategory("PROGRAMMING", programmingCount);
    	
    	//Combine all questions
    	List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(logicalQuestions);
        allQuestions.addAll(technicalQuestions);
        allQuestions.addAll(programmingQuestions);
      
    
        //Debugging
    	System.out.println("=================================================");
    	System.out.println("Logical Questions: " + logicalQuestions.size());
    	System.out.println("Technical Questions: " + technicalQuestions.size());
    	System.out.println("Programming Questions: " + programmingQuestions.size());
    	System.out.println("All Questions: " + allQuestions.size());
    	System.out.println("=================================================");
    	
    	
    	//Create a new Exam
    	Exam exam = new Exam();
    	exam.setName(name);
    	exam.setCreatedBy(createdBy);
    	exam.setDuration(duration);
    	exam.setCreatedDate(LocalDateTime.now());
    	exam = examRepository.save(exam);
    
    	
    	// Link questions to the exam
        for (Question question : allQuestions) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam);
            examQuestion.setQuestion(question);
            examQuestionRepository.save(examQuestion);
        }
        
        
        return exam;
    }
    

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }


	public Optional<Exam> getExamById(int id) {
		return examRepository.findById(id);
	}
}