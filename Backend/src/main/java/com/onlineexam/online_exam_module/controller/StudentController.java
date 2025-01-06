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

import com.onlineexam.online_exam_module.model.User;
import com.onlineexam.online_exam_module.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	//create a new student
	@PostMapping
	public User createStudent(@RequestBody User student) {
		return studentService.createStudent(student);
	}
	
	
	//Get all students
	@GetMapping
	public List<User> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	
	//Get a student by Id
	@GetMapping("/{id}")
	public User getStudentById(@PathVariable(name = "id") int id) {
		return studentService.getStudentById(id);
	}
	
	
	//Update a student
	@PutMapping("/{id}")
	public User updateStudent(@PathVariable(name = "id") int id, @RequestBody User student) {
		return studentService.updateStudent(id, student);
	}
	
	
	//Delete a student
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id) {
		studentService.deleteStudent(id);
		return "Student with id: "+id+" deleted sucessfully...!";
	}
}
