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
    const [results, setResults] = useState([]);
    const selectUpdates = {};
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
        let newResults = [];
        let checked = false;

        if (results.length === 0){
            newResults.push({
                questionId: id,
                answerId: [name],
                questionType: "Short Answer",
                answerContent: value,
            })
            checked = true;
        } else {
            results.map((result) => {
                    if (result.questionId == id){
                        checked = true;
                        result.answerContent = value;                  
                    }
                    newResults = results;
            })
        }
        if (checked === false){
            newResults.push({
                questionId: id,
                answerId: [name],
                questionType: "Short Answer",
                answerContent: value,
            })
        }
        setResults(newResults);
    })

    const multipleChoiceChange = ((event) => {
        console.log("MULTIPLE CHOICE CHANGE");
        let {name, id} = event.target;
        let newResults = [];
        let checked = false;

        if (results.length === 0){
            newResults.push({
                questionId: name,
                answerId: [id],
                questionType: "Multiple Choice",
                answerContent: "Doesnt Matter",
            })
            checked = true;
            console.log('Length 0 RESULTS: ' + newResults.length);
        } else {
            results.map((result) => {
                 if (result.questionId == name){
                     checked = true;
                     result.answerId = [id];                   
                 }
                 newResults = results;
            })
        }
        if (checked === false){
            newResults.push({
                questionId: name,
                answerId: [id],
                questionType: "Multiple Choice",
                answerContent: "Doesnt Matter",
            })
        }
        setResults(newResults);
    })

    const multiSelectChange = ((event) => {

        
        let {name, id} = event.target;
        let checked = false;
        let newResults = [];
        
        if (results.length === 0){
            newResults.push({
                questionId: name,
                answerId: [id],
                questionType: "Multi Select",
                answerContent: "Multi Select so doesnt matter",
            })
            checked = true;
            console.log('Length 0 RESULTS: ' + newResults.length);
        } else {
            results.map((result) => {
                 if (result.questionId == name){
                     checked = true;
                     if (result.answerId.includes(id)){
                        result.answerId.splice(result.answerId.indexOf(id), 1);
                     } else {
                        result.answerId.push(id);
                     }
                     
                 }
                 newResults = results;
            })
        }

        if (checked === false){
            newResults.push({
                questionId: name,
                answerId: [id],
                answerContent: "Multi Select - So No Content",
            })
        }
        setResults(newResults);
        
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