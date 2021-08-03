package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.SkillLevelRepository;

@Service
public class SkillLevelService {

	private SkillLevelRepository skillLevelRepo;

	public SkillLevelService(SkillLevelRepository skillLevelRepo) {
		this.skillLevelRepo = skillLevelRepo;
	}

	public Optional<SkillLevel> findById(int id) {
		return skillLevelRepo.findById(id);
	}
	
	public SkillLevel getById(int id) {
		return skillLevelRepo.findByskillLevelId(id);
	}

	public List<SkillLevel> findAll() {
		return skillLevelRepo.findAll();
	}

	public List<SkillLevel> findBySkill(Skill skill) {
		return skillLevelRepo.findBySkill(skill);
	}

	public void delete(SkillLevel skillLevel) {
		skillLevelRepo.delete(skillLevel);
	}

	public SkillLevel save(SkillLevel skillLevel) {
		return skillLevelRepo.save(skillLevel);
	}

	public void save(List<SkillLevel> skillLevels) {
		skillLevelRepo.saveAll(skillLevels);
		skillLevelRepo.flush();
	}
}
