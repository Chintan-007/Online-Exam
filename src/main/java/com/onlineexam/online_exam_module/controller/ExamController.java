package com.onlineexam.online_exam_module.controller;

import com.onlineexam.online_exam_module.dto.ExamDTO;
import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam("logicalMcqCount") int logicalMcqCount,
            @RequestParam("technicalMcqCount") int technicalMcqCount,
            @RequestParam("programmingMcqCount") int programmingMcqCount,
            @RequestParam("programmingQuesCount") int programmingQuesCount,
            @RequestParam("passingPercentage") double passingPercentage) {
        return examService.createExam(name, createdBy, duration, logicalMcqCount,technicalMcqCount,programmingMcqCount,programmingQuesCount,passingPercentage);
    }

    
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ExamDTO getExamById(@PathVariable(name = "id") int id) {
        return examService.getExamDTOById(id);
    }
    
}
