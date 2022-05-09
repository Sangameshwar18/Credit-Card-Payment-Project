package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.bean.User;
import com.cg.dao.IAdminRepository;
import com.cg.dao.IUserRepository;
import com.cg.exception.AccessDeniedException;
import com.cg.exception.InvalidCredentailsException;
import com.cg.exception.UserNotFoundException;

@Service
public class IUserService {

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private IAdminRepository adminRepo;

	// add User
	public ResponseEntity<String> addUser(User newUser) {

		if (!newUser.getRole().equalsIgnoreCase("ADMIN")) {

			throw new AccessDeniedException("Customer can't create direct account contact to admin");
		}

		if (newUser.getRole().equals("ADMIN"))
			adminRepo.saveAndFlush(new Admin(newUser));

		return new ResponseEntity<String>("User added Successfully", HttpStatus.CREATED);

	}

	// remove User
	public ResponseEntity<String> removeUser(long id) {

		if (!userRepo.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}

		userRepo.deleteById(id);

		return new ResponseEntity<String>("User removed successfully", HttpStatus.OK);
	}

	// Sign In
	public ResponseEntity<Object> signIn(String userId, String password) {

		User user = userRepo.findUserByUserId(userId);

		if (user == null || !user.getPassword().equals(password)) {
			throw new InvalidCredentailsException("Invalid Credentails");
		}

		if (user.getRole().equals("ADMIN"))
			return new ResponseEntity<>("Admin Signed In", HttpStatus.OK);

		return new ResponseEntity<>("Customer Signed In", HttpStatus.OK);
	}

	// Sign Out
	public ResponseEntity<String> signOut() {

		return new ResponseEntity<String>("Signed Out", HttpStatus.OK);
	}

	// Change password
	public ResponseEntity<String> changePassword(long id, String newPassword) {

		if (!userRepo.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}

		User userToUpdate = userRepo.findById(id).get();

		userToUpdate.setPassword(newPassword);

		userRepo.saveAndFlush(userToUpdate);

		return new ResponseEntity<String>("Password Changed Successfully", HttpStatus.OK);
	}

	// Get User By Id
	public User getUserById(long id) {

		if (!userRepo.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}

		return userRepo.findById(id).get();
	}

	// Get User By User Id
	public User getUserByUserId(String userId) {

		User user = userRepo.findUserByUserId(userId);

		if (user == null) {

			throw new UserNotFoundException("User with userId " + userId + " doesn't exist");
		}

		return user;
	}

}
