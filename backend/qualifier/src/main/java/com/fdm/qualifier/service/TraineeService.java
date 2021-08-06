package com.fdm.qualifier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.dto.TraineeSkillLevelDTO;
import com.fdm.qualifier.model.Result;

import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.SkillLevelRepository;
import com.fdm.qualifier.repository.TraineeRepository;


@Service
public class TraineeService {
	protected static final String SKILL_DOESN_T_EXIST_IN_DATABASE_MESSAGE = "Skill doesn't exist in database";
	protected static final String TRAINEE_DOESN_T_EXIST_IN_DATABASE_MESSAGE = "Trainee doesn't exist in database";
	protected static final int MAX_PINNED_SKILLS = 3;
	protected static final String NO_SKILLS_MESSAGE = "No skills";
	protected static final String TOO_MANY_PINNED_SKILLS_MESSAGE = "Too many pinned skills, maximum of 3 pinned skills";
	protected static final String NO_PINNED_SKILLS_MESSAGE = "No pinned skills";
	protected static final String NO_SKILL_IN_PINNED_SKILLS_PATTERN = "You do not have pinned skill %s: %s cannot unpin";
	protected static final String NO_SKILL_IN_SKILLS_PATTERN = "You do not have skill %s: %s cannot pin";

	private TraineeRepository traineeRepo;
	private SkillLevelRepository skillLevelRepo;
	private SkillLevelService skillLevelService;
	private SkillService skillService;

	private Log log = LogFactory.getLog(TraineeService.class);

	@Autowired
	public TraineeService(TraineeRepository traineeRepo, SkillLevelRepository skillLevelRepo,
			SkillLevelService skillLevelService, SkillService skillService) {
		super();
		this.traineeRepo = traineeRepo;
		this.skillLevelRepo = skillLevelRepo;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
	}

	public Trainee getTraineeByID(int id) {

		return traineeRepo.getTraineeByUid(id);
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
	 * @param traineeId
	 * @return
	 */
	public List<SkillLevel> getPinnedSkills(int traineeId) {
		log.trace("getPinnedSkills() called");
		log.debug("TraineeId: " + traineeId);
		return traineeRepo.getPinnedSkillsByUid(traineeId);
	}

	/**
	 * Gets a trainees skills, excluding pinned skills
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<SkillLevel> getSkills(int traineeId) {
		log.trace("getSkills() called");
		log.debug("TraineeId: " + traineeId);
		return traineeRepo.getSkillsByUid(traineeId);
	}

	/**
	 * Gets a trainees skills and pinned skills in a single list
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<SkillLevel> getAllSkills(int traineeId) {
		log.trace("getAllSkills() called");
		List<SkillLevel> allSkills = traineeRepo.getSkillsByUid(traineeId);
		allSkills.addAll(traineeRepo.getPinnedSkillsByUid(traineeId));
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
	public String pinSkill(int traineeId, int skillToPinId) {
		log.trace("addPinnedSkill() called");
		String returnMessage = "";

		Optional<Trainee> possibleTrainee = traineeRepo.findById(traineeId);
		Optional<SkillLevel> possibleSkill = skillLevelRepo.findById(skillToPinId);

		if (!possibleTrainee.isPresent()) {
			log.debug("trainee doesn't exist");
			returnMessage = TRAINEE_DOESN_T_EXIST_IN_DATABASE_MESSAGE;
		} else if (!possibleSkill.isPresent()) {
			log.debug("skill doesn't exist");
			returnMessage = SKILL_DOESN_T_EXIST_IN_DATABASE_MESSAGE;
		} else {
			Trainee trainee = possibleTrainee.get();
			SkillLevel skillToPin = possibleSkill.get();

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
					traineeRepo.save(trainee);
					log.debug("After updating pinned skills and skills: " + trainee);
				}
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
	public String unpinSkill(int traineeId, int skillToUnpinId) {
		String returnMessage = "";
		Optional<Trainee> possibleTrainee = traineeRepo.findById(traineeId);
		Optional<SkillLevel> possibleSkillLevel = skillLevelRepo.findById(skillToUnpinId);

		if (!possibleTrainee.isPresent()) {
			log.debug("trainee doesn't exist");
			returnMessage = TRAINEE_DOESN_T_EXIST_IN_DATABASE_MESSAGE;
		} else if (!possibleSkillLevel.isPresent()) {
			log.debug("skill doesn't exist");
			returnMessage = SKILL_DOESN_T_EXIST_IN_DATABASE_MESSAGE;
		} else {
			Trainee trainee = possibleTrainee.get();
			SkillLevel skillToUnpin = possibleSkillLevel.get();

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
					traineeRepo.save(trainee);
					log.debug("After updating pinned skills and skills: " + trainee);
					log.debug(trainee.getPinnedSkills());
					log.debug(trainee.getSkills());
				}
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

	/**
	 * Gets all trainees
	 * 
	 * @return
	 */
	public List<Trainee> getAllTrainees() {
		log.trace("getAllTrainees() called");
		return traineeRepo.findAll();
	}

	/**
	 * Adds the specified Skill Level to the specified trainee. Returns true if
	 * skill was added
	 * 
	 * @param skillLevel
	 * @param traineeId
	 * @return skillAdded
	 */
	public boolean addSkillToTrainee(SkillLevel skillLevel, int traineeId) {
		boolean addedSkill = true;
		Trainee foundTrainee = getTraineeByID(traineeId);

		if (foundTrainee != null && !foundTrainee.getSkills().contains(skillLevel)
				&& !foundTrainee.getPinnedSkills().contains(skillLevel)) {

			Skill skill = skillLevel.getSkill();
			boolean hasSkillAtDifferentLevel = false;

			for (SkillLevel sl : foundTrainee.getSkills()) {
				if (sl.getSkill().equals(skill))
					hasSkillAtDifferentLevel = true;
			}

			for (SkillLevel sl : foundTrainee.getPinnedSkills()) {
				if (sl.getSkill().equals(skill))
					hasSkillAtDifferentLevel = true;
			}

			if (hasSkillAtDifferentLevel) {
				addedSkill = false;
			} else {
				foundTrainee.addSkill(skillLevel);
				addedSkill = true;
			}
		} else {
			addedSkill = false;
		}

		return addedSkill;
	}

	
	/** Removes the specified skill from the specified trainee 
	 * 
	 * @param skill
	 * @param traineeId
	 */
	public void removeSkillFromTrainee(Skill skill, int traineeId) {
		Trainee foundTrainee = getTraineeByID(traineeId);
		foundTrainee.removeSkill(skill);
	}

	/**
	 * Gets trainees that match the string in their first or last name
	 * 
	 * @param name
	 * @return
	 */
	public List<Trainee> findTraineeByName(String name) {
		return traineeRepo.findByFirstNameOrLastName(name);
	}

	/**

	 * Finds Trainees by the name of a skill by passing a skill level into findTraineeBySkills
	 * @param skillName
	 * @return
	 */
	public List<Trainee> findBySkillName(String skillName) {
		Skill skill = skillService.findByName(skillName);
		List<SkillLevel> skillLevel = skillLevelService.findBySkill(skill);
		return findTraineeBySkills(skillLevel);
	}

	
	/**
	 * Returns trainees from a skill level
	 * @param skill
	 * @return
	 */
	public List<Trainee> findTraineeBySkills(List<SkillLevel> skill) {
		List<Trainee> results = traineeRepo.findTraineeBySkillsIn(skill);
		results.addAll(traineeRepo.findTraineeByPinnedSkillsIn(skill));
		return results;
	}


	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Result> getAllResults(int userId) {
		return traineeRepo.getResultsByUid(userId);
	}
	
	
	/**
	 * Finds trainees by their first and last name
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<Trainee> findByFirstAndLastName(String firstName, String lastName){
		return traineeRepo.findByFirstNameAndLastName(firstName, lastName);
	}

	
	public List<TraineeSkillLevelDTO> getPinnedSkillsAsDTO(int userId) {
		List<SkillLevel> traineeSkills = getPinnedSkills(userId);
		List<TraineeSkillLevelDTO> traineeSkillsAsDTO = new ArrayList<>();

		for (SkillLevel traineeSkill : traineeSkills) {
			traineeSkillsAsDTO.add(new TraineeSkillLevelDTO(traineeSkill));
		}

		return traineeSkillsAsDTO;
	}

	public List<TraineeSkillLevelDTO> getSkillsAsDTO(int userId) {
		List<SkillLevel> traineeSkills = getSkills(userId);
		List<TraineeSkillLevelDTO> traineeSkillsAsDTO = new ArrayList<>();

		for (SkillLevel traineeSkill : traineeSkills) {
			traineeSkillsAsDTO.add(new TraineeSkillLevelDTO(traineeSkill));
		}

		return traineeSkillsAsDTO;
	}
}
