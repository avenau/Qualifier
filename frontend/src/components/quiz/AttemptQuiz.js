import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button, Container, Row, Col } from 'react-bootstrap';
import Questions from './Questions';
import Timer from './Timer';

function AttemptQuiz() {

    const history = useHistory();
    const quizId = useLocation().pathname.split("/")[2];
    const [isLoading, setLoading] = useState(true);
    const axios = require("axios");
    const questionTemplate = 
                     [{           
                        questionId: 0,
                        content: "NOT LOADED",
                        type: "NOT LOADED",
                        points: 0,
                        image: "NOT LOADED",
                        answers:  [{
                            answerId: 0,
                            content: "NOT LOADED",
                            correct: "NOT LOADED"
                        }]
                    }]
    const quizTemplate = {
        quizId: 0,
        name: "",
        description: "",
        duration: 0.0,
        questionCount: 0,
        passingMark: 101.0,
        questions: questionTemplate
    }
    const [quiz, setQuiz] = useState(quizTemplate);
  
  
    // const [questions, setQuestion] = useState(questionTemplate);
    const [mark, setMark] = useState(0);
    //const [answers, setAnswers] = useState([]);

    // const addAnswer = ((value) => {
    //     setAnswers(answers.concat(value))
    // })

    useEffect (() => {
        console.log("ATTEMPT QUIZ ID " + quizId)
        axios
        .get('http://localhost:9999/quiz/get/' + quizId)
        .then((response) =>{
            console.log(response.data);
            console.log("ATTEMPT QUIZ ID ")
            setQuiz(response.data);
            setLoading(false);
        })
        .catch((error) => {
            console.log(error.message);
            history.push("/*");
        })
        console.log(quiz.questions);
        console.log("DURATION: " + quiz.duration);

    }, [quiz.duration]);

    if (isLoading) {
        return <div className="App">Loading...</div>;
    }

    return (
        <Container>
            <Row>
                <Col className="d-flex justify-content-end sticky-top">
                    <Timer duration={quiz.duration}/>
                </Col>
            </Row>
            <Row>
                <Col className="d-flex justify-content-center">
                    <Questions quizId={quizId} quiz={quiz}/>
                </Col>
            </Row>
        </Container>
    )
}
export default AttemptQuiz;