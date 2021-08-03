package com.fdm.qualifier.model;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", " + (name != null ? "name=" + name : "") + "]";
	}

	

}
