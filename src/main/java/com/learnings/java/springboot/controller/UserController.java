package com.learnings.java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.java.springboot.exception.ResourceNotFoundException;
import com.learnings.java.springboot.model.User;
import com.learnings.java.springboot.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) throws ResourceNotFoundException {
		
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this Id :: "+id));
		
	}
	
	//add user
	@PostMapping
	public User createUser(@RequestBody User user){
		return userRepository.save(user);
	}
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") long id) throws ResourceNotFoundException   {
		User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this Id :: "+id));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmailId(user.getEmailId());
		userRepository.save(existingUser);
		 return existingUser;
		
	}
	
	//delete user
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable long id) throws ResourceNotFoundException {
		User existingUser =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this Id :: "+id));
		
		userRepository.delete(existingUser);
		
		return existingUser;
		
	}
	

}
