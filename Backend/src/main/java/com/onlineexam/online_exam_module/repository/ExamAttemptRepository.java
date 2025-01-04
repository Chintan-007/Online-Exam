package com.onlineexam.online_exam_module.repository;

import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.model.ExamAttempt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Integer> {

	void save(Optional<ExamAttempt> examAttempt);

	List<ExamAttempt> findByExam(Exam exam);
}
