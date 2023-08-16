package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User addUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User> getAllUsers() {
		return userRepository.findAll();
	}
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable long id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException("User with id " + id + " does not exists"));
	}
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User user, @PathVariable long id) {
		return userRepository.save(user);
	}
	
}
