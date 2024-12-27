package com.onlineexam.online_exam_module.service;


import com.onlineexam.online_exam_module.model.AttemptedQuestion;
import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.model.ExamAttempt;
import com.onlineexam.online_exam_module.model.ExamQuestion;
import com.onlineexam.online_exam_module.model.User;
import com.onlineexam.online_exam_module.repository.AttemptedQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamAttemptRepository;
import com.onlineexam.online_exam_module.repository.ExamRepository;
import com.onlineexam.online_exam_module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamAttemptService {

    @Autowired
    private ExamAttemptRepository examAttemptRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AttemptedQuestionRepository attemptedQuestionRepository;
    
    public ExamAttempt getExamAttemptById(int examAttemptId) {
        return examAttemptRepository.findById(examAttemptId)
                .orElseThrow(() -> new IllegalArgumentException("Exam attempt not found with ID: " + examAttemptId));
    }

    public ExamAttempt startExam(int examId, int userId) {
        Optional<Exam> exam = examRepository.findById(examId);
        Optional<User> user = userRepository.findById(userId);

        if (exam.isPresent() && user.isPresent()) {
            ExamAttempt examAttempt = new ExamAttempt();
            examAttempt.setExam(exam.get());
            examAttempt.setUser(user.get());
            examAttempt.setAttemptDate(LocalDateTime.now());
            examAttempt.setScore(0); // Initial score is 0
            return examAttemptRepository.save(examAttempt);
        } else {
            throw new IllegalArgumentException("Invalid exam or user ID");
        }
    }

	public ExamAttempt finalizeExamAttempt(int examAttemptId) {
		
		Optional<ExamAttempt> examAttemptOpt = examAttemptRepository.findById(examAttemptId);
		
		if(examAttemptOpt.isPresent()) {
			ExamAttempt examAttempt = examAttemptOpt.get();
			
			
			if(examAttempt.isFinalized()) {
				throw new IllegalArgumentException("Exam has already finalised");
			}
			
			
			//Check if all questions have been attempted
			List<ExamQuestion> examQuestions = examAttempt.getExam().getExamQuestions();
			List<AttemptedQuestion> attemptedQuestions = attemptedQuestionRepository.findByExamAttempt(examAttempt);
			
			if(examQuestions.size() != attemptedQuestions.size()) {
				throw new IllegalArgumentException("Not all questions have been attempted");
			}
			
			//Finalize the exam attempt
			examAttempt.setFinalized(true);
			return examAttemptRepository.save(examAttempt);
			
			
		}
		return null;
	}
}
