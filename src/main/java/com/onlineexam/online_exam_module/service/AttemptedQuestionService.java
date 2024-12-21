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
        Optional<ExamAttempt> examAttempt = examAttemptRepository.findById(examAttemptId);
        Optional<Question> question = questionRepository.findById(questionId);

        if (examAttempt.isPresent() && question.isPresent()) {
            AttemptedQuestion attemptedQuestion = new AttemptedQuestion();
            attemptedQuestion.setExamAttempt(examAttempt.get());
            attemptedQuestion.setQuestion(question.get());
            attemptedQuestion.setSelectedAnswer(selectedAnswer);

            // Check if the answer is correct
            boolean isCorrect = question.get().getCorrectAnswer().equals(selectedAnswer);
            attemptedQuestion.setCorrect(isCorrect);

            return attemptedQuestionRepository.save(attemptedQuestion);
        } else {
            throw new IllegalArgumentException("Invalid exam attempt or question ID");
        }
    }
}
