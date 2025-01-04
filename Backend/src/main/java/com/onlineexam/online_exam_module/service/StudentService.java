package com.onlineexam.online_exam_module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineexam.online_exam_module.model.User;
import com.onlineexam.online_exam_module.repository.UserRepository;

@Service
public class StudentService {

	@Autowired
	private UserRepository userRepository;
	
	
	//Create a new Student
	public User createStudent(User student) {
		student.setRole("STUDENT");
		return userRepository.save(student);
	}
	
	
	//Get all students
	public List<User> getAllStudents(){
		return userRepository.findByRole("STUDENT");
	}
	
	
	//Get a Student by ID
	public User getStudentById(int id) {
		User user = userRepository.findById(id)
					.orElseThrow(()-> new IllegalArgumentException("Student not found with id: "+id));
		
		if(!"STUDENT".equals(user.getRole())) {
			throw new IllegalArgumentException("User is not Student");
		}
		
		return user;
	}
	
	
	//Update student
	public User updateStudent(int id, User updatedStudent) {
		User existingStudent = getStudentById(id);
		existingStudent.setName(updatedStudent.getName());
		existingStudent.setEmail(updatedStudent.getEmail());
		existingStudent.setUserId(updatedStudent.getUserId());
		existingStudent.setPassword(updatedStudent.getPassword());
		
		return userRepository.save(existingStudent);
	}
	
	
	//Delete student
	public void deleteStudent(int id) {
		User student = getStudentById(id);
		userRepository.delete(student);
	}
}
