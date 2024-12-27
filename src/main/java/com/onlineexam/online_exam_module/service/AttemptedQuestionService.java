package com.onlineexam.online_exam_module.service;


import com.onlineexam.online_exam_module.model.AttemptedQuestion;
import com.onlineexam.online_exam_module.model.ExamAttempt;
import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.repository.AttemptedQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamAttemptRepository;
import com.onlineexam.online_exam_module.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttemptedQuestionService {

    @Autowired
    private AttemptedQuestionRepository attemptedQuestionRepository;

    @Autowired
    private ExamAttemptRepository examAttemptRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public AttemptedQuestion saveAttemptedQuestion(int examAttemptId, int questionId, String selectedAnswer) {
        Optional<ExamAttempt> examAttemptOpt = examAttemptRepository.findById(examAttemptId);
        Optional<Question> questionOpt = questionRepository.findById(questionId);

        if (examAttemptOpt.isPresent() && questionOpt.isPresent()) {
        	
        	ExamAttempt examAttempt = examAttemptOpt.get();// Retrieve the ExamAttempt
        	Question question = questionOpt.get(); // Retrieve the Question
        	
        	
        	//Prevent furthur submission once exam has been submitted
        	if (examAttempt.isFinalized()) {
        	    throw new IllegalStateException("Cannot submit answers. Exam is already finalized.");
        	}

        	
            AttemptedQuestion attemptedQuestion = new AttemptedQuestion();
            attemptedQuestion.setExamAttempt(examAttempt);
            attemptedQuestion.setQuestion(question);
            attemptedQuestion.setSelectedAnswer(selectedAnswer);

            // Check if the answer is correct
            boolean isCorrect = question.getCorrectAnswer().equals(selectedAnswer);
            attemptedQuestion.setCorrect(isCorrect);
            
            // Update the score if the answer is correct
            if (isCorrect) {
                int currentScore = examAttempt.getScore();
                examAttempt.setScore(currentScore + 1); // Increment score
                examAttemptRepository.save(examAttempt); // Save updated ExamAttempt
            }

            return attemptedQuestionRepository.save(attemptedQuestion);
        } else {
            throw new IllegalArgumentException("Invalid exam attempt or question ID");
        }
    }
}
