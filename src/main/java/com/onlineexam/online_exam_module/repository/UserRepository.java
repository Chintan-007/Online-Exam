package com.onlineexam.online_exam_module.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineexam.online_exam_module.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	List<User> findByRole(String string);
	
}
