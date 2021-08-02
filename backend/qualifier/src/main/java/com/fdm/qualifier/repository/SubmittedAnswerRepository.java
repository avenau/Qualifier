package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.SubmittedAnswer;

@Repository
public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer, Integer> {

}
