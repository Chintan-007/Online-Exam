package com.onlineexam.online_exam_module.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineexam.online_exam_module.model.ProgrammingQuestion;
import com.onlineexam.online_exam_module.service.ProgrammingQuestionService;

@RestController
@RequestMapping("/api/programming-questions")
public class ProgrammingQuestionController {

	@Autowired
	ProgrammingQuestionService programmingQuestionService;
	
	
	//adding question
	@PostMapping("/add")
	public ProgrammingQuestion addProgrammingQuestion(@RequestBody ProgrammingQuestion question) {
		return programmingQuestionService.addProgrammingQuestion(question);
	}
	
	//getting all programming question
	@GetMapping
	public List<ProgrammingQuestion> getAllProgrammingQuestions(){
		return programmingQuestionService.getAllProgrammingQuestions();
	}
	
	//get question by id
	@GetMapping("/{id}")
	public ProgrammingQuestion getProgrammingQuestionById(@PathVariable(name="id") int id) {
		return programmingQuestionService.getProgrammingQuestionById(id);
	}
	
	
	//update question by id
	@PutMapping("/{id}")
	public ProgrammingQuestion updateProgrammingQuestion(@PathVariable(name="id") int id,@RequestBody ProgrammingQuestion question) {
		return programmingQuestionService.updateProgrammingQuestion(id,question);
	}
	
	//delete question
	@DeleteMapping("/{id}")
	public void deleteProgrammingQuestionById(@PathVariable(name="id") int id) {
		programmingQuestionService.deleteProgrammingQuestionById(id);
	}
}
