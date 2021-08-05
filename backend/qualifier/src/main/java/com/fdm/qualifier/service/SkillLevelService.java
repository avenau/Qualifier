package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.SkillLevelRepository;

@Service
public class SkillLevelService {

	private SkillLevelRepository skillLevelRepo;
	
	public boolean isSufficientSkills(List<SkillLevel> traineeSkills, List<SkillLevel> requiredSkills) {
		int matchedSkillCount = 0;
		for (SkillLevel tsl:traineeSkills) {
			for(SkillLevel rsl:requiredSkills) {
				if(tsl.getSkill().getName().equals(rsl.getSkill().getName())) {
					matchedSkillCount += 1;
					if(!isSufficientLevel(tsl,rsl)) {
						return false;
					}
				}
			}
		}
		if (matchedSkillCount != requiredSkills.size()) {
			return false;
		}
		
		return true;
	}
	
	public boolean isSufficientLevel(SkillLevel traineeSL, SkillLevel requiredSL) {
		if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.UNVERIFIED) {
			return false;
		} else if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.BEGINNER && (requiredSL.getLevel() 
				== SkillLevel.KnowledgeLevel.INTERMEDIATE || requiredSL.getLevel() 
						== SkillLevel.KnowledgeLevel.EXPERT)) {
			return false;
		} else if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.INTERMEDIATE && requiredSL.getLevel()
				== SkillLevel.KnowledgeLevel.EXPERT) {
			return false;
		} else {
			return true;
		}
	}

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

	public SkillLevel findByQuizId(Quiz quiz) {
		return skillLevelRepo.findByQuiz(quiz);
	}
}
