package com.springboot.web.demo.userDemo.util;

import java.util.Arrays;

import com.springboot.web.demo.userDemo.dto.StudentDto;
import com.springboot.web.demo.userDemo.model.Role;
import com.springboot.web.demo.userDemo.model.Student;

public abstract class StudentUtil {

	public static Student updateRequired(Student existingRec, Student updateRec) {
		
		if(updateRec.getUsername() != null)
			existingRec.setUsername(updateRec.getUsername());
		if(updateRec.getName() != null)
			existingRec.setName(updateRec.getName());
		if(updateRec.getPassword() != null)
			existingRec.setPassword(updateRec.getPassword());
		
		return existingRec;
	}
	
	public static Student dtoToUserMap(StudentDto dto) {
		Student user = new Student();
		
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		
		return user;
	}
}
