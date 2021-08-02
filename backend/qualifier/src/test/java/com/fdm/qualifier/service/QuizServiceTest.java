package com.fdm.qualifier.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;
import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.repository.ResultRepository;

public class QuizServiceTest {
	private QuizService quizService;
	
	@Mock
	private Quiz mockQuiz;
	
	@Mock
	private Answer mockAnswer;
	
	@Mock
	private Question mockQuestion;
	
	@Mock
	private Result mockResult;
	
	@Mock
	private AnswerRepository mockAnswerRepo;
	
	@Mock
	private QuestionRepository mockQuestionRepo;
	
	@Mock
	private QuizRepository mockQuizRepo;
	
	@Mock
	private ResultRepository mockResultRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		quizService = new QuizService(mockResultRepo, mockQuizRepo, mockQuestionRepo, mockAnswerRepo);
	}
	
	@Test
	public void test_save_quiz() {
		// ARRANGE
		// ACT
		quizService.save(mockQuiz);
		// ASSERT
		verify(mockQuizRepo, times(1)).save(mockQuiz);
		verify(mockQuestionRepo, times(1)).save(mockQuestion);
		verify(mockAnswerRepo, times(1)).save(mockAnswer);
	}
}
