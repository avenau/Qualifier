package com.fdm.qualifier.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Client;
import com.fdm.qualifier.service.ClientService;

class ClientControllerTest {
	ClientController clientController;
	
	@Mock
	private ClientService mockClientService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		clientController = new ClientController(mockClientService);
	}
	
	@Test
	void getAllClients_returnList_onCall() {
		List<Client> expected = new ArrayList<Client>();
		when(mockClientService.getAllClients()).thenReturn(expected);
		
		List<Client> result = clientController.getAllClients();
		
		assertEquals(expected, result);
	}

}
