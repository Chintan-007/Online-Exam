package com.onlineexam.online_exam_module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="exam_programming_questions")
@Data
public class ExamProgrammingQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="exam_id", nullable=false)
	@JsonIgnore
	private Exam exam;
	
	@ManyToOne
	@JoinColumn(name="programming_question_id", nullable=false)
	private ProgrammingQuestion programmingQuestion;

}
