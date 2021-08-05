package com.fdm.qualifier.dto;

import java.util.ArrayList;
import java.util.List;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;

public class SkillDTO {
	private int skillId;
	private String name;
	private List<SkillLevelDTO> skillLevels;
	
	public SkillDTO(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.name = skill.getName();
		
		this.skillLevels = new ArrayList<SkillLevelDTO>();
		if (skill.getSkilllevels() != null) {
			for (SkillLevel skillLevel : skill.getSkilllevels()) {
				skillLevels.add(new SkillLevelDTO(skillLevel));
			}
		}
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SkillLevelDTO> getSkillLevels() {
		return skillLevels;
	}

	public void setSkillLevels(List<SkillLevelDTO> skillLevels) {
		this.skillLevels = skillLevels;
	}
	
	
}
