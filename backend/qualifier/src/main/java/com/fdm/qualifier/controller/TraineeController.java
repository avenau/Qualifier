package com.fdm.qualifier.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/addUnverifiedSkill")
	public void addUnverifiedSkill(@RequestBody Integer[] ids) {
		Trainee foundTrainee = traineeService.getTraineeByID(ids[0]);
		List<SkillLevel> skill = skillLevelService.findBySkill(skillService.findById(ids[1]));
		SkillLevel unverifiedSkill = null;
		for (SkillLevel sl: skill) {
			if (sl.getLevel()==SkillLevel.KnowledgeLevel.UNVERIFIED) {
				unverifiedSkill = sl;
			}
		}
		traineeService.addSkillToTrainee(unverifiedSkill, ids[0]);
		traineeService.save(foundTrainee);	
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
	public List<SkillLevel> getPinnedSkills(@RequestBody Trainee trainee) {
		log.debug(trainee);
		return traineeService.getPinnedSkills(trainee.getUserId());
	}
	
	@PostMapping("/getSkills")
	public List<SkillLevel> getSkills(@RequestBody Trainee trainee) {
		log.debug(trainee);
		return traineeService.getSkills(trainee.getUserId());
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

}
