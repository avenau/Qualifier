package com.fdm.qualifier.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.AuthRequest;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;

/**
 * User Service
 * @author William
 *
 */
@Service
public class UserService {
	private Log log = LogFactory.getLog(UserService.class);
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * Get a user from repo by username
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username)
	{
	    Optional<User> user = userRepo.findByUsername(username);
	    if (user.isPresent())
	        return user.get();
	    return null;
	}
	
	/**
	 * Checks if user exists in repo
	 * @param authRequest
	 * @return
	 */
	public boolean userExists(AuthRequest authRequest)
    {
        Optional<User> checker = userRepo.findByUsername(authRequest.getUsername());
        if (checker.isPresent())
            return false;
        return true;
    }

	/**
	 * Get a user from repo by Id
	 * @param userId
	 * @return
	 */
	public User findById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			return null;
		}
		return user.get();
	}
	
	/**
	 * Save a user to repo
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {
		return userRepo.save(user);
	}
}
