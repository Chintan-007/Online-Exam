package com.onlineexam.online_exam_module.controller;

import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public Exam createExam(
            @RequestParam("name") String name,
            @RequestParam("createdBy") String createdBy,
            @RequestParam("duration") int duration,
            @RequestParam("logicalCount") int logicalCount,
            @RequestParam("technicalCount") int technicalCount,
            @RequestParam("programmingCount") int programmingCount,
            @RequestParam("passingPercentage") double passingPercentage) {
        return examService.createExam(name, createdBy, duration, logicalCount,technicalCount,programmingCount,passingPercentage);
    }

    
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}") 
    public Optional<Exam> getExamById(@PathVariable(name="id") int id) {
    	return examService.getExamById(id);
    }
    
}
