package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Skill;
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
	
	@PostMapping("/addUnverifiedSkill")
	public void addUnverifiedSkill(Skill skill) {
		//Get Current Trainee
		//Add skill to Trainee
	}
	
}
