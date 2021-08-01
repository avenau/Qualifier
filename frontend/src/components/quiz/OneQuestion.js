import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button } from 'react-bootstrap';
/*
    Experimenting with one question perp page
*/

function OneQuestion(props) {
    let questions = props.question;
    let isLast = props.isLast;
    let axios = require('axios');

    const [answers, setAnswers] = useState([0, 1, 2])

    const addAnswer = (() => {
        
    })

    const submitQuestion = (() => {
        axios
        .post('http://localhost:9999/submitQuiz', { quizId: 6, answers: ["1", "2", "3"] })
        .then((response) => {

        })
        .catch(()=>{

        })
    })

    return (
        <Form>
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
                                id={answer}
                                />
                            )
                        } else if (question.type === "MULTI_SELECT"){
                            return(
                                <Form.Check
                                inline
                                label={answer.content}
                                name={question.questionId}
                                type="checkbox"
                                id={answer}
                                /> 
                            )
                        } else if (question.type === "SHORT_ANSWER"){
                            return(
                            <Form.Group className="mb-3" controlId="shortAnswerInput" id={answer}>
                                <Form.Label>Enter your answer</Form.Label>
                                <Form.Control as="textarea" rows={3} />
                                </Form.Group>
                            )
                        }                                
                    })}
                </div>
            </div>


            <Button variant="primary" type="submit" onClick={submitQuestion}>
                Submit
            </Button>
        </Form>

            // questions.map(question => (
            //     <div>
            //         Question id: {question.questionId} <br/>
            //         Question Content: {question.content} <br/>
            //         Question Type: {question.type} <br/>
            //         <div key={`inline-${question.type}`} className="mb-3">

            //             {question.answers.map(answer =>{
            
            //                 if (question.type === "MUTIPLE_CHOICE") {
                                
            //                     return( 
            //                         <Form.Check
            //                         inline
            //                         label={answer.content}
            //                         name={question.questionId}
            //                         type="radio"
            //                         id={`inline-radio-${answer.answerId}`}
            //                         />
            //                     )
            //                 } else if (question.type === "MULTI_SELECT"){
            //                     return(
            //                         <Form.Check
            //                         inline
            //                         label={answer.content}
            //                         name={question.questionId}
            //                         type="checkbox"
            //                         id={`inline-checkbox-${answer.answerId}`}
            //                         /> 
            //                     )
            //                 } else if (question.type === "SHORT_ANSWER"){
            //                     return(
            //                     <Form.Group className="mb-3" controlId="shortAnswerInput">
            //                         <Form.Label>Enter your answer</Form.Label>
            //                         <Form.Control as="textarea" rows={3} />
            //                     </Form.Group>
            //                     )
            //                 }                                
            //             })}
            //         </div>
            //     </div>

            // ))
    )
    
}

export default Questions;