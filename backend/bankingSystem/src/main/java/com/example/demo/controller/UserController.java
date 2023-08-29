package com.example.demo.controller;

import java.sql.Date;
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
	
	@Autowired
	private EmailController emailController;
	
	
	@PostMapping("/user")
	public ResponseEntity<?>  addUser(@RequestBody User newUser) {
		try {

			Date currentTimestamp = new Date(System.currentTimeMillis());
			newUser.getAccount().setOpenDate(currentTimestamp);
			userRepository.save(newUser);
			String email = newUser.getEmail();
			String subject = "Welcome to namma bank";
	        String text = "Dear " + newUser.getFirstName()
	        		+ ",\n\n Thank you for choosing namma bank! "
	        		+ "\n We're pleased to inform you that your new account has been successfully created. \n"
	        		+ "\n\t Your account number is: " + newUser.getAccount().getAccountNum() +". \n"
	        		+ " \n Please keep this number safe for future reference. "
	        		+ "\n If you have any questions or need assistance, feel free to reach out to us. "
	        		+ "\n Thanks for choosing Namma Bank! \n"
	        		+ "\n Warm Regards,"
	        		+ "\n Namma Bank.";
		        
	        emailController.sendEmail(email, subject, text);
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
