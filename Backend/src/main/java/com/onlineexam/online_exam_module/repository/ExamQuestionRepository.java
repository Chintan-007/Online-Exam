package com.onlineexam.online_exam_module.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineexam.online_exam_module.model.ExamQuestion;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Integer> {
}

