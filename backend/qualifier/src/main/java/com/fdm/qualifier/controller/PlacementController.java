package com.fdm.qualifier.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.ClientService;

import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.TraineeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementController {
	
	private Log log = LogFactory.getLog(PlacementController.class);
	private PlacementService placementService;
	private ClientService clientService;
	private TraineeService traineeService;
	private SkillLevelService skillLevelService;
	
	@Autowired
	public PlacementController(PlacementService placementService, ClientService clientService, 
			TraineeService traineeService, SkillLevelService skillLevelService) {
		super();
		this.placementService = placementService;
		this.clientService = clientService;
		this.traineeService = traineeService;
		this.skillLevelService = skillLevelService;
	}
	
	@PostMapping("/savePlacement")
	public void save(@RequestBody Placement placement) {
		log.info("Saving placement: " + placement);
		placementService.save(placement);
	}
	

	@PostMapping("/searchPlacements")
	public List<Placement> searchPlacements(@RequestBody String searchTerm) {
		List<Placement> resultList = new ArrayList<>();
		resultList.addAll(placementService.findByName(searchTerm));
		resultList.addAll(placementService.findByClientName(searchTerm));
		resultList.addAll(placementService.findBySkillName(searchTerm));
		resultList.addAll(placementService.findByLocation(searchTerm));
		return resultList;
	}
	
	@GetMapping("/getAllPlacements")
	public List<Placement> getAllPlacements(){
		return placementService.findAll();
	}
	
	@PostMapping("/applyForPlacement")
	public void applyForPlacement(@RequestBody Integer[] ids) {
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		Placement foundPlacement = placementService.findById(ids[1]);
		List<SkillLevel> requiredSkillList = foundPlacement.getSkillsNeeded();
		List<SkillLevel> traineeSkillList = traineeService.getAllSkills(ids[0]);
		if (skillLevelService.isSufficientSkills(traineeSkillList, requiredSkillList)) {
			foundPlacement.addAppliedTrainee(foundTrainee);
			placementService.save(foundPlacement);
			log.info("trainee " + foundTrainee.getFirstName() + "has successfully applied for this placement");
		} else {
			log.info("trainee " + foundTrainee.getFirstName() + "is not eligible for this placement");
		}
	}
	
	
}
