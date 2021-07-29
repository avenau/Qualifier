package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.TrainerRepository;

@Service
public class TrainerService {
	private TrainerRepository trainerRepo;

	@Autowired
	public TrainerService(TrainerRepository trainerRepo) {
		super();
		this.trainerRepo = trainerRepo;
	}

}
