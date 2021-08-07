package com.fdm.qualifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.dto.SkillDTO;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.repository.SkillRepository;

/**
 * Skill Service
 * @author William
 *
 */
@Service
public class SkillService {
	private SkillRepository skillRepo;

	@Autowired
	public SkillService(SkillRepository skillRepo) {
		super();
		this.skillRepo = skillRepo;
	}

	/**
	 * Save all skills to repo
	 * @param skills
	 */
	public void save(List<Skill> skills) {
		for(Skill skill : skills) {
			Skill foundSkill = findByName(skill.getName());
			if(foundSkill != null) 
				skills.remove(skill);
		}
		skillRepo.saveAll(skills);
		skillRepo.flush();
	}
	
	/**
	 * Delete skill by id from repo
	 * @param id
	 */
	public void deleteById(int id) {
		skillRepo.deleteById(id);
	}
	
	/**
	 * Find skill based on name from repo
	 * @param name
	 * @return
	 */
	public Skill findByName(String name) {
		return skillRepo.findByName(name);
	}
	
	/**
	 * Find skill by id from repo
	 * @param id
	 * @return
	 */
	public Skill findById(int id) {
		return skillRepo.findBySkillId(id);
	}
	
	/**
	 * Get all skills from repo
	 * @return
	 */
	public List<Skill> findAll(){
		return skillRepo.findAll();
	}

	/**
	 * Save a skill to repo
	 * @param skill
	 * @return
	 */
	public Skill save(Skill skill) {
		return skillRepo.save(skill);
	}
	
	/**
	 * Save skill by Name to repo
	 * @param name
	 * @return
	 */
	public Skill saveByName(String name) {
		Skill skillFound = findByName(name);
		if(skillFound != null)
			return skillFound;	
		return skillRepo.save(new Skill(name));
	}
	
	/**
	 * Checks if skill in repo
	 * @param name
	 * @return
	 */
	public boolean skillExist(String name) {
		if (findByName(name) == null) {
			return false;
		}
		return true;
	}

	/**
	 * Get all skills as SkillDTO
	 * @return
	 */
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
