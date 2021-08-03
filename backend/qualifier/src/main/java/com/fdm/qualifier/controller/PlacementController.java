package com.fdm.qualifier.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Placement;
import com.fdm.qualifier.service.PlacementService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlacementController {
	
	private PlacementService placementService;
	
	private Log log = LogFactory.getLog(PlacementController.class);

	@Autowired
	public PlacementController(PlacementService placementService) {
		super();
		this.placementService = placementService;
	}

	@PostMapping("/searchPlacements")
	public List<Placement> searchPlacements(@RequestBody String placement) {
		List<Placement> resultList = new ArrayList<>();
		resultList.addAll(placementService.findByName(placement));
		resultList.addAll(placementService.findByClientName(placement));
		resultList.addAll(placementService.findBySkillName(placement));
		resultList.addAll(placementService.findByLocation(placement));
		System.out.println(resultList);
		return resultList;
		
	}
	
	
}
