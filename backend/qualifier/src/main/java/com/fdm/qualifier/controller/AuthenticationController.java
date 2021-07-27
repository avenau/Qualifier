package com.fdm.qualifier.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fdm.qualifier.util.Const.*;

import com.fdm.qualifier.httpRequest.PreVerifiedAccount;
import com.fdm.qualifier.httpResponse.LoginResponse;
import com.fdm.qualifier.security.AccountDetailsService;
import com.fdm.qualifier.service.UserService;
import com.fdm.qualifier.util.JwtUtil;

@RestController
@RequestMapping(value = AUTHENTICATION_BASE_URI)
public class AuthenticationController 
{
    private Log log = LogFactory.getLog(AuthenticationController.class);
    
    private AuthenticationManager authenticationManager;
    private AccountDetailsService accountDetailsService;
    private UserService service;
    private JwtUtil jwtUtil;

    /**
     * Constructor with Spring autowired stuff
     * @param am
     * @param ads
     * @param as
     * @param jwtUtil
     */
    @Autowired
    public AuthenticationController(AuthenticationManager am,
                                    AccountDetailsService ads,
                                    UserService us,
                                    JwtUtil jwtUtil)
    {
        this.authenticationManager = am;
        this.accountDetailsService = ads;
        this.service = us;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping(value = USER_LOGIN_URI)
    public ResponseEntity<?> loginRequest_POST(@RequestBody PreVerifiedAccount unVerifiedAccount) 
    {
        log.info("trying to login");
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(unVerifiedAccount.getUsername(),
                                                        unVerifiedAccount.getPassword()));
        } catch (BadCredentialsException e)
        {
            log.error("Bad login request received");
            return ResponseEntity.ok("bad credentials");
            //return ResponseEntity.ok(new LoginResponse(HttpStatus.UNAUTHORIZED, INVALID_CREDIENTIAL));
        }

        log.info("new login request received, correct credentials");
        final UserDetails accountDetails = accountDetailsService.loadUserByUsername(unVerifiedAccount.getUsername());
        final String jwt = jwtUtil.generateToken(accountDetails);

        return ResponseEntity.ok(new LoginResponse( HttpStatus.ACCEPTED, 
                                                    "this is the message",
                                                    jwt));
    }
}
