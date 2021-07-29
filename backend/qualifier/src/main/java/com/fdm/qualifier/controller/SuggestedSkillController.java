package com.fdm.qualifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.SuggestedSkillService;

@RestController
public class SuggestedSkillController {
	private SuggestedSkillService suggestedSkillService;

	@Autowired
	public SuggestedSkillController(SuggestedSkillService suggestedSkillService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
	}

}