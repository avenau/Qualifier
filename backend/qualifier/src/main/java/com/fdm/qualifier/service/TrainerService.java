package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Trainer;
import com.fdm.qualifier.repository.TrainerRepository;

/**
 * Trainer Service
 * @author William
 *
 */
@Service
public class TrainerService {
	private TrainerRepository trainerRepo;

	@Autowired
	public TrainerService(TrainerRepository trainerRepo) {
		super();
		this.trainerRepo = trainerRepo;
	}
	
	/**
	 * Save trainer to repo
	 * @param trainer
	 * @return
	 */
	public Trainer saveTrainer(Trainer trainer) {
		return trainerRepo.save(trainer);
	}

}
