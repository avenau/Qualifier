package com.fdm.qualifier.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main Controller
 * @author William
 *
 */
@RestController
@RequestMapping(value = "/")
public class MainController 
{
    protected static final String RETURNING_MAIN_PAGE_MESSAGE = "returning main page";
	private Log log = LogFactory.getLog(MainController.class);

    /**
     * Gets Home page
     * @return
     */
    @GetMapping(value = "/")
    public ResponseEntity<?> mainpage_GET()
    {
        log.info("accessing index route");
        return ResponseEntity.ok(RETURNING_MAIN_PAGE_MESSAGE);
    }
}
