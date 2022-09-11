package com.springboot.web.demo.userDemo.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.web.demo.userDemo.model.Student;

public interface StudentService extends UserDetailsService{

	Student findByUsername(String username);
	Student findById(long id);
	List<Student> findAllUser();
	Student createUser(Student user);
	void updateUser(long id, Student user);
	void deleteUser(long id);
	
}
