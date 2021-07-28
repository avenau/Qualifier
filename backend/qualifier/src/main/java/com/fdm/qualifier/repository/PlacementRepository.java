package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.qualifier.model.Placement;

public interface PlacementRepository extends JpaRepository<Placement, Integer>{
	
}
