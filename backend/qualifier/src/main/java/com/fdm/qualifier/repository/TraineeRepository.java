package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>{
	
	public Trainee getTraineeByuid(int id);
	
}
