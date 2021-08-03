package com.fdm.qualifier.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/addSkill")
	public Map<String, String> addSkill(@RequestBody String skillname) {
		
		skillname = skillname.substring(0, skillname.length() - 1);
		System.out.println(skillname);
		
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "failed");
		if (skillService.skillExist(skillname)) {
			status.put("status", "already exist");
		} else {
			status.put("status", "success");
			skillService.save(new Skill(skillname));
		}
		
		return status;
	}

}
