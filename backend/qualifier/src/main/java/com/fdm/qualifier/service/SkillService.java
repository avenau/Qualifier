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

	public void save(List<Skill> skills) {
		for(Skill skill : skills) {
			Skill foundSkill = findByName(skill.getName());
			if(foundSkill != null) 
				skills.remove(skill);
		}
		skillRepo.saveAll(skills);
		skillRepo.flush();
	}
	
	public void deleteById(int id) {
		skillRepo.deleteById(id);
	}
	
	public Skill findByName(String name) {
		return skillRepo.findByName(name);
	}
	
	public Skill findById(int id) {
		return skillRepo.findBySkillId(id);
	}
	
	public List<Skill> findAll(){
		return skillRepo.findAll();
	}
	
	public Skill save(Skill skill) {
		Skill skillFound = findByName(skill.getName());
		if(skillFound != null)
			skill = skillFound;		
		return skillRepo.save(skill);
	}
	
	public Skill saveByName(String name) {
		Skill skillFound = findByName(name);
		if(skillFound != null)
			return skillFound;	
		return skillRepo.save(new Skill(name));
	}
	
	
	public boolean skillExist(String name) {
		if (findByName(name) == null) {
			return false;
		}
		return true;
	}
}
