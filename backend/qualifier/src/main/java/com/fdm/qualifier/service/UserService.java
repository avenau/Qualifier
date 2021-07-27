package com.fdm.qualifier.service;

import java.util.Optional;

import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
    private Log log = LogFactory.getLog(UserService.class);
    
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // TODO



    /**
     * Only called upon project initalisation,
     * to create Admin type Account and store in the database
     * iff that Admin does not exist.
     * 
     * @param username
     * @param password
     */
    public void initAdmin(String username, String password)
    {
        Optional<User> account = userRepository.findByUsername(username);
        if (account.isEmpty())
        {
            log.info(String.format("Creating new Admin account, username: %s", username));
            User admin = new User(username, passwordEncoder.encode(password));
            //User admin = new Admin(username, passwordEncoder.encode(password), "ROLE_ADMIN");
            userRepository.save(admin);
        }
    }
}
