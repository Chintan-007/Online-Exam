package com.onlineexam.online_exam_module.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineexam.online_exam_module.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
