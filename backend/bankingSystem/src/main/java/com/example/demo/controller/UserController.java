package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public ResponseEntity<?>  addUser(@RequestBody User newUser) {
		try {
			userRepository.save(newUser);
		} catch(IllegalArgumentException ex) {
	        throw new IllegalArgumentException("Arguments are not valid");			
		}
		SuccessResponse<String> successResponse = new SuccessResponse<>("User created successfully");
        return ResponseEntity.ok(successResponse);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?>  getAllUsers() {
		List<User> users = userRepository.findAll();
		SuccessResponse<List<User>> successResponse = new SuccessResponse<>("Fetched users successfully", users);
        return ResponseEntity.ok(successResponse);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			SuccessResponse<User> successResponse = new SuccessResponse<>("Fetched user successfully", user.get());
            return ResponseEntity.ok(successResponse);
        } else {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
	}
	
	@PutMapping("/user/address/{id}")
	public ResponseEntity<?> updateUserAddress(@RequestBody User updatedUser, @PathVariable long id) {
		Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
        	System.out.println("user " + id + "is present");
            User existingUser = existingUserOptional.get();

            if (updatedUser.getPermAddId() != null) {
                existingUser.setPermAddId(updatedUser.getPermAddId());
            }

            if (updatedUser.getTempAddId() != null) {
                existingUser.setTempAddId(updatedUser.getTempAddId());
            }

            userRepository.save(existingUser);
        	System.out.println("user " + id + "is saved");
            
    		SuccessResponse<String> successResponse = new SuccessResponse<>("User updated successfully");
            return ResponseEntity.ok(successResponse);

        } else {
        	System.out.println("user " + id + "is NOT present");
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
	}
}
