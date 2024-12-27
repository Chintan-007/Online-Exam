package com.onlineexam.online_exam_module.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineexam.online_exam_module.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT :count ROWS ONLY", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("count") int count);

}
