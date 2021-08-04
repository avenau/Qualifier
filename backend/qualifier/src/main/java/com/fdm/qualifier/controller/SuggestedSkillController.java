package com.fdm.qualifier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Skill;
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
	public String save(@RequestBody SuggestedSkill suggestedSkill) {
		log.trace("save() called");
		log.info("Saving suggested skill: " + suggestedSkill);
		return suggestedSkillService.save(suggestedSkill);
	}
	
	@GetMapping("/getAllSuggestedSkills")
	public List<SuggestedSkill> getAll() {
		log.trace("getAll() called");
		return suggestedSkillService.getAll();
	}
	
	@PostMapping("/removeSuggestedSkill")
	public Map<String, String> removeSuggestedSkill(@RequestBody String skillname) {
		if (skillname.charAt(skillname.length() -1) == '=') {
			skillname = skillname.substring(0, skillname.length() - 1);
		}
		
		System.out.println("SkillName " + skillname);
		Map<String, String> status = new HashMap<String, String>();
		if (suggestedSkillService.findByName(skillname).isEmpty()) {
			status.put("status", "failed");
			return status;
		}
		SuggestedSkill suggestedSkill = suggestedSkillService.findByName(skillname).get(0);
		suggestedSkillService.declineSuggestedSkill(suggestedSkill);
		status.put("status", "success");
		return status;		
	}
}
