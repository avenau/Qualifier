import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button, Container } from 'react-bootstrap';
import Questions from './Questions';
import Timer from './Timer';

function AttemptQuizPage() {

    const history = useHistory();
    const quizId = useLocation().pathname.split("/")[2];
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
  
  
    const [questions, setQuestion] = useState(questionTemplate);
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
    })

    useEffect (() => {
        axios
        .get('http://localhost:9999/getQuizQuestions', {   
            params: {
                id:quizId
            },
        })
        .then((response) =>{
            setQuestion(response.data);
        })
        .catch((error) => {
            history.push("/*");
        })
        console.log(questions);

    }, [questions.length]);


    return (
        <Container className="d-flex justify-content-center pt-5 w-100">
            <Timer quizId={quizId}/>
            <Questions questions={questions}/>

        </Container>
    )
}
export default AttemptQuizPage;