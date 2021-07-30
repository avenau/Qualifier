package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.TraineeService;

@RestController
public class TraineeController {
	private TraineeService traineeService;

	@Autowired
	public TraineeController(TraineeService traineeService) {
		super();
		this.traineeService = traineeService;
	}

	@GetMapping("/currentTrainee")
	public Trainee getTrainee() {
		//int id = sessionTrainer.getID();
		Trainee test = new Trainee();
		test.setFirstName("John");
		test.setLastName("Smith");
		return test;
		//return traineeService.getTraineeByID(0);
	}
	
}
