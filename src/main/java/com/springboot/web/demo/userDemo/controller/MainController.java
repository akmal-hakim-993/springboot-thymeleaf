package com.springboot.web.demo.userDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.web.demo.userDemo.service.user.StudentService;

@Controller
@RequestMapping("/")
public class MainController {

	private StudentService userservice;
	
    public MainController(StudentService userService) {
    	this.userservice = userService;
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("students", userservice.findAllUser());
        return "index";
    }
}
