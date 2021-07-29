package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer>{
	public List<Placement> findByLocation(String location);
}
