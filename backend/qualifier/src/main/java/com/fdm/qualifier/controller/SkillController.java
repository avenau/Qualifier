package com.fdm.qualifier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.service.SkillService;

@RestController
@CrossOrigin(origins = "http://localhost:3005")
public class SkillController {
	private SkillService skillService;

	@Autowired
	public SkillController(SkillService skillService) {
		super();
		this.skillService = skillService;
	}
	
	@GetMapping("/getAllSkills")
	public List<Skill> getAllSkills(){
		return skillService.findAll();
	}

}
