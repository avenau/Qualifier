package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.AuthRequest;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;

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
	
	public User getUserByUsername(String username)
	{
	    Optional<User> user = userRepo.findByUsername(username);
	    if (user.isPresent())
	        return user.get();
	    return null;
	}
	
	public boolean userExists(AuthRequest authRequest)
    {
        Optional<User> checker = userRepo.findByUsername(authRequest.getUsername());
        if (checker.isPresent())
            return false;
        return true;
    }

	public User findById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			return null;
		}
		return user.get();
	}
}
