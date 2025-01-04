package com.onlineexam.online_exam_module.service;


import com.onlineexam.online_exam_module.dto.ExamResultDTO;
import com.onlineexam.online_exam_module.dto.ExamSummaryDTO;
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
import java.util.ArrayList;
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
			
			
			// Calculate total score
	        int totalScore = attemptedQuestionRepository.findByExamAttempt(examAttempt)
	                .stream()
	                .filter(AttemptedQuestion::isCorrect)
	                .mapToInt(q -> 1) // Each correct answer gives 1 point
	                .sum();
	        examAttempt.setScore(totalScore);

	        // Determine pass/fail based on passingPercentage
	        double passingPercentage = examAttempt.getExam().getPassingPercentage();
	        boolean passed = (totalScore >= examAttempt.getExam().getExamQuestions().size() * (passingPercentage / 100));
	        examAttempt.setPassed(passed);
	        
	        
			//Finalize the exam attempt
			examAttempt.setFinalized(true);
			return examAttemptRepository.save(examAttempt);
			
			
		}
		return null;
	}

	public List<ExamSummaryDTO> getExamSummaries() {
		
	    List<Exam> exams = examRepository.findAll(); //Fetches all exams.

	    List<ExamSummaryDTO> summaries = new ArrayList<>();
	    
	    for (Exam exam : exams) {
	        List<ExamAttempt> attempts = examAttemptRepository.findByExam(exam); //Fetch associated attempts.

	        if (!attempts.isEmpty()) { //Calculate total attempts, average score, and passing percentage.
	            double totalScore = attempts.stream().mapToDouble(ExamAttempt::getScore).sum();
	            long totalAttempts = attempts.size();
	            double averageScore = totalScore / totalAttempts;

	            long passedCount = attempts.stream().filter(ExamAttempt::isPassed).count();
	            double passingPercentage = ((double) passedCount / totalAttempts) * 100;

	            ExamSummaryDTO summary = new ExamSummaryDTO();
	            summary.setExamName(exam.getName());
	            summary.setTotalAttempts((int) totalAttempts);
	            summary.setAverageScore(averageScore);
	            summary.setPassingPercentage(passingPercentage);

	            summaries.add(summary);
	        }
	    }
	    return summaries;
	}


	public List<ExamResultDTO> getExamResultsByExamId(int examId) {
		
	    Optional<Exam> optionalExam = examRepository.findById(examId);
	    if (optionalExam.isEmpty()) {//Validates if the exam exists.
	        throw new IllegalArgumentException("Exam with ID " + examId + " does not exist.");
	    }
	    
	    List<ExamAttempt> attempts = examAttemptRepository.findByExam(optionalExam.get());//Fetches all attempts for the given exam.

	    List<ExamResultDTO> results = new ArrayList<>();
	    for (ExamAttempt attempt : attempts) { //Extracts and formats student-specific details into ExamResultDTO.
	        ExamResultDTO result = new ExamResultDTO();
	        result.setStudentName(attempt.getUser().getName());
	        result.setScore(attempt.getScore());
	        result.setPassed(attempt.isPassed());
	        results.add(result);
	    }
	    return results;
	}

}
