package com.onlineexam.online_exam_module.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(100)")
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(50)")
    private String userId;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String role; // e.g., ADMIN, STUDENT
    
    
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userId=" + userId + ", password="
				+ password + ", role=" + role + "]";
	}
    
    
}

