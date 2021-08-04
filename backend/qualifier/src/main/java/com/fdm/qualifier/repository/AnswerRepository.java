package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	public List<Answer> findByQuestion(Question question);
}
