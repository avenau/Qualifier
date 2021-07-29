package com.fdm.qualifier.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.service.SuggestedSkillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SuggestedSkillController {
	private SuggestedSkillService suggestedSkillService;
	
	private Log log = LogFactory.getLog(SuggestedSkillController.class);

	@Autowired
	public SuggestedSkillController(SuggestedSkillService suggestedSkillService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
	}

	@PostMapping("/saveSuggestedSkill")
	public void save(SuggestedSkill suggestedSkill) {
		log.trace("save() called");
		log.info("Saving suggested skill: " + suggestedSkill);
		suggestedSkillService.save(suggestedSkill);
	}
	
	@GetMapping("/getAllSuggestedSkills")
	public List<SuggestedSkill> getAll() {
		log.trace("getAll() called");
		return suggestedSkillService.getAll();
	}
}
