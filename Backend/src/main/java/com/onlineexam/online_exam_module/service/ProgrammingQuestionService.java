package com.onlineexam.online_exam_module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineexam.online_exam_module.model.ProgrammingQuestion;
import com.onlineexam.online_exam_module.repository.ProgrammingQuestionRepository;

@Service
public class ProgrammingQuestionService {

	@Autowired
	ProgrammingQuestionRepository programmingQuestionRepository;
	
	public ProgrammingQuestion addProgrammingQuestion(ProgrammingQuestion question) {
		return programmingQuestionRepository.save(question);
	}
	
	public List<ProgrammingQuestion> getAllProgrammingQuestions(){
		return programmingQuestionRepository.findAll();
	}
	
	public ProgrammingQuestion getProgrammingQuestionById(int id) {
		return programmingQuestionRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Programming Question Not Found"));
	}
	
	public void deleteProgrammingQuestionById(int id) {
			programmingQuestionRepository.deleteById(id);
			
	}

	public ProgrammingQuestion updateProgrammingQuestion(int id,ProgrammingQuestion updatedQuestion) {
		
		ProgrammingQuestion existingQuestion = getProgrammingQuestionById(id);
    	existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
    	existingQuestion.setReferenceAnswer(updatedQuestion.getReferenceAnswer());
    	existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
    	
    	return programmingQuestionRepository.save(existingQuestion);
	}
}
