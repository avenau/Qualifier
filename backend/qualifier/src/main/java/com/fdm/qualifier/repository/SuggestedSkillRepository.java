package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.SuggestedSkill;

@Repository
public interface SuggestedSkillRepository extends JpaRepository<SuggestedSkill, Integer>{

}
