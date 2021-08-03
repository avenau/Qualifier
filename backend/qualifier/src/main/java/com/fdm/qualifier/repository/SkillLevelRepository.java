package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Integer> {

	public List<SkillLevel> findBySkill(Skill skill);
	
	public SkillLevel findByskillLevelId(int id);
}
