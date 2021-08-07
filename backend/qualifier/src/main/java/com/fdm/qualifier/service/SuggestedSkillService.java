package com.fdm.qualifier.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.SkillRepository;
import com.fdm.qualifier.repository.SuggestedSkillRepository;

/**
 * SuggestedSkill Service
 * @author William
 *
 */
@Service
public class SuggestedSkillService {
	protected static final String SKILL_ALREADY_SUGGESTED_MESSAGE = "Skill already suggested";

	private SuggestedSkillRepository suggestedSkillRepo;
	private SkillRepository skillRepo;

	private Log log = LogFactory.getLog(SuggestedSkillService.class);
	
	@Autowired
	public SuggestedSkillService(SuggestedSkillRepository suggestedSkillRepo, SkillRepository skillRepo) {
		super();
		this.suggestedSkillRepo = suggestedSkillRepo;
		this.skillRepo = skillRepo;
	}

	/**
	 * Save suggestedSkill to repo and return a message on result
	 * @param suggestedSkill
	 * @return
	 */
	public String save(SuggestedSkill suggestedSkill) {
		log.trace("save() called");

		String returnMessage = "";
		
		suggestedSkill.setName(suggestedSkill.getName().trim());
		
		if(suggestedSkillRepo.findByNameIgnoreCase(suggestedSkill.getName()).size() > 0 ) {
			log.debug("setting return message to: " );
			returnMessage = SKILL_ALREADY_SUGGESTED_MESSAGE;
		} else {
			log.debug("Saving suggested skill: " + suggestedSkill);
			suggestedSkillRepo.save(suggestedSkill);
		}
		
		return returnMessage;
	}

	/**
	 * Gets all suggestedSkills from repo
	 * @return
	 */
	public List<SuggestedSkill> getAll() {
		return suggestedSkillRepo.findAll();
	}
	
	/**
	 * Get suggestedSkills based on name from repo
	 * @param name
	 * @return
	 */
	public List<SuggestedSkill> findByName(String name) {
		return suggestedSkillRepo.findByNameIgnoreCase(name);
	}
	
	/**
	 * Delete suggestedSkill from repo
	 * @param skill
	 */
	public void declineSuggestedSkill(SuggestedSkill skill) {
		suggestedSkillRepo.delete(skill);
	}
	
	/**
	 * Create new skill in skillRepo based on suggested skill and remove it from suggestedSkillRepo
	 * @param skill
	 * @return
	 */
	public Skill acceptSuggestedSkill(SuggestedSkill skill) {
		Skill savedSkill = new Skill(skill.getName());
		suggestedSkillRepo.delete(skill);
		return skillRepo.save(savedSkill);
	}
}
