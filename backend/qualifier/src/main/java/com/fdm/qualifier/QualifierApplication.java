package com.fdm.qualifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;

@SpringBootApplication
public class QualifierApplication {
	
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(QualifierApplication.class, args);
	}

}
