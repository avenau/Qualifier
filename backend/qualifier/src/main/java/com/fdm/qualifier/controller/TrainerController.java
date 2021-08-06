package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.TrainerService;

/**
 * Trainer Controller
 * @author William
 *
 */
@RestController
public class TrainerController {
	private TrainerService trainerService;

	@Autowired
	public TrainerController(TrainerService trainerService) {
		super();
		this.trainerService = trainerService;
	}

}
