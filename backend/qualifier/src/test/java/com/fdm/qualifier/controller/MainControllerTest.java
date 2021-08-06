package com.fdm.qualifier.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

class MainControllerTest {
	MainController mainController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mainController = new MainController();
	}
	
}
