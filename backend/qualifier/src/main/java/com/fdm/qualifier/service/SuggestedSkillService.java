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
	private SuggestedSkillRepository suggestedSkillRepo;

	private Log log = LogFactory.getLog(SuggestedSkillService.class);
	
	@Autowired
	public SuggestedSkillService(SuggestedSkillRepository suggestedSkillRepo) {
		super();
		this.suggestedSkillRepo = suggestedSkillRepo;
	}

	public void save(SuggestedSkill suggestedSkill) {
		log.trace("save() called");
		log.info("Saving suggested skill: " + suggestedSkill);
		suggestedSkillRepo.save(suggestedSkill);
	}

	public List<SuggestedSkill> getAll() {
		return suggestedSkillRepo.findAll();
	}
}
