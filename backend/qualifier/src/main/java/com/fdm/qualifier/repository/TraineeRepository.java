package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.SkillLevel;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>{

	@Query("SELECT t.pinnedSkills FROM Trainee t WHERE uid = :id")
	List<SkillLevel> getPinnedSkillsByUid(@Param("id") int id);

	@Query("SELECT t.skills FROM Trainee t WHERE uid = :id")
	List<SkillLevel> getSkillsByUid(@Param("id") int id);

}
