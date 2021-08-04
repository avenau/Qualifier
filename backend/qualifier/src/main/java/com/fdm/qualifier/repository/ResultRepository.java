package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer>{
	public List<Result> findByQuiz(Quiz quiz);
}
