package com.fdm.qualifier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.service.ClientService;
import com.fdm.qualifier.model.Client;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@GetMapping("/getAllClients")
	public List<Client> getAllClients(){
		return clientService.getAllClients();
	}

}
