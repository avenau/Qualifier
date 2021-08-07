package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.SkillLevelRepository;

/**
 * SkillLevel Service
 * 
 * @author William
 *
 */
@Service
public class SkillLevelService {

	private SkillLevelRepository skillLevelRepo;

	public SkillLevelService(SkillLevelRepository skillLevelRepo) {
		this.skillLevelRepo = skillLevelRepo;
	}

	/**
	 * Checks if a trainee has all skills in the list
	 * 
	 * @param traineeSkills
	 * @param requiredSkills
	 * @return
	 */
	public boolean isSufficientSkills(List<SkillLevel> traineeSkills, List<SkillLevel> requiredSkills) {
		int matchedSkillCount = 0;
		for (SkillLevel tsl : traineeSkills) {
			for (SkillLevel rsl : requiredSkills) {
				if (tsl.getSkill().getName().equals(rsl.getSkill().getName())) {
					matchedSkillCount += 1;
					if (!isSufficientLevel(tsl, rsl)) {
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

	/**
	 * Checks if trainee skillLevel equal required skillLevel
	 * 
	 * @param traineeSL
	 * @param requiredSL
	 * @return
	 */
	public boolean isSufficientLevel(SkillLevel traineeSL, SkillLevel requiredSL) {
		if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.UNVERIFIED) {
			return false;
		} else if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.BEGINNER
				&& (requiredSL.getLevel() == SkillLevel.KnowledgeLevel.INTERMEDIATE
						|| requiredSL.getLevel() == SkillLevel.KnowledgeLevel.EXPERT)) {
			return false;
		} else if (traineeSL.getLevel() == SkillLevel.KnowledgeLevel.INTERMEDIATE
				&& requiredSL.getLevel() == SkillLevel.KnowledgeLevel.EXPERT) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get optional skillLevel by id from repo
	 * @param id
	 * @return
	 */
	public Optional<SkillLevel> findById(int id) {
		return skillLevelRepo.findById(id);
	}

	/**
	 * Get skillLevel By Id from repo
	 * @param id
	 * @return
	 */
	public SkillLevel getById(int id) {
		return skillLevelRepo.findByskillLevelId(id);
	}

	/**
	 * Get all skillLevels
	 * @return
	 */
	public List<SkillLevel> findAll() {
		return skillLevelRepo.findAll();
	}

	/**
	 * Get skillLevels by skill
	 * @param skill
	 * @return
	 */
	public List<SkillLevel> findBySkill(Skill skill) {
		return skillLevelRepo.findBySkill(skill);
	}

	/**
	 * Delete a skillLevel from repo
	 * @param skillLevel
	 */
	public void delete(SkillLevel skillLevel) {
		skillLevelRepo.delete(skillLevel);
	}

	/**
	 * Save a skillLevel to repo
	 * @param skillLevel
	 * @return
	 */
	public SkillLevel save(SkillLevel skillLevel) {
		return skillLevelRepo.save(skillLevel);
	}

	/**
	 * Save all skillLevels to repo
	 * @param skillLevels
	 */
	public void save(List<SkillLevel> skillLevels) {
		skillLevelRepo.saveAll(skillLevels);
		skillLevelRepo.flush();
	}

	/**
	 * Find SkillLevel by quiz from repo
	 * @param quiz
	 * @return
	 */
	public SkillLevel findByQuizId(Quiz quiz) {
		return skillLevelRepo.findByQuiz(quiz);
	}
}
