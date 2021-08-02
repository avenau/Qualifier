package com.fdm.qualifier.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.TraineeRepository;

@Service
public class TraineeService {
	protected static final int MAX_PINNED_SKILLS = 3;
	protected static final String NO_SKILLS_MESSAGE = "No skills";
	protected static final String TOO_MANY_PINNED_SKILLS_MESSAGE = "Too many pinned skills, maximum of 3 pinned skills";
	protected static final String NO_PINNED_SKILLS_MESSAGE = "No pinned skills";
	protected static final String NO_SKILL_IN_PINNED_SKILLS_PATTERN = "You do not have pinned skill %s: %s cannot unpin";
	protected static final String NO_SKILL_IN_SKILLS_PATTERN = "You do not have skill %s: %s cannot pin";

	private TraineeRepository traineeRepo;

	private Log log = LogFactory.getLog(TraineeService.class);

	@Autowired
	public TraineeService(TraineeRepository traineeRepo) {
		super();
		this.traineeRepo = traineeRepo;
	}
	/**
	 * Saves trainee to the repository
	 * 
	 * @param trainee
	 * @return
	 */
	public Trainee save(Trainee trainee) {
		log.trace("save() called");
		return traineeRepo.save(trainee);
	}

	/**
	 * Gets a trainees pinned skills
	 * 
	 * @param trainee
	 * @return
	 */
	public List<SkillLevel> getPinnedSkills(Trainee trainee) {
		log.trace("getPinnedSkills() called");
		return traineeRepo.getPinnedSkillsByUid(trainee.getUserId());
	}

	/**
	 * Gets a trainees skills, excluding pinned skills
	 * 
	 * @param trainee
	 * @return
	 */
	public List<SkillLevel> getSkills(Trainee trainee) {
		log.trace("getSkills() called");
		return traineeRepo.getSkillsByUid(trainee.getUserId());
	}

	/**
	 * Gets a trainees skills and pinned skills in a single list
	 * 
	 * @param trainee
	 * @return
	 */
	public List<SkillLevel> getAllSkills(Trainee trainee) {
		log.trace("getAllSkills() called");
		List<SkillLevel> allSkills = traineeRepo.getSkillsByUid(trainee.getUserId());
		allSkills.addAll(traineeRepo.getPinnedSkillsByUid(trainee.getUserId()));
		return allSkills;
	}

	/**
	 * Adds skills to pinned skills if pinned skills list is less than 3 and removes
	 * skill from trainees skills list.
	 * 
	 * @param trainee
	 * @param skillToPin
	 * @return
	 */
	public String pinSkill(Trainee trainee, SkillLevel skillToPin) {
		log.trace("addPinnedSkill() called");
		String returnMessage = "";
		trainee = traineeRepo.save(trainee);

		log.debug("Trainee: " + trainee);

		List<SkillLevel> skills = trainee.getSkills();
		List<SkillLevel> pinnedSkills = trainee.getPinnedSkills();
		log.debug("Trainee skills: " + skills);
		log.debug("Trainee pinnedSkills: " + pinnedSkills);

		if (skills == null || skills.size() < 1) {
			log.debug("skills was null or size 0");
			returnMessage = NO_SKILLS_MESSAGE;
		} else {
			if (pinnedSkills.size() > (MAX_PINNED_SKILLS - 1)) {
				log.debug("pinned skills full");
				returnMessage = TOO_MANY_PINNED_SKILLS_MESSAGE;
			} else if (!skills.contains(skillToPin)) {
				log.debug("" + skillToPin + "not found in skills");
				returnMessage = createMessageWithSkillNameAndLevel(NO_SKILL_IN_SKILLS_PATTERN, skillToPin);
			} else {
				skills.remove(skillToPin);
				pinnedSkills.add(skillToPin);
				trainee.setSkills(skills);
				trainee.setPinnedSkills(pinnedSkills);
				log.debug("After updating pinned skills and skills: " + trainee);
			}
		}
		return returnMessage;
	}

	/**
	 * Removes skillToUnpin from pinnedSkills list in trainee to skills list
	 * 
	 * @param trainee
	 * @param skillToUnpin
	 * @return
	 */
	public String unpinSkill(Trainee trainee, SkillLevel skillToUnpin) {
		String returnMessage = "";
		trainee = traineeRepo.save(trainee);

		List<SkillLevel> skills = trainee.getSkills();
		List<SkillLevel> pinnedSkills = trainee.getPinnedSkills();

		if (pinnedSkills == null || pinnedSkills.size() < 1) {
			log.debug("pinnedSkills was null or size 0");
			returnMessage = NO_PINNED_SKILLS_MESSAGE;
		} else {
			if (!pinnedSkills.contains(skillToUnpin)) {
				log.debug("" + skillToUnpin + "not found in pinnedSkills");
				returnMessage = createMessageWithSkillNameAndLevel(NO_SKILL_IN_PINNED_SKILLS_PATTERN, skillToUnpin);
			} else {
				pinnedSkills.remove(skillToUnpin);
				skills.add(skillToUnpin);
				trainee.setSkills(skills);
				trainee.setPinnedSkills(pinnedSkills);
				log.debug("After updating pinned skills and skills: " + trainee);
			}
		}
		return returnMessage;
	}

	/**
	 * Creates a String message from pattern, and skill using name and level of
	 * skill
	 * 
	 * @param pattern
	 * @param skill
	 * @return
	 */
	protected String createMessageWithSkillNameAndLevel(String pattern, SkillLevel skill) {
		return String.format(pattern, skill.getSkill().getName(), skill.getLevel());
	}
}
