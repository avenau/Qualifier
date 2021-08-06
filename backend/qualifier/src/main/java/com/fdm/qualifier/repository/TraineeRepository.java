package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Trainee;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.model.SkillLevel;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>{

	Trainee getTraineeByUid(int id);

	@Query("SELECT t.pinnedSkills FROM Trainee t WHERE uid = :id")
	List<SkillLevel> getPinnedSkillsByUid(@Param("id") int id);

	@Query("SELECT t.skills FROM Trainee t WHERE uid = :id")
	List<SkillLevel> getSkillsByUid(@Param("id") int id);

	@Query("SELECT t FROM Trainee t where firstName = :name OR lastName = :name")
	List<Trainee> findByFirstNameOrLastName(@Param("name")String name);
	
	List<Trainee> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Trainee> findTraineeBySkillsIn(List<SkillLevel> skills);
	
	List<Trainee> findTraineeByPinnedSkillsIn(List<SkillLevel> pinnedSkills);

//	Trainee findByUser(User user);
	
	@Query("SELECT t.results FROM Trainee t WHERE uid = :id")
	List<Result> getResultsByUid(@Param("id") int uid);

}
