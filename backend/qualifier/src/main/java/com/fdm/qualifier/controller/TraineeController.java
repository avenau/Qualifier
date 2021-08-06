package com.fdm.qualifier.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdm.qualifier.dto.TraineeSkillLevelDTO;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.Skill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdm.qualifier.model.Skill;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;
import com.fdm.qualifier.service.TraineeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TraineeController {
	private TraineeService traineeService;
	private SkillLevelService skillLevelService;
	private SkillService skillService;

	private Log log = LogFactory.getLog(TraineeController.class);
	
	@Autowired
	public TraineeController(TraineeService traineeService, SkillLevelService skillLevelService, SkillService skillService) {
		super();
		this.traineeService = traineeService;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
	}

	@PostMapping("/changePinnedSkill")
	public Trainee changePinnedSkills(@RequestBody Trainee trainee) {
		log.trace("changePinnedSkills() called");
//		traineeService.changePinnedSkills(trainee);
		return trainee;
	}
	
	@PostMapping("/addUnverifiedSkill")
	public SkillLevel addUnverifiedSkill(@RequestBody Integer[] ids) {
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		List<SkillLevel> skill = skillLevelService.findBySkill(skillService.findById(ids[1]));
		SkillLevel unverifiedSkill = null;
		boolean addedSkill = false;
		
		for (SkillLevel sl: skill) {
			if (sl.getLevel()==SkillLevel.KnowledgeLevel.UNVERIFIED) {
				unverifiedSkill = sl;
			}
		}
		
		addedSkill = traineeService.addSkillToTrainee(unverifiedSkill, ids[0]);
		traineeService.save(foundTrainee);
		log.debug(foundTrainee.getSkills());
		log.debug(unverifiedSkill);

		return addedSkill ? unverifiedSkill : null;
	}
	 
	@PostMapping("/removeTraineeSkill")
	public void removeTraineeSkill(@RequestBody Integer[] ids) {
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		SkillLevel skillLevel = skillLevelService.getById(ids[1]);
		Skill skill = skillLevel.getSkill();
		traineeService.removeSkillFromTrainee(skill, ids[0]);
		traineeService.save(foundTrainee);
	}

//	@PostMapping("/changePinnedSkill")
//	public Trainee changePinnedSkills(@RequestBody Trainee trainee) {
//		log.trace("changePinnedSkills() called");
////		traineeService.changePinnedSkills(trainee);
//		return trainee;
//	}

	@GetMapping("/getAllTrainees")
	public List<Trainee> getAllTrainees() {
		return traineeService.getAllTrainees();
	}
	
	@PostMapping("/getPinnedSkills")
	public List<TraineeSkillLevelDTO> getPinnedSkills(@RequestBody int[] traineeId) {
		log.debug("Getting Pinned Skills of traineeId: " + traineeId);
		return traineeService.getPinnedSkillsAsDTO(traineeId[0]);
	}
	
	@PostMapping("/getSkills")
	public List<TraineeSkillLevelDTO> getSkills(@RequestBody int[] traineeId) {
		log.debug("Getting Skills of traineeId: " + traineeId);
		return traineeService.getSkillsAsDTO(traineeId[0]);
	}
	
	@PostMapping("/pinSkill")
	public String pinSkill(@RequestBody Integer[] ids) {
		log.debug(ids[0] + " " + ids[1]);
		return traineeService.pinSkill(ids[0], ids[1]);
	}

	@PostMapping("/unpinSkill")
	public String unpinSkill(@RequestBody Integer[] ids) {
		return traineeService.unpinSkill(ids[0], ids[1]);
	}
	

	@PostMapping("/searchTrainees")
	public List<Trainee> searchTrainees(@RequestBody String searchTerm){
		List<Trainee> result = new ArrayList<>();
		result.addAll(traineeService.findTraineeByName(searchTerm));
		result.addAll(traineeService.findBySkillName(searchTerm));
		if(searchTerm.split(" ").length ==2) {
			String[] splitName = searchTerm.split(" ");
			result.addAll(traineeService.findByFirstAndLastName(splitName[0], splitName[1]));
		}
		return result;
	}

	@PostMapping("/getTraineesResults")
	public List<Result> getTraineeResults(@RequestBody int[] traineeId) {
		log.debug(traineeId);
		List<Result> results = traineeService.getAllResults(traineeId[0]);
		log.debug(results);
		return results;
	}

}
