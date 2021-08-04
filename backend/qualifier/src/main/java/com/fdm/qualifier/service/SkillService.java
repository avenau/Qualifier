package com.fdm.qualifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.dto.SkillDTO;
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
	
	public boolean skillExist(String name) {
		if (findByName(name) == null) {
			return false;
		}
		return true;
	}

	public List<SkillDTO> findAllSkillDTOs() {
		List<SkillDTO> skillDTOs = new ArrayList<SkillDTO>();
		List<Skill> skills = skillRepo.findAll();
		for (Skill skill : skills) {
			SkillDTO skillDTO = new SkillDTO(skill);
			skillDTOs.add(skillDTO);
		}
		return skillDTOs;
	}
}
