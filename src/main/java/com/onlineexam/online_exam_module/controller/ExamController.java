package com.onlineexam.online_exam_module.controller;

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

    @PostMapping
    public Exam createExam(
            @RequestParam("name") String name,
            @RequestParam("createdBy") String createdBy,
            @RequestParam("duration") int duration) {
        return examService.createExam(name, createdBy, duration);
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }
}
