package com.onlineexam.online_exam_module.service;

import com.onlineexam.online_exam_module.dto.ExamDTO;
import com.onlineexam.online_exam_module.dto.ProgrammingQuestionDTO;
import com.onlineexam.online_exam_module.dto.QuestionDTO;
import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.model.ExamProgrammingQuestion;
import com.onlineexam.online_exam_module.model.ExamQuestion;
import com.onlineexam.online_exam_module.model.ProgrammingQuestion;
import com.onlineexam.online_exam_module.model.Question;
import com.onlineexam.online_exam_module.repository.ExamProgrammingQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamQuestionRepository;
import com.onlineexam.online_exam_module.repository.ExamRepository;
import com.onlineexam.online_exam_module.repository.ProgrammingQuestionRepository;
import com.onlineexam.online_exam_module.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired 
    private QuestionRepository questionRepository;
    
    @Autowired
    private ExamQuestionRepository examQuestionRepository;
    
    @Autowired
    private ProgrammingQuestionRepository programmingQuestionRepository;
    
    @Autowired
    private ExamProgrammingQuestionRepository examProgrammingQuestionRepository;
    

    public Exam createExam(String name, String createdBy, int duration, int logicalMcqCount, int technicalMcqCount, int programmingMcqCount, int programmingQueCount, double passingPercentage) {    	    	

    	//Create a new Exam
    	Exam exam = new Exam();
    	exam.setName(name);
    	exam.setCreatedBy(createdBy);
    	exam.setDuration(duration);
    	exam.setCreatedDate(LocalDateTime.now());
    	exam.setPassingPercentage(passingPercentage);
    	exam = examRepository.save(exam);
    	

    	//=================================MCQ SECTION========================================//
    	// Fetch MCQs by category
    	List<Question> logicalMCQs = questionRepository.findRandomQuestionsByCategory("LOGICAL", logicalMcqCount);		//FetchingLogical mcqs
    	List<Question> technicalMCQs = questionRepository.findRandomQuestionsByCategory("TECHNICAL", technicalMcqCount);
    	List<Question> programmingMCQs = questionRepository.findRandomQuestionsByCategory("PROGRAMMING", programmingMcqCount);
    	
    	//Combine all MCQs
    	List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(logicalMCQs);
        allQuestions.addAll(technicalMCQs);
        allQuestions.addAll(programmingMCQs);    
    	
    	// Link MCQs to the exam
        for (Question question : allQuestions) {
            ExamQuestion examMCQs = new ExamQuestion();
            examMCQs.setExam(exam);
            examMCQs.setQuestion(question);
            examQuestionRepository.save(examMCQs);
        }        
        
        
        //=================================PROGRAMMING SECTION========================================//
        // Fetching programming questions
        List<ProgrammingQuestion> programmingQuestions = programmingQuestionRepository.findRandomProgrammingQuestions(programmingQueCount);
        
        // Link Programming Questions to the exam
        for (ProgrammingQuestion programmingQuestion : programmingQuestions) {
            ExamProgrammingQuestion examProgrammingQuestion = new ExamProgrammingQuestion(); //join table between ProgrammingQuestion table and Exam table
            examProgrammingQuestion.setExam(exam);
            examProgrammingQuestion.setProgrammingQuestion(programmingQuestion);
            // Save the relation
            examProgrammingQuestionRepository.save(examProgrammingQuestion);
        }
        
        return exam;
    }
    

    //Retrieving all Exams 
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    //Retrieving a particular Exam
    public ExamDTO getExamDTOById(int id) {
        Optional<Exam> exam = examRepository.findById(id);

        if (exam.isPresent()) {
            return mapToExamDTO(exam.get());
        } else {
            throw new IllegalArgumentException("Exam not found for ID: " + id);
        }
    }

    private ExamDTO mapToExamDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setName(exam.getName());
        examDTO.setCreatedBy(exam.getCreatedBy());
        examDTO.setCreatedDate(exam.getCreatedDate());
        examDTO.setDuration(exam.getDuration());
        examDTO.setPassingPercentage(exam.getPassingPercentage());

        // Map exam questions
        List<QuestionDTO> questions = exam.getExamQuestions().stream()
                .map(eq -> new QuestionDTO(eq.getQuestion()))
                .toList();
        examDTO.setExamQuestions(questions);

        // Map programming questions
        List<ProgrammingQuestionDTO> programmingQuestions = exam.getExamProgrammingQuestions().stream()
                .map(pq -> new ProgrammingQuestionDTO(pq.getProgrammingQuestion()))
                .toList();
        examDTO.setProgrammingQuestions(programmingQuestions);

        return examDTO;
    }
}