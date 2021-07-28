package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.repository.PlacementRepository;

@Service
public class PlacementService {
	
	private PlacementRepository placementRepo;

	@Autowired
	public PlacementService(PlacementRepository placementRepo) {
		super();
		this.placementRepo = placementRepo;
	}
	
	public Optional<Placement> findById(int id) {
		return placementRepo.findById(id);
	}
	
	public List<Placement> findByLocation(String location){
		return placementRepo.findByLocation(location);
	}
	
	public List<Placement> findAll() {
		return placementRepo.findAll();
	}
	
	public void deleteById(int id) {
		placementRepo.deleteById(id);
	}
	
	public void deleteAll() {
		placementRepo.deleteAll();
	}
	
	public Placement save(Placement placement) {
		return placementRepo.save(placement);
	}
	
	

}
