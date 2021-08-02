package com.fdm.qualifier.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdm.qualifier.model.User;
import com.fdm.qualifier.repository.UserRepository;
import com.fdm.qualifier.security.AccountDetails;

/**
 * Same as AccountDetails, its for Spring Security's need!!!!
 */
@Service
public class AccountDetailsService implements UserDetailsService
{
    private Log log = LogFactory.getLog(AccountDetailsService.class);

    private UserRepository userRepository;    
  
    @Autowired
    public AccountDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        Optional<User> account =  userRepository.findByUsername(username);
        log.info("AccountDetailsService ::: loading user for validation purposes");        
        account.orElseThrow(() -> new UsernameNotFoundException("cannot be found, User::" + username));
        return account.map(AccountDetails::new).get();

    }
}
