package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.SubmittedAnswer;
import com.fdm.qualifier.model.Question;

@Repository
public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer, Integer> {
	public List<SubmittedAnswer> findSubmittedAnswerByQuestion(Question question);
}
