package com.onlineexam.online_exam_module.repository;

import com.onlineexam.online_exam_module.model.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Integer> {
}
