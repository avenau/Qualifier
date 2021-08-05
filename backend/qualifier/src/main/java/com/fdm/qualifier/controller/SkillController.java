package com.fdm.qualifier.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;
import com.fdm.qualifier.service.SkillLevelService;
import com.fdm.qualifier.service.SkillService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
	private SkillService skillService;
	private SkillLevelService skillLevelService;

	@Autowired
	public SkillController(SkillService skillService, SkillLevelService skillLevelService) {
		super();
		this.skillService = skillService;
		this.skillLevelService = skillLevelService;
	}
	
	@GetMapping("/getAllSkills")
	public List<Skill> getAllSkills(){
		return skillService.findAll();
	}
	
	@GetMapping("/getAllSkillDTOs")
	public List<SkillDTO> getAllSkillsDTO(){
		return skillService.findAllSkillDTOs();
	}
	
	@PostMapping("/updateSkillName")
	public Map<String, String> updateSkillName(@RequestBody Map<String, Object> newSkillName) {
		int skillId = Integer.parseInt((String)newSkillName.get("skillId"));
		String skillName = (String) newSkillName.get("skillName");
		
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "failed");
		if (skillService.skillExist(skillName)) {
			status.put("status", "already exist");
		} else {
			status.put("status", "success");
			Skill skill = skillService.findById(skillId);
			skill.setName(skillName);
			skillService.save(skill);
		}
		
		return status;
	}
	
	@GetMapping("/skill/remove/{id}")
	public void deleteSkill(@PathVariable("id") String id) {
		System.out.println("REMOVE CHECKING");
		int skillId = Integer.parseInt(id);
		System.out.println("SKILL ID: " + skillId);
		skillService.deleteById(skillId);
	}
	
	@PostMapping("/addSkill")
	public Map<String, String> addSkill(@RequestBody String skillname) {
		if (skillname.charAt(skillname.length() -1) == '=') {
			skillname = skillname.substring(0, skillname.length() - 1);
		}
		
		System.out.println("Skill Name: " + skillname);
		
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "failed");
		if (skillService.skillExist(skillname)) {
			status.put("status", "already exist");
		} else {
			status.put("status", "success");
			Skill skill = new Skill(skillname);
			SkillLevel beginner = new SkillLevel(KnowledgeLevel.BEGINNER, skill, null);
			SkillLevel intermediate = new SkillLevel(KnowledgeLevel.INTERMEDIATE, skill, null);
			SkillLevel expert = new SkillLevel(KnowledgeLevel.EXPERT, skill, null);
			skill.addSkillLevel(beginner);
			skill.addSkillLevel(intermediate);
			skill.addSkillLevel(expert);
			skillService.save(skill);
			
		}	
		return status;		

	}

}
