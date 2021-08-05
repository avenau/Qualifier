package com.fdm.qualifier.model;


import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.OneToMany;


@Entity
public class Skill {
	@Id
	@GeneratedValue
	private int skillId;
	private String name;
	
//<<<<<<< HEAD
//	@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "skill")
//	@JsonBackReference(value = "skillLevel")
//	private List<SkillLevel> skillLevels;
//=======
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="skill")
	@JsonBackReference(value = "skillLevel")
	private List<SkillLevel> skilllevels;


	public Skill() {
		super();
	}

	public Skill(String name) {
		super();
		this.name = name;
		this.skilllevels = new ArrayList<SkillLevel>();
		
	}
	
	public Skill(String name, List<SkillLevel> skilllevels) {
		super();
		this.name = name;
		this.skilllevels = skilllevels;
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

	public List<SkillLevel> getSkilllevels() {
		return skilllevels;
	}

	public void setSkilllevels(List<SkillLevel> skilllevels) {
		this.skilllevels = skilllevels;
	}
	public void addSkillLevel(SkillLevel skilllevel) {
		this.skilllevels.add(skilllevel);
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
