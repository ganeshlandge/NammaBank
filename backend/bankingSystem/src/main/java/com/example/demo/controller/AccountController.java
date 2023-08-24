package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;

import javax.validation.constraints.Null;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.NewPasswordDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.ForgotPassword;
import com.example.demo.model.ForgotUsername;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ForgotPasswordRepository;
import com.example.demo.repository.ForgotUsernameRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtHelper;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ForgotUsernameRepository forgotUsernameRepository;
	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailController emailController;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtHelper helper;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/netbanking/login")
//<<<<<<< HEAD
//	public ResponseEntity<?>  login(@RequestBody Login login) {
//		Optional<Account> account = accountRepository.findByUsernameAndLoginPasswd(login.getUsername(), login.getPassword());
//		if(account.isPresent()) {
//			LoginResponse resp = new LoginResponse();
//			resp.setUserId(account.get().getUser().getId());
//			resp.setAccountNum(account.get().getAccountNum());
//			resp.setUsername(account.get().getUsername());
//			resp.setEmail(account.get().getUser().getEmail());
//	        return ResponseEntity.ok(new SuccessResponse<>("Login successful",resp));
//		} else {
//			throw new UnauthorizedException("Username or password is incorrect");
//		}
//=======
	public ResponseEntity<?> login(@RequestBody JwtRequest request) {
		this.doAuthenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);
		Optional<Account> account = accountRepository.findByUsername(request.getUsername());
		
		JwtResponse response = new JwtResponse.Builder()
				.jwtToken(token)
				.username(userDetails.getUsername())
				.accountNum(account.get().getAccountNum())
				.email(account.get().getUser().getEmail())
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
//		Optional<Account> account = accountRepository.findByUsernameAndLoginPasswd(login.getUsername(), login.getPassword());
//		if(account.isPresent()) {
//	        return ResponseEntity.ok(new SuccessResponse<>("Login successful"));
//		} else {
//			throw new UnauthorizedException("Username or password is incorrect");
//		}
//>>>>>>> aead1c5 (added jwt support)
	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/netbanking/register")
	public ResponseEntity<?> register(@RequestBody Account account) {
		Optional<Account> currentAccount = accountRepository.findById(account.getAccountNum());
		if (currentAccount.isPresent()) {
			User user = currentAccount.get().getUser();
			if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().isEmpty()) {
				throw new BadRequestException("Invalid request. Email is not linked with this account.");
			}
			String email = user.getEmail();

			Random random = new Random();
	        int code = random.nextInt(9000) + 1000; 
	        
	        String subject = "Internet Banking registration OTP";
	        String text = " Dear Customer, \n "
	        		+ "Your OTP for netbanking registration is: " + code + ".\n"
	        		+ "\n NEVER share OTP. Not you? Contact us \n"
	        		+ "\n Thanks for choosing Namma Bank! \n"
	        		+ "\n Warm Regards,"
	        		+ "\n Namma Bank.";
		        
	        emailController.sendEmail(email, subject, text);
		        
	        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			forgotUsernameRepository.save(new ForgotUsername(currentAccount.get().getAccountNum(), code, currentTimestamp));
	        return ResponseEntity.ok(new SuccessResponse<>("OTP generated successfully for netbanking registration", String.valueOf(code)));
		}
		throw new ResourceNotFoundException("Account " + account.getAccountNum() + " does not exists");
	}

	@PostMapping("/netbanking/register/validate")
	public ResponseEntity<?> registerValidate(@RequestBody AccountDTO newAccount) {
		Optional<ForgotUsername> currentFgPass = forgotUsernameRepository.findById(newAccount.getAccountNum());
		if (currentFgPass.isPresent()) {
			if (newAccount.getOtp() != currentFgPass.get().getOtp()) {
				throw new BadRequestException("Invalid OTP");
			}
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			long millisecondsDifference = currentTimestamp.getTime() - currentFgPass.get().getTimestamp().getTime();
			Duration duration = Duration.ofMillis(millisecondsDifference);
			long seconds = duration.toSecondsPart();
			if (seconds > 60) {
				throw new BadRequestException("OTP expired. Please generate again");
			}
			Optional<Account> currentAccount = accountRepository.findById(newAccount.getAccountNum());
			if (currentAccount.isPresent()) {
				if (currentAccount.get().getUsername() == null || currentAccount.get().getUsername().isEmpty() || currentAccount.get().getUsername().isBlank()) {
					currentAccount.get().setUsername(newAccount.getUsername());
					currentAccount.get().setLoginPasswd(passwordEncoder.encode(newAccount.getLoginPasswd()));
					currentAccount.get().setTranscationPasswd(newAccount.getTranscationPasswd());
					accountRepository.save(currentAccount.get());
					User user = currentAccount.get().getUser();
					if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().isEmpty()) {
				        throw new BadRequestException("Invalid request. Email is not linked with this account.");
				    }
					String email = user.getEmail();
					String subject = "Internet Banking registration completed";
			        String text = "Congratulations! "
			        		+ "\n\n Dear " + user.getFirstName() 
			        		+ ",\n Your netbanking registration is now complete. \n"
			        		+ "\n\n You can log in using your username: " + currentAccount.get().getUsername() +" and the password you've set during the registration process. \n"
			        		+ "\n Enjoy the convenience of managing your finances online."
			        		+ "\n If you have any queries or require support, don't hesitate to get in touch with us.\n "
			        		+ "\n Thanks for choosing Namma Bank! \n"
			        		+ "\n Warm Regards,"
			        		+ "\n Namma Bank.";
				        
			        emailController.sendEmail(email, subject, text);
			        return ResponseEntity.ok(new SuccessResponse<>("Registration for Internet Banking is successful"));
				} else {
					throw new ResourceConflictException("User account already exists");
				}
			} else {
				throw new ResourceNotFoundException("Account " + newAccount.getAccountNum() + " does not exists");
			}
		}
		throw new ResourceNotFoundException("Account " + newAccount.getAccountNum() + " does not exists");
	}

	@PostMapping("/netbanking/forgot_username")
	public ResponseEntity<?> fogotUsername(@RequestBody Account account) {
		Optional<Account> currentAccount = accountRepository.findById(account.getAccountNum());
		if (currentAccount.isPresent()) {
			System.out.println("Username is " + currentAccount.get().getUsername());

			if (currentAccount.get().getUsername().isBlank() || currentAccount.get().getUsername().isEmpty()) {
				throw new BadRequestException("Invalid request. This account is not registered for netbanking");
			}
			User user = currentAccount.get().getUser();
			if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().isEmpty()) {
				throw new BadRequestException(
						"Invalid request. Email error!x This account is not registered for netbanking");
			}
			String email = user.getEmail();

			Random random = new Random();
	        int code = random.nextInt(9000) + 1000;
	        
	        String subject = "OTP for Forgot Username";
	        String text = "Dear Customer, " 
	        		+ "\n Your username is: " + currentAccount.get().getUsername()
	        		+ "\n Your OTP is: " + code + ".\n"
	        		+ "\n NEVER share OTP. Not you? Contact us. \n"
	        		+ "\n Thanks for choosing Namma Bank! \n"
	        		+ "\n Warm Regards,"
	        		+ "\n Namma Bank.";
	        
	        emailController.sendEmail(email, subject, text);
	        
	        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			forgotUsernameRepository.save(new ForgotUsername(currentAccount.get().getAccountNum(), currentAccount.get().getUsername(), code, currentTimestamp));
	        return ResponseEntity.ok(new SuccessResponse<>("OTP generated successfully", String.valueOf(code)));
		}
		throw new ResourceNotFoundException("Account " + account.getAccountNum() + " does not exists");
	}

	@PostMapping("/netbanking/forgot_username/validate")
	public ResponseEntity<?> fogotUsernameValidate(@RequestBody ForgotUsername fgusername) {
		Optional<ForgotUsername> currentFgPass = forgotUsernameRepository.findById(fgusername.getAccountNum());
		if (currentFgPass.isPresent()) {
			if (fgusername.getOtp() != currentFgPass.get().getOtp()) {
				throw new BadRequestException("Invalid OTP");
			}
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			long millisecondsDifference = currentTimestamp.getTime() - currentFgPass.get().getTimestamp().getTime();
			Duration duration = Duration.ofMillis(millisecondsDifference);
			long seconds = duration.toSecondsPart();
			if (seconds > 60) {
				throw new BadRequestException("OTP expired. Please generate again");
			}
			return ResponseEntity.ok(new SuccessResponse<>("Valid OTP"));
		}
		throw new ResourceNotFoundException("Account " + fgusername.getAccountNum() + " does not exists");
	}

	@PostMapping("/netbanking/forgot_password")
	public ResponseEntity<?> fogotPassword(@RequestBody Account account) {
		Optional<Account> currentAccount = accountRepository.findByUsername(account.getUsername());
		if (currentAccount.isPresent()) {
			if (currentAccount.get().getUsername().isBlank() || currentAccount.get().getUsername().isEmpty()) {
				throw new BadRequestException("Invalid request. This account is not registered for netbanking");
			}

			User user = currentAccount.get().getUser();
			if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().isEmpty()) {
				throw new BadRequestException(
						"Invalid request. Email error!x This account is not registered for netbanking");
			}
			String email = user.getEmail();

			Random random = new Random();
	        int code = random.nextInt(9000) + 1000; // Generate a number between 1000 and 9999
	        String subject = "OTP for reset Password";
	        String text = "Dear Customer, " 
	        		+ "\n Your password reset OTP is: " + code + ".\n"
	        		+ "\n NEVER share OTP. Not you? Contact us. \n"
	        		+ "\n Thanks for choosing Namma Bank! \n"
	        		+ "\n Warm Regards,"
	        		+ "\n Namma Bank.";
	        
	        emailController.sendEmail(email, subject, text);
	        
	        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			forgotPasswordRepository.save(new ForgotPassword(currentAccount.get().getUsername(), code, currentTimestamp));
	        return ResponseEntity.ok(new SuccessResponse<>("OTP generated successfully", String.valueOf(code)));
			
		}
		throw new ResourceNotFoundException("Account does not exists  or user is not registered for netbanking");
	}

	@PostMapping("/netbanking/forgot_password/validate")
	public ResponseEntity<?> fogotPasswordValidate(@RequestBody ForgotPassword fgpass) {
		Optional<ForgotPassword> currentFgPass = forgotPasswordRepository.findById(fgpass.getUsername());
		if (currentFgPass.isPresent()) {
			if (fgpass.getOtp() != currentFgPass.get().getOtp()) {
				throw new BadRequestException("Invalid OTP");
			}
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			long millisecondsDifference = currentTimestamp.getTime() - currentFgPass.get().getTimestamp().getTime();
			Duration duration = Duration.ofMillis(millisecondsDifference);
			long seconds = duration.toSecondsPart();
			if (seconds > 60) {
				throw new BadRequestException("OTP expired. Please generate again");
			}
			return ResponseEntity.ok(new SuccessResponse<>("Valid OTP"));
		}
		throw new ResourceNotFoundException("Account does not exists");
	}

	@PostMapping("/netbanking/new_password")
	public ResponseEntity<?> setNewPassword(@RequestBody NewPasswordDTO newpasswd) {
		Optional<Account> currentAccount = accountRepository.findById(newpasswd.getAccountNum());
		if (currentAccount.isPresent()) {
			currentAccount.get().setLoginPasswd(newpasswd.getPassword());
			accountRepository.save(currentAccount.get());
//			TODO: check if save operation fails
			User user = currentAccount.get().getUser();
			if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().isEmpty()) {
		        throw new BadRequestException("Invalid request. Email error!x This account is not registered for netbanking");
		    }
			String email = user.getEmail();
			
			Random random = new Random();
	        int code = random.nextInt(9000) + 1000; // Generate a number between 1000 and 9999
	        String subject = "Alert! Password Changed";
	        String text = "Dear Customer, " 
	        		+ "\n Your password is changed successfully! " + code + "/\n"
	        		+ "\n NEVER share OTP. Not you? Contact us.\n"
	        		+ "\n Thanks for choosing Namma Bank! \n"
	        		+ "\n Warm Regards,"
	        		+ "\n Namma Bank.";
	        
	        emailController.sendEmail(email, subject, text);
			
	        return ResponseEntity.ok(new SuccessResponse<>("Updated password successfully"));
		}
		throw new ResourceNotFoundException("Account does not exists");
	}
	
	@GetMapping("/netbanking/{username}")
	public ResponseEntity<?> getAccountDetails(@PathVariable String username){
		Optional<Account> account = accountRepository.findByUsername(username);
		if(account.isPresent()) {
			return ResponseEntity.ok(account.get());
		}
		throw new ResourceNotFoundException("Account does not exists");
	}
	
}
