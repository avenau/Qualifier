package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.SkillRepository;

@Service
public class SkillService {
	private SkillRepository skillRepo;

	@Autowired
	public SkillService(SkillRepository skillRepo) {
		super();
		this.skillRepo = skillRepo;
	}
	
	
}
