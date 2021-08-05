package com.fdm.qualifier.dto;

import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.SkillLevel.KnowledgeLevel;

public class SkillLevelDTO {
	private int skillLevelId;
	private KnowledgeLevel knowledgelevel;
	private Integer quizId;
	
	public SkillLevelDTO(SkillLevel skillLevel) {
		super();
		this.skillLevelId = skillLevel.getSkillLevelId();
		this.knowledgelevel = skillLevel.getLevel();
		if (skillLevel.getQuiz() != null) {
			this.quizId = skillLevel.getQuiz().getQuizId();
		}
	}

	public int getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(int skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	public KnowledgeLevel getKnowledgelevel() {
		return knowledgelevel;
	}

	public void setKnowledgelevel(KnowledgeLevel knowledgelevel) {
		this.knowledgelevel = knowledgelevel;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}
	
	
}
