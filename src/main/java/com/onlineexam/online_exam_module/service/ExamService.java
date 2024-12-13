package com.onlineexam.online_exam_module.service;

import com.onlineexam.online_exam_module.model.Exam;
import com.onlineexam.online_exam_module.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Exam createExam(String name, String createdBy, int duration) {
        Exam exam = new Exam();
        exam.setName(name);
        exam.setCreatedBy(createdBy);
        exam.setCreatedDate(LocalDateTime.now());
        exam.setDuration(duration);
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
}