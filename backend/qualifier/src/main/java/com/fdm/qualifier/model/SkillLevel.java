package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SkillLevel {
	@Id
	@GeneratedValue
	private int skillLevelId;
	private KnowledgeLevel level;

	@ManyToOne
	@JoinColumn(name = "FK_SKILL")
	private Skill skill;

	@ManyToOne
	@JoinColumn(name = "FK_QUIZ")
	@OneToOne
	private Quiz quiz;

	public enum KnowledgeLevel {
		UNVERIFIED, BEGINNER, INTERMEDIATE, EXPERT
	}

	public SkillLevel() {
		super();
	}

	public SkillLevel(KnowledgeLevel level, Skill skill, Quiz quiz) {
		super();
		this.level = level;
		this.skill = skill;
		this.quiz = quiz;
	}

	public int getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(int skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public KnowledgeLevel getLevel() {
		return level;
	}

	public void setLevel(KnowledgeLevel level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "SkillLevel [" + (level != null ? "level=" + level + ", " : "")
				+ (quiz != null ? "quiz=" + quiz + ", " : "") + (skill != null ? "skill=" + skill + ", " : "")
				+ "skillLevelId=" + skillLevelId + "]";
	}

}
