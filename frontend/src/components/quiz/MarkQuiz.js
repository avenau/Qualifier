import { useEffect, useState } from "react";
import { Button, Container, Form, ListGroup } from "react-bootstrap";
import { useLocation } from "react-router-dom";


function MarkQuiz() {
    let axios = require("axios");

    const resultId = 33;//useLocation().pathname.split("/")[2];
    const [result, setResult] = useState({});
    const [questions, setQuestions] = useState([]);
    const [marksToAdd, setMarksToAdd] = useState([]);

    useEffect(() => {
        getResult();
    }, []);

    function getResult() {
        axios.post('http://localhost:9999/getResult', { resultId: resultId })
            .then(function (response) {
                console.log(response);
                setResult(response.data)
                if (response.data.quiz.questions != undefined) {
                    setQuestions(response.data.quiz.questions);
                    let initMarks = [];
                    response.data.quiz.questions.forEach(element => {
                        initMarks.push(0);
                    });
                    setMarksToAdd(initMarks);
                } else
                    setQuestions([]);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log("finally");
            })
    }

    const submitMarkedQuiz = (evt) => {
        evt.preventDefault();
        console.log("Submitting quiz");
        let markedResults = result.mark;
        marksToAdd.forEach( mark => {
            markedResults += mark;
        })
        let finishedMarks = result;
        finishedMarks.mark = markedResults;
        finishedMarks.marked = true;
        setResult(finishedMarks);
        axios.post('http://localhost:9999/submitMarkedResult', finishedMarks)
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error){
            console.log(error);
        })
        .then(function () {

        })
    }

    const setMarks = (event, index) => {
        // console.log(event.target.value)
        let markArray = marksToAdd.slice();
        markArray[index] = parseInt(event.target.value);
        setMarksToAdd(markArray);
    }

    const questionsList =
        questions.map(
            (question, index) =>
                <ListGroup.Item key={"question-" + index}>
                    <span>{index} Question</span>
                    <br />
                    <span>{question.content}</span>
                    <Form.Group>
                        {question.type === "SHORT_ANSWER" ?
                            <div>
                                <div>{question.submittedAnswers[0].answerContent != null ?
                                    <div>
                                        {question.submittedAnswers[0].answerContent}
                                    </div> :
                                    <div>
                                        No answer submitted
                                    </div>
                                }</div>
                                <Form.Control type="number" min="0" max={question.points} value={marksToAdd[index]} onChange={(e) => setMarks(e, index)} required/>
                                <Form.Text>Total Marks {question.points}</Form.Text>
                            </div>
                            :
                            <span></span>
                        }
                    </Form.Group>
                </ListGroup.Item>
        )

    return (
        <Container>
            <Form onSubmit={submitMarkedQuiz}>
                <ListGroup>
                    {questionsList.length > 0 ? questionsList : <div>NO RESULT FOUND</div>}
                </ListGroup>
                <Button type="submit">Mark</Button>
            </Form>
        </Container>
    )
}

export default MarkQuiz;