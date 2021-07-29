package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.SuggestedSkillRepository;

@Service
public class SuggestedSkillService {
	private SuggestedSkillRepository suggestedSkillRepo;

	@Autowired
	public SuggestedSkillService(SuggestedSkillRepository suggestedSkillRepo) {
		super();
		this.suggestedSkillRepo = suggestedSkillRepo;
	}

}
