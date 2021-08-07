package com.fdm.qualifier.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;
import com.fdm.qualifier.service.SkillService;

/**
 * Skill Controller
 * 
 * @author William
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
	protected static final String SUCCESS_STRING = "success";
	protected static final String ALREADY_EXIST_STRING = "already exist";
	protected static final String FAILED_STRING = "failed";
	protected static final String STATUS_KEY = "status";
	protected static final String SKILL_NAME_KEY = "skillName";
	protected static final String SKILL_ID_KEY = "skillId";
	
	private SkillService skillService;

	@Autowired
	public SkillController(SkillService skillService) {
		super();
		this.skillService = skillService;
	}

	/**
	 * Gets all skills from repo
	 * 
	 * @return
	 */
	@GetMapping("/getAllSkills")
	public List<Skill> getAllSkills() {
		return skillService.findAll();
	}

	/**
	 * Gets all Skills as DTO
	 * 
	 * @return
	 */
	@GetMapping("/getAllSkillDTOs")
	public List<SkillDTO> getAllSkillsDTO() {
		return skillService.findAllSkillDTOs();
	}

	/**
	 * Updates a skill name
	 * 
	 * @param newSkillName
	 * @return
	 */
	@PostMapping("/updateSkillName")
	public Map<String, String> updateSkillName(@RequestBody Map<String, Object> newSkillName) {
		int skillId = Integer.parseInt((String) newSkillName.get(SKILL_ID_KEY));
		String skillName = (String) newSkillName.get(SKILL_NAME_KEY);

		Map<String, String> status = new HashMap<String, String>();
		status.put(STATUS_KEY, FAILED_STRING);
		if (skillService.skillExist(skillName)) {
			status.put(STATUS_KEY, ALREADY_EXIST_STRING);
		} else {
			status.put(STATUS_KEY, SUCCESS_STRING);
			Skill skill = skillService.findById(skillId);
			skill.setName(skillName);
			skillService.save(skill);
		}

		return status;
	}

	/**
	 * Delete a skill
	 * 
	 * @param id
	 */
	@GetMapping("/skill/remove/{id}")
	public void deleteSkill(@PathVariable("id") String id) {
		int skillId = Integer.parseInt(id);
		skillService.deleteById(skillId);
	}

	/**
	 * Add a skill
	 * 
	 * @param skillname
	 * @return
	 */
	@PostMapping("/addSkill")
	public Map<String, String> addSkill(@RequestBody String skillname) {
		if (skillname.charAt(skillname.length() - 1) == '=') {
			skillname = skillname.substring(0, skillname.length() - 1);
		}

		Map<String, String> status = new HashMap<String, String>();
		status.put(STATUS_KEY, FAILED_STRING);
		if (skillService.skillExist(skillname)) {
			status.put(STATUS_KEY, ALREADY_EXIST_STRING);
		} else {
			status.put(STATUS_KEY, SUCCESS_STRING);
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
