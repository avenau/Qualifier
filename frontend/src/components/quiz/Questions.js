import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button } from 'react-bootstrap';

function Questions(props) {
    let questions = props.questions;
    let axios = require('axios');

    const [answers, setAnswers] = useState([0, 1, 2])

    const addAnswer = (() => {
        
    })

    /*
        Stuck
    */
    const submitQuiz = (() => {
        console.log("SUBMITTING QUIZ");
        axios
        .post('http://localhost:9999/submitQuiz', { quizId: 6})
        .then((response) => {

        })
        .catch(()=>{

        })
    })

    return (
        <Form className = "w-50">
                {questions.map(question => (
                    <div>
                        <div className = "pb-2"><strong>Q{questions.indexOf(question) + 1}. </strong>{question.content} <br/></div>
                        <div key={`${question.questionId}`} className="mb-3">

                            {question.answers.map(answer =>{
                 
                                if (question.type === "MUTIPLE_CHOICE") {
                                    
                                    return( 
                                        <Form.Check
                                        label={answer.content}
                                        name={question.questionId}
                                        type="radio"
                                        id={answer}
                                        />
                                    )
                                } else if (question.type === "MULTI_SELECT"){
                                    return(
                                        <Form.Check
                                        label={answer.content}
                                        name={question.questionId}
                                        type="checkbox"
                                        id={answer}
                                        /> 
                                    )
                                } else if (question.type === "SHORT_ANSWER"){
                                    return(
                                    <Form.Group className="mb-3" controlId="shortAnswerInput" id={answer}>
                                        <Form.Control as="textarea" placeholder= "Enter your answer here" rows={3} />
                                      </Form.Group>
                                    )
                                }                                
                            })}
                        </div>
                    </div>

                )) }
                <Button variant="primary" type="submit" onClick={submitQuiz}>
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