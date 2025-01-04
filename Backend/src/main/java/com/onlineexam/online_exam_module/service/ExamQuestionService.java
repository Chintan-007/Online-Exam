package com.onlineexam.online_exam_module.service;



import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.model.ExamQuestion;
import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.repository.ExamQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamRepository;
import com.onlineexam.online_exam_module.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ExamQuestion addQuestionToExam(int examId, int questionId) {
        Optional<Exam> exam = examRepository.findById(examId);
        Optional<Question> question = questionRepository.findById(questionId);

        if (exam.isPresent() && question.isPresent()) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam.get());
            examQuestion.setQuestion(question.get());
            return examQuestionRepository.save(examQuestion);
        } else {
            throw new IllegalArgumentException("Invalid exam or question ID");
        }
    }
}

