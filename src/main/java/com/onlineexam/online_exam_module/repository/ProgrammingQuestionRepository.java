package com.onlineexam.online_exam_module.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineexam.online_exam_module.model.ProgrammingQuestion;

public interface ProgrammingQuestionRepository extends JpaRepository<ProgrammingQuestion, Integer> {

	@Query(value = "SELECT * FROM programming_questions ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT :count ROWS ONLY", nativeQuery = true)
	List<ProgrammingQuestion> findRandomProgrammingQuestions(@Param("count") int count);

}
