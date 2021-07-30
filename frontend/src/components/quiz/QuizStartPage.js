import { useState, useEffect } from "react";
import { Redirect, useHistory } from "react-router-dom";
import { Button, Container } from 'react-bootstrap';

function QuizStartPage() {
    const axios = require('axios');
    let history = useHistory();
    const quizTemplate = {
                            quizId: 0,
                            name: "",
                            description: "",
                            duration: 0.0,
                            questionCount: 0,
                            passingMark: 101.0,
                            questions: []
                        }
    const [quiz, setQuiz] = useState(quizTemplate);

    useEffect(() => {
        axios
        .get('http://localhost:9999/getQuizDetails', {   
            params: {
                id:1
            },
        }).then(response => {
            setQuiz(response.data)
        })
        .catch(() => {});  
    }, [quiz.questions]);

    const startQuiz = () => {
        history.push("/quiz/" + quiz.quizId);

    }

    return (
        <Container>
            <p>{quiz.name}</p>
            <p>Time Limit: {quiz.duration}</p>
            <p>Number of Questions: {quiz.questionCount}</p>
            <p>Marks Required: {quiz.passingMark}%</p>
            <Button variant="primary" onClick={startQuiz}>Start Quiz</Button>
            
        </Container>
    )

}
export default QuizStartPage;