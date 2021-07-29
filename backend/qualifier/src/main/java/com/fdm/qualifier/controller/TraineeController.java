package com.fdm.qualifier.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.TraineeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {
	private TraineeService traineeService;

	private Log log = LogFactory.getLog(TraineeController.class);
	
	@Autowired
	public TraineeController(TraineeService traineeService) {
		super();
		this.traineeService = traineeService;
	}

	@PostMapping("/changePinnedSkill")
	public Trainee changePinnedSkills(@RequestBody Trainee trainee) {
		log.trace("changePinnedSkills() called");
//		traineeService.changePinnedSkills(trainee);
		return trainee;
	}
}
