package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

}
