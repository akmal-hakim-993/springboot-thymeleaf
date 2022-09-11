package com.springboot.web.demo.userDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.web.demo.userDemo.dto.StudentDto;
import com.springboot.web.demo.userDemo.model.Student;
import com.springboot.web.demo.userDemo.service.user.StudentService;
import com.springboot.web.demo.userDemo.util.StudentUtil;

@Controller
@RequestMapping("/registration")
public class StudentController {

	private StudentService userservice;
    
    public StudentController(StudentService userService) {
    	this.userservice = userService;
    }
     
    /**
     * 
     * End points
     */
    @PostMapping
    public String createUser(@ModelAttribute("student") StudentDto dto) {
    	if(dto.getUsername().isBlank() || dto.getPassword().isBlank()) return "redirect:/registration?error";
		Student student = StudentUtil.dtoToUserMap(dto);
		userservice.createUser(student);
		
    	return "redirect:/registration?success";
    }
    
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable( "id" ) long id,StudentDto dto) {	
    	Student student = StudentUtil.dtoToUserMap(dto);
    	userservice.updateUser(id, student);
    	return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    	userservice.deleteUser(id);
    	return "redirect:/";
    }
    
    /*
     * Show form
     */
    
    @GetMapping
    public String showRegistrationForm() {
    	return "registration";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = userservice.findById(id);
        
        model.addAttribute("student", student);
        return "update";
    }
    
    /**
     * Model Attribute
     */
    
    @ModelAttribute("student")
    public StudentDto studentDto() {
    	return new StudentDto();
    }
}
