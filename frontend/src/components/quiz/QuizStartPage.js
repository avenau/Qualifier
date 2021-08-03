import { useState, useEffect } from "react";
import { Redirect, useHistory, useLocation } from "react-router-dom";
import { Button, Container, Card } from 'react-bootstrap';

// #TODO ADD SESSION LOGIN TO CHECK PAGE
function QuizStartPage() {
    const axios = require('axios');
    let history = useHistory();
    const quizId = useLocation().pathname.split("/")[2];
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
        .get('http://localhost:9999/quiz/get/' + quizId)
        .then(response => {
            setQuiz(response.data)
        })
        .catch((error) => {
            history.push("/*");
        }) 
    }, [quiz.quizId]);

    const startQuiz = () => {
        history.push("/quiz/" + quizId);

    }

    return (
        <Container className="text-center d-flex justify-content-center pt-5">
            <Card style={{ width: '15rem', paddingTop: '15px' }}>
            <Card.Body>
                <Card.Title>{quiz.name}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">No. of Questions: {quiz.questionCount}</Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted">Marks Required: {quiz.passingMark}%</Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted">Time Limit: {quiz.duration}</Card.Subtitle>
                <Button variant="primary" onClick={startQuiz}>Start Quiz</Button>
            </Card.Body>
            </Card>
            
        </Container>
    )

}
export default QuizStartPage;