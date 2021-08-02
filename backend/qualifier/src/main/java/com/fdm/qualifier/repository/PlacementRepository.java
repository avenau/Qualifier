package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.SkillLevel;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer>{
	public List<Placement> findByLocation(String location);
	public List<Placement> findByName(String name);
	public List<Placement> findBySkillsNeededIn(List<SkillLevel> skillsNeeded);
	public List<Placement> findByClient(Client client);
}
