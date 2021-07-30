package com.fdm.qualifier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.repository.SkillRepository;

@Service
public class SkillService {
	private SkillRepository skillRepo;

	@Autowired
	public SkillService(SkillRepository skillRepo) {
		super();
		this.skillRepo = skillRepo;
	}
	
	public Skill findByName(String name) {
		return skillRepo.findByName(name);
	}
	
	public Skill save(Skill skill) {
		return skillRepo.save(skill);
	}
}
