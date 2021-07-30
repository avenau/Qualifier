package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	Client findByName(String name);
}
