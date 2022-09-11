package com.springboot.web.demo.userDemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.web.demo.userDemo.model.Student;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByUsername(String username);
	void deleteByUsername(String username);
	
}
