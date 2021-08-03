package com.fdm.qualifier.controller;

import java.util.List;

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
	
	@PostMapping("/addUnverifiedSkill")
	public void addUnverifiedSkill(@RequestBody Trainee trainee, SkillLevel skill) {
		traineeService.addSkillToTrainee(skill, trainee.getUserId());
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
