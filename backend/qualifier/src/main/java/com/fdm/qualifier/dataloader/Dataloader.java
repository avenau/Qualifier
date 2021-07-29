package com.fdm.qualifier.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdm.qualifier.model.SuggestedSkill;
import com.fdm.qualifier.service.SuggestedSkillService;

@Component
public class Dataloader implements ApplicationRunner {
	private SuggestedSkillService suggestedSkillService;

	public Dataloader(SuggestedSkillService suggestedSkillService) {
		super();
		this.suggestedSkillService = suggestedSkillService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		SuggestedSkill suggestedSkill = new SuggestedSkill("java");
		
		suggestedSkillService.save(suggestedSkill);
	}

}
