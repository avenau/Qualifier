package com.fdm.qualifier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fdm.qualifier.service.AccountDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private AccountDetailsService accountDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(AccountDetailsService ads, JwtRequestFilter jrf)
    {
        this.accountDetailsService = ads;
        this.jwtRequestFilter = jrf;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .cors()             // Cross Origin Request
            .and()              
            .csrf().disable()   // Cross-site request forgery

            .authorizeRequests()
            .antMatchers("/", "/getUser", "/saveSuggestedSkill", "/getAllSuggestedSkills", "/getQuizDetails", "/searchPlacements", "/getAllPlacements", 
            		"/getQuizQuestions", "/submitQuiz", "/getAllQuizzes", "/getPinnedSkills", "/savePlacement", "/getStartQuizDetails",
            		"/getSkills", "/addUnverifiedSkill", "/addSkill", "/getAllSkills", "/getAllTrainees", "/pinSkill", "/unpinSkill", "/getAllClients","/getResult", "/h2-console/**", "/auth/**", "/quiz/get/*", "quiz/create/*", "/quiz/submit", "/removeSuggestedSkill").permitAll() //!!CHANGE THIS WHEN LOGIN IS FUNCTIONAL!!


            // put .antMatcher(route).permitAll() for public access
            //.antMatchers("/auth/**").permitAll()
            
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(accountDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
//        return new BCryptPasswordEncoder(10);
    	return NoOpPasswordEncoder.getInstance();
    }
}
