package com.springboot.web.demo.userDemo.service.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.web.demo.userDemo.model.Role;
import com.springboot.web.demo.userDemo.model.Student;
import com.springboot.web.demo.userDemo.repository.StudentRepository;
import com.springboot.web.demo.userDemo.util.StudentUtil;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public StudentServiceImpl(StudentRepository userRepository) {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Student user = findByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
	
	@Override
	public Student findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public Student findById(long id) {
		Optional<Student> user = userRepository.findById(id);
		return user.get();
	}
	
	@Override
	public List<Student> findAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public Student createUser(Student user) {
    	Optional<Student> savedUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername())); 
    	
    	if(savedUser.isPresent()) {
    		return savedUser.get();
    	}
    	else {
    		
    		String encodedPassword = bCryptPasswordEncoder
                    .encode(user.getPassword());
    		
    		user.setPassword(encodedPassword);
    		
    		return userRepository.save(user);
    	}
	}
	
	@Override
	public void updateUser(long id, Student user) {
		Optional<Student> savedUser = userRepository.findById(id);
    	
    	if(savedUser.isPresent()) {
			StudentUtil.updateRequired(savedUser.get(), user);
    		userRepository.save(savedUser.get());
    	}
	}
	
	@Override
	public void deleteUser(long id) {
		Optional<Student> user = userRepository.findById(id);
    	if(user.isPresent()){
    		userRepository.deleteById(id);
    	}
		
	}
}
