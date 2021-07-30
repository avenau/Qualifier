package com.fdm.qualifier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.repository.PlacementRepository;

@Service
public class PlacementService {
	
	private PlacementRepository placementRepo;
	private SkillLevelService skillLevelService;
	private SkillService skillService;
	private ClientService clientService;

	@Autowired
	public PlacementService(PlacementRepository placementRepo, SkillLevelService skillLevelService, SkillService skillService, ClientService clientService) {
		super();
		this.placementRepo = placementRepo;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
		this.clientService = clientService;
	}
	
	public Placement findById(int id) {
		Optional<Placement> placement = placementRepo.findById(id);
		if(placement.isEmpty()) {
			return null;
		}
		return placement.get();
	}
	
	public List<Placement> findByLocation(String location){
		return placementRepo.findByLocation(location);
	}
	
	public List<Placement> findByName(String name){
		return placementRepo.findByName(name);
	}
	
	public List<Placement> findBySkillName(String skillName){
		Skill skill = skillService.findByName(skillName);
		List<SkillLevel> skillLevel = skillLevelService.findBySkill(skill);
		return findBySkillLevelIn(skillLevel);	
	}
	
	public List<Placement> findBySkillLevelIn(List<SkillLevel> skillLevelList) {
		return placementRepo.findBySkillsNeededIn(skillLevelList);
	}
	
	public List<Placement> findByClientName(String clientName){
		Client client = clientService.findByName(clientName);
		return placementRepo.findByClient(client);
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
