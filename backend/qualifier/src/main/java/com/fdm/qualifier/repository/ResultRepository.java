package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Results;

@Repository
public interface ResultRepository extends JpaRepository<Results, Integer>{

}
