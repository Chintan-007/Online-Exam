package com.onlineexam.online_exam_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineexam.online_exam_module.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
