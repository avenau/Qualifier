package com.fdm.qualifier.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.service.ClientService;

import com.fdm.qualifier.service.PlacementService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementController {
	
	private Log log = LogFactory.getLog(PlacementController.class);
	private PlacementService placementService;
	private ClientService clientService;
	
	@Autowired
	public PlacementController(PlacementService placementService, ClientService clientService) {
		super();
		this.placementService = placementService;
		this.clientService = clientService;
	}
	
	@PostMapping("/savePlacement")
	public void save(@RequestBody Placement placement) {
		log.info("Saving placement: " + placement);
		placementService.save(placement);
	}
	

	@PostMapping("/searchPlacements")
	public List<Placement> searchPlacements(@RequestBody String searchTerm) {
		List<Placement> resultList = new ArrayList<>();
		resultList.addAll(placementService.findByName(searchTerm));
		resultList.addAll(placementService.findByClientName(searchTerm));
		resultList.addAll(placementService.findBySkillName(searchTerm));
		resultList.addAll(placementService.findByLocation(searchTerm));
		return resultList;
	}
	
	@GetMapping("/getAllPlacements")
	public List<Placement> getAllPlacements(){
		return placementService.findAll();
	}
	
	
}
