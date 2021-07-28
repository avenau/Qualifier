package com.fdm.qualifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.repository.ClientRepository;

@Service
public class ClientService {
	private ClientRepository clientRepo;

	@Autowired
	public ClientService(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
}