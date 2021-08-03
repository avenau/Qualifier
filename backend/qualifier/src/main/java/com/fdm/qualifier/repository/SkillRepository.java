package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{

	public Skill findByName(String name);
	
	public Skill findBySkillId(int id);
}
