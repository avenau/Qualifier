import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button } from 'react-bootstrap';
import Questions from './Questions';

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
        <div>
            <p>HELLO</p>
            <p>{questions.length}</p>
            <Form>
                {['checkbox', 'radio'].map((type) => (
                    <div key={`inline-${type}`} className="mb-3">
                    <Form.Check
                        inline
                        label="1"
                        name="group1"
                        type={type}
                        id={`inline-${type}-1`}
                    />
                    <Form.Check
                        inline
                        label="2"
                        name="group1"
                        type={type}
                        id={`inline-${type}-2`}
                    />
                    <Form.Check
                        inline
                        disabled
                        label="3 (disabled)"
                        type={type}
                        id={`inline-${type}-3`}
                    />
                    </div>
                ))}
            </Form>
            <Form>
                {questions.map(question => (
                    <div>
                        Question id: {question.questionId} <br/>
                        Question Content: {question.content} <br/>
                        Question Type: {question.type} <br/>
                        <div key={`inline-${question.type}`} className="mb-3">

                            {question.answers.map(answer =>{
                 
                                if (question.type === "MUTIPLE_CHOICE") {
                                    
                                    return( 
                                        <Form.Check
                                        inline
                                        label={answer.content}
                                        name={question.questionId}
                                        type="radio"
                                        id={`inline-radio-${answer.answerId}`}
                                        />
                                    )
                                } else if (question.type === "MULTI_SELECT"){
                                    return(
                                        <Form.Check
                                        inline
                                        label={answer.content}
                                        name={question.questionId}
                                        type="checkbox"
                                        id={`inline-checkbox-${answer.answerId}`}
                                        /> 
                                    )
                                } else if (question.type === "SHORT_ANSWER"){
                                    return(
                                    <Form.Group className="mb-3" controlId="shortAnswerInput">
                                        <Form.Label>Enter your answer</Form.Label>
                                        <Form.Control as="textarea" rows={3} />
                                      </Form.Group>
                                    )
                                }                                
                            })}
                        </div>
                    </div>

                )) }
                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Questions questions={questions}/>
            </Form>
        </div>
    )
}
export default AttemptQuizPage;