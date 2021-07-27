package com.fdm.qualifier.setup;

import com.fdm.qualifier.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class createAdmin implements ApplicationRunner
{
    private Log log = LogFactory.getLog(createAdmin.class);
    private UserService userService;

    public createAdmin(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception 
    {
        log.info("initialise one admin User");
        userService.initAdmin("admin", "admin");
    }
}
