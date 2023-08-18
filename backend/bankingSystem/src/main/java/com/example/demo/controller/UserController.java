package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/api/success")
    public ApiResponse<String> successfulResponse() {
        String successMessage = "Request was successful!";
        return new ApiResponse(HttpStatus.OK.value(), successMessage, "");
    }
	
	@PostMapping("/user")
	public ApiResponse<String> addUser(@RequestBody User newUser) {
		try {
			userRepository.save(newUser);
		} catch(IllegalArgumentException ex) {
	        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), "");			
		} catch(ConstraintViolationException ex) {
	        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.toString(), "");			
		} catch(Exception ex) {
	        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "");
		}
		return new ApiResponse<String>(HttpStatus.OK.value(), "User created successfully", "");
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
	
	@PutMapping("/user/address/{id}")
	public ResponseEntity<String> updateUserAddress(@RequestBody User updatedUser, @PathVariable long id) {
		try {
            Optional<User> existingUserOptional = userRepository.findById(id);

            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                if (updatedUser.getPermAddId() != null) {
                    existingUser.setPermAddId(updatedUser.getPermAddId());
                }

                if (updatedUser.getTempAddId() != null) {
                    existingUser.setTempAddId(updatedUser.getTempAddId());
                }

                userRepository.save(existingUser);

                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user address");
        }
	}

	
}
