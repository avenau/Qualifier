package com.fdm.qualifier.dto;

import com.fdm.qualifier.model.Skill;

public class TraineeSkillDTO {
	private int skillId;
	private String name;

	public TraineeSkillDTO() {
		super();
	}

	public TraineeSkillDTO(int skillId, String name) {
		super();
		this.skillId = skillId;
		this.name = name;
	}
	
	public TraineeSkillDTO(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.name = skill.getName();
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

	@Override
	public String toString() {
		return "TraineeSkillDTO [skillId=" + skillId + ", name=" + name + "]";
	}

}
