package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.User;
import com.fdm.qualifier.service.TraineeService;

@RestController
public class TraineeController {
	private TraineeService traineeService;

	@Autowired
	public TraineeController(TraineeService traineeService) {
		super();
		this.traineeService = traineeService;
	}

	@GetMapping("/getProfile")
	public String getTraineeProfilePage() {
		//int id = session
		//Trainee foundTrainee = traineeService.getTraineeByID(id);
		Trainee foundTrainee = new Trainee();
		foundTrainee.getStream();
		foundTrainee.getRoles();
		foundTrainee.getEmail();
		foundTrainee.getAddress();
		foundTrainee.getPhoneNumber();
		foundTrainee.getCity();
		foundTrainee.getCity();
		
		return "profile";
	}
	
}
