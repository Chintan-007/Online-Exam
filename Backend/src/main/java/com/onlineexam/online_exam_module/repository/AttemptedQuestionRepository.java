package com.onlineexam.online_exam_module.repository;

import com.onlineexam.online_exam_module.model.AttemptedQuestion;
import com.onlineexam.online_exam_module.model.ExamAttempt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptedQuestionRepository extends JpaRepository<AttemptedQuestion, Integer> {
	List<AttemptedQuestion> findAllByExamAttemptId(int examAttemptId);

	List<AttemptedQuestion> findByExamAttempt(ExamAttempt examAttempt);
}
