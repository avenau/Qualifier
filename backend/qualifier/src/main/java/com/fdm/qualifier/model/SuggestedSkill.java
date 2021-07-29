package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SuggestedSkill {
	@Id
	@GeneratedValue
	private int suggestedSkillId;
	private String name;

	public SuggestedSkill() {
		super();
	}

	public SuggestedSkill(String name) {
		super();
		this.name = name;
	}

	public int getSuggestedSkillId() {
		return suggestedSkillId;
	}

	public void setSuggestedSkillId(int suggestedSkillId) {
		this.suggestedSkillId = suggestedSkillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SuggestedSkill [suggestedSkillId=" + suggestedSkillId + ", name=" + name + "]";
	}

}
