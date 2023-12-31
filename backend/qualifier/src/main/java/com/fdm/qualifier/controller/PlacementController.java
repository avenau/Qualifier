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

import com.fdm.qualifier.dto.PlacementRecieverDTO;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.ClientService;

import com.fdm.qualifier.service.PlacementService;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.TraineeService;

/**
 * Placement Controller
 * @author William
 *
 */
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

	/**
	 * Saves placement from PlacementDTO
	 * @param placementDTO
	 */
	@PostMapping("/savePlacement")
	public void save(@RequestBody PlacementRecieverDTO placementDTO) {
		log.info("Saving placement: " + placementDTO);
		placementService.saveDTO(placementDTO);
	}
	
	/**
	 * Searches placements by searchTerm and returns List
	 * @param searchTerm
	 * @return
	 */
	@PostMapping("/searchPlacements")
	public List<Placement> searchPlacements(@RequestBody String searchTerm) {
		List<Placement> resultList = new ArrayList<>();
		resultList.addAll(placementService.findByName(searchTerm));
		resultList.addAll(placementService.findByClientName(searchTerm));
		resultList.addAll(placementService.findBySkillName(searchTerm));
		resultList.addAll(placementService.findByLocation(searchTerm));
		return resultList;
	}

	/**
	 * Gets all placements
	 * @return
	 */
	@GetMapping("/getAllPlacements")
	public List<Placement> getAllPlacements(){
		return placementService.findAll();
	}
	
	/**
	 * Adds trainee to placements applied trainees list from id
	 * @param ids
	 * @return
	 */
	@PostMapping("/applyForPlacement")
	public String applyForPlacement(@RequestBody Integer[] ids) {
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		Placement foundPlacement = placementService.findById(ids[1]);
		List<SkillLevel> requiredSkillList = foundPlacement.getSkillsNeeded();
		List<SkillLevel> traineeSkillList = traineeService.getAllSkills(ids[0]);
		if (skillLevelService.isSufficientSkills(traineeSkillList, requiredSkillList)) {
			foundPlacement.addAppliedTrainee(foundTrainee);
			placementService.save(foundPlacement);
			log.info("trainee " + foundTrainee.getFirstName() + "has successfully applied for this placement");
			return "You have successfully applied for this permission";
		} else {
			log.info("trainee " + foundTrainee.getFirstName() + "is not eligible for this placement");
			return "You are not eligible for this position. Please check you possess the required skills";
		}
	}

	/**
	 * Approves trainee for applied placement using traineeId and placementId
	 * @param ids
	 * @return
	 */
	@PostMapping("/approveRequest")
	public String approveREquest(@RequestBody Integer[] ids) {
		System.out.println("Passed in trainee ID is:" + ids[0]);
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		Placement foundPlacement = placementService.findById(ids[1]);
		System.out.println("Adding trainee " + foundTrainee.getFirstName() + " to placement: " + foundPlacement.getName());
		placementService.placeApprovedTrainee(foundPlacement, foundTrainee);
		return foundTrainee.getFirstName() + " " + foundTrainee.getLastName() + " was successfully placed";
	}
	
	
}
