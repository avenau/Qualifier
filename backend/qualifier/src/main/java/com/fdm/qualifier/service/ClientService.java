package com.fdm.qualifier.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.repository.ClientRepository;

/**
 * Client Service
 * @author William
 *
 */
@Service
public class ClientService {
	private ClientRepository clientRepo;

	@Autowired
	public ClientService(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
	
	/**
	 * Saves a client to repo
	 * @param client
	 * @return
	 */
	public Client save(Client client) {
		return clientRepo.save(client);
	}
	
	/**
	 * Gets a client from repo by name
	 * @param name
	 * @return
	 */
	public Client findByName(String name){
		return clientRepo.findByName(name);
	}
	
	/**
	 * Gets all clients from repo
	 * @return
	 */
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}
}
