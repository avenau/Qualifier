import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button, Container, Row, Col } from 'react-bootstrap';
import Questions from './Questions';
import Timer from './Timer';

function AttemptQuizPage() {

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

    const submitQuestion = (() => {
        axios
        .post('http://localhost:9999/saveSuggestedSkill', { suggestedSkillId: 0, name: "" })
        .then((response) => {

        })
        .catch(()=>{

        })
        .finally(() => {
            history.push('/finishquiz');
        })
    })

    useEffect (() => {
        axios
        .get('http://localhost:9999/getQuizDetails', {   
            params: {
                quizId:quizId
            },
        })
        .then((response) =>{
            console.log(response.data);
            setQuiz(response.data);
            setLoading(false);
        })
        .catch((error) => {
            console.log(error.message);
            history.push("/*");
        })
        console.log(quiz.questions);
        console.log("DURATION: " + quiz.duration);

    }, [quiz.questions.length, quiz.duration]);

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
                    <Questions quizId={quizId}/>
                </Col>
            </Row>
        </Container>
    )
}
export default AttemptQuizPage;