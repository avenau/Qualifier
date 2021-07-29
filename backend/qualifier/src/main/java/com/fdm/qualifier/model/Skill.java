package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
	@Id
	@GeneratedValue
	private int skillId;
	private String name;

	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Skill(String name) {
		super();
		this.name = name;
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
		return "Skill [skillId=" + skillId + ", name=" + name + "]";
	}

}
