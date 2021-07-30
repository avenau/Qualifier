package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.SkillLevel;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>{

	List<SkillLevel> getPinnedSkillsByUid(int id);

	List<SkillLevel> getSkillsByUid(int id);

}
