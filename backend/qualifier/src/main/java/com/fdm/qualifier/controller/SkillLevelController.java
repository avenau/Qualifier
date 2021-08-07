package com.fdm.qualifier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.model.SkillLevel;

/**
 * SkillLevel Controller
 * @author William
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SkillLevelController {
		
	private SkillLevelService skillLevelService;

	@Autowired
	public SkillLevelController(SkillLevelService skillLevelService) {
		super();
		this.skillLevelService = skillLevelService;
	}
	
	/**
	 * Get all skill levels
	 * @return
	 */
	@GetMapping("/getAllSkillLevels")
	public List<SkillLevel> getAllSkillLevels(){
		return skillLevelService.findAll();
	}
		
		
	
}
