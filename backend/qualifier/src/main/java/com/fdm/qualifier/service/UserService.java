package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	
}
