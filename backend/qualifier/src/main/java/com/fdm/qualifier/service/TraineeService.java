package com.fdm.qualifier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.TraineeRepository;

@Service
public class TraineeService {
	private TraineeRepository traineeRepo;

	@Autowired
	public TraineeService(TraineeRepository traineeRepo) {
		super();
		this.traineeRepo = traineeRepo;
	}

	public Trainee save(Trainee trainee) {
		return traineeRepo.save(trainee);		
	}

	public List<SkillLevel> getPinnedSkills(Trainee trainee) {
		return traineeRepo.findAllPinnedSkills();
	}
	
	public List<SkillLevel> getSkills(Trainee trainee) {
		return traineeRepo.findAllSkills();
	}
}
