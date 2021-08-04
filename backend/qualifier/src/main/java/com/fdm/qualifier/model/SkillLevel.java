package com.fdm.qualifier.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SkillLevel {
	@Id
	@GeneratedValue
	private int skillLevelId;
	private KnowledgeLevel level;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_SKILL")
	@JsonManagedReference(value = "skillLevel")
	private Skill skill;

	@OneToOne
	@JsonBackReference(value = "skillLevel")
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
	public int hashCode() {
		return Objects.hash(level, skill);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillLevel other = (SkillLevel) obj;
		return level == other.level && Objects.equals(skill, other.skill);
	}

	@Override
	public String toString() {
		return "SkillLevel [" + (level != null ? "level=" + level + ", " : "")
				+ (quiz != null ? "quiz=" + quiz + ", " : "") 
				+ (skill != null ? "skill=" + skill + ", " : "")
				+ "skillLevelId=" + skillLevelId + "]";
	}

}
