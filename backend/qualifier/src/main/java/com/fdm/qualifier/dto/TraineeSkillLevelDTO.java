package com.fdm.qualifier.dto;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;

public class TraineeSkillLevelDTO {
	private int skillLevelId;
	private TraineeSkillDTO skill;
	private SkillLevel.KnowledgeLevel level;

	public TraineeSkillLevelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TraineeSkillLevelDTO(int skillLevelId, Skill skill, KnowledgeLevel knowledgeLevel) {
		super();
		this.skillLevelId = skillLevelId;
		this.skill = new TraineeSkillDTO(skill);
		this.level = knowledgeLevel;
	}
	
	public TraineeSkillLevelDTO(SkillLevel skillLevel) {
		super();
		this.skillLevelId = skillLevel.getSkillLevelId();
		this.skill = new TraineeSkillDTO(skillLevel.getSkill());
		this.level = skillLevel.getLevel();
	}

	public int getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(int skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	public TraineeSkillDTO getSkill() {
		return skill;
	}

	public void setSkill(TraineeSkillDTO skill) {
		this.skill = skill;
	}

	public SkillLevel.KnowledgeLevel getLevel() {
		return level;
	}

	public void setLevel(SkillLevel.KnowledgeLevel knowledgeLevel) {
		this.level = knowledgeLevel;
	}

	@Override
	public String toString() {
		return "TraineeSkillLevelDTO [skillLevelId=" + skillLevelId + ", skill=" + skill + ", knowledgeLevel="
				+ level + "]";
	}

}