import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check, Button } from 'react-bootstrap';

function Questions(props) {
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
   const submittedAnswerTemplate = 
   {
       answerId: [],
       answerType: "",
       answerContent: "",
   }
    const [results, setResults] = useState({50000: submittedAnswerTemplate});
    const [questions, setQuestions] = useState(questionTemplate);
    let history = useHistory();
    let axios = require('axios');

    

    const addAnswer = (() => {
        
    })

    useEffect(() => {
        axios.get("http://localhost:9999/getQuizQuestions", {   
            params: {
                quizId:props.quizId
            }
        })
        .then((response) => {
            setQuestions(response.data);
        })
    }, [questions.length])

    /*
        Stuck
    */
    const submitQuiz = (() => {
        console.log("SUBMITTING QUIZ");
        axios
        .post('http://localhost:9999/submitQuiz', { payload: results})
        .then((response) => {

            history.push('/finishquiz');
        })
        .catch(()=>{
            
        })
    })

        

    const shortAnswerChange = ((event) => {
        let {name, id, value} = event.target;
        console.log("RESULT : " + this.results);

        setResults({...results, [id]: {
                                        answerId: [name],
                                        answerContent: value,
                                    }
                    });
    })

    const multipleChoiceChange = ((event) => {
        console.log("MULTIPLE CHOICE CHANGE");
        let {name, id} = event.target;

        setResults({...results, [name]: {
                                        answerId: [id],
                                        answerContent: "Doesnt Matter",
                                    }
                    });
    })

    const multiSelectChange = ((event) => {


        let {name, id} = event.target;

        if (!(results.hasOwnProperty(name))){
            setResults({...results, [name]: {
                                            answerId: [].push(id),
                                            answerContent: "Doesnt Matter",
                                        }
                        });

        } else {
            if (results[name].answerId.length === 0){
                setResults({...results, [name]: {
                    answerId: [].push(id),
                    answerContent: "Doesnt Matter",
                }
                });

            } else if (results[name]['answerId'].indexOf(id) >= 0){

                    setResults({...results, [name]: {
                        answerId: results[name].answerId.push(id),
                        answerContent: "Doesnt Matter",
                    }})

            } else {
                setResults({...results, [name]: {
                    answerId: results[name].answerId.splice(results[name].answerId.indexOf(id), 1),
                    answerContent: "Doesnt Matter",
                }})

               }
        }
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
                                        id={answer.answerId}
                                        onChange={multipleChoiceChange}
                                        />
                                    )
                                } else if (question.type === "MULTI_SELECT"){
                                    return(
                                        <Form.Check
                                        label={answer.content}
                                        name={question.questionId}
                                        type="checkbox"
                                        id={answer.answerId}
                                        onChange={multiSelectChange}
                                        /> 
                                    )
                                } else if (question.type === "SHORT_ANSWER"){
                                    return(
                                    <Form.Group className="mb-3" id={answer.answerId}>
                                        <Form.Control as="textarea" id={question.questionId} name={answer.answerId} onChange={shortAnswerChange} placeholder= "Enter your answer here" rows={3} />
                                      </Form.Group>
                                    )
                                }                                
                            })}
                        </div>
                    </div>

                )) }
                <Button variant="primary" onClick={submitQuiz}>
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