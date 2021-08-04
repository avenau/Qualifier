package com.fdm.qualifier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	public List<Question> findQuestionByQuiz(Quiz quiz);
}
