package com.fdm.qualifier.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.repository.SuggestedSkillRepository;

@Service
public class SuggestedSkillService {
	protected static final String SKILL_ALREADY_SUGGESTED_MESSAGE = "Skill already suggested";

	private SuggestedSkillRepository suggestedSkillRepo;

	private Log log = LogFactory.getLog(SuggestedSkillService.class);
	
	@Autowired
	public SuggestedSkillService(SuggestedSkillRepository suggestedSkillRepo) {
		super();
		this.suggestedSkillRepo = suggestedSkillRepo;
	}

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

	public List<SuggestedSkill> getAll() {
		return suggestedSkillRepo.findAll();
	}
}
