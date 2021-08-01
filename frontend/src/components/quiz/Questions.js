import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button } from 'react-bootstrap';

function Questions(props) {
    let questions = props.questions;

    return (
        questions.map(question => (
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

        ))
    )
    
}

export default Questions;