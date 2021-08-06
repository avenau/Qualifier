package com.fdm.qualifier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.dto.PlacementRecieverDTO;
import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.model.Skill;
import com.fdm.qualifier.model.SkillLevel;
import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.repository.ClientRepository;
import com.fdm.qualifier.repository.PlacementRepository;
import com.fdm.qualifier.repository.SkillLevelRepository;

@Service
public class PlacementService {
	private Log log = LogFactory.getLog(PlacementService.class);
	
	private PlacementRepository placementRepo;
	private ClientRepository clientRepo;
	private SkillLevelService skillLevelService;
	private SkillService skillService;
	private ClientService clientService;
	private TraineeService traineeService;
	private SkillLevelRepository skillLevelRepo;

	@Autowired
	public PlacementService(PlacementRepository placementRepo, SkillLevelService skillLevelService,
			SkillService skillService, ClientService clientService, TraineeService traineeService,
			ClientRepository clientRepo, SkillLevelRepository skillLevelRepo) {
		super();
		this.placementRepo = placementRepo;
		this.skillLevelService = skillLevelService;
		this.skillService = skillService;
		this.clientService = clientService;
		this.traineeService = traineeService;
		this.clientRepo = clientRepo;
		this.skillLevelRepo = skillLevelRepo;
	}

	public Placement findById(int id) {
		Optional<Placement> placement = placementRepo.findById(id);
		if (placement == null) {
			return null;
		}
		return placement.get();
	}

	public List<Placement> findByLocation(String location) {
		return placementRepo.findByLocation(location);
	}

	public List<Placement> findByName(String name) {
		return placementRepo.findByName(name);
	}

	public List<Placement> findBySkillName(String skillName) {
		Skill skill = skillService.findByName(skillName);
		List<SkillLevel> skillLevel = skillLevelService.findBySkill(skill);
		return findBySkillLevelIn(skillLevel);
	}

	public List<Placement> findBySkillLevelIn(List<SkillLevel> skillLevelList) {
		return placementRepo.findBySkillsNeededIn(skillLevelList);
	}

	public List<Placement> findByClientName(String clientName) {
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

	/**
	 * Update placement to contain the approved Trainee
	 * 
	 * @param placement
	 * @param trainee
	 * @return
	 */
	public Placement placeApprovedTrainee(Placement placement, Trainee trainee) {
		// update placement
		placement.setTrainee(trainee);
		// update trainee
//        List<Placement> traineePlacements= trainee.getPlacements();
//        traineePlacements.add(placement);
//        trainee.setPlacements(traineePlacements);
//        traineeService.save(trainee);
		return placementRepo.save(placement);
	}

	public Placement saveDTO(PlacementRecieverDTO placementDTO) {
		log.debug("Saving placementDTO");
		Client client = clientRepo.getById(placementDTO.getClient().getClientId());
//		log.debug("got client");
		
		Placement placement = new Placement(placementDTO.getName(), placementDTO.getStartDate(),
				placementDTO.getCompletionDate(), placementDTO.getDescription(), placementDTO.getLocation(), client,
				new ArrayList<SkillLevel>());
		
		List<SkillLevel> skillsNeeded = new ArrayList<>();
		
		for (Integer skillLevelId : placementDTO.getSkillsNeeded()) {
			Optional<SkillLevel> skillLevel = skillLevelRepo.findById(skillLevelId);
			if(skillLevel.isPresent())
				skillsNeeded.add(skillLevel.get());
		}
		
		placement.setSkillsNeeded(skillsNeeded);
		
		placement = save(placement);
		
		log.debug("Saved placement: " + placement);
		log.debug("Saved placement skillsNeeded: " + placement.getSkillsNeeded());
		return placement;
	}
}
