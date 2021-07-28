package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.TraineeRepository;

@Service
public class TraineeService {
	private TraineeRepository traineeRepo;

	@Autowired
	public TraineeService(TraineeRepository traineeRepo) {
		super();
		this.traineeRepo = traineeRepo;
	}

}
