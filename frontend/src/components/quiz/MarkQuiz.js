import { useEffect, useState } from "react";
import { Button, Container, Form, ListGroup } from "react-bootstrap";
import { useHistory, useLocation } from "react-router-dom";


function MarkQuiz() {
    let axios = require("axios");
    const history = useHistory();

    const resultId = useLocation().pathname.split("/")[2];
    const [result, setResult] = useState({});
    const [submittedAnswers, setSubmittedAnswers] = useState([]);
    const [marksToAdd, setMarksToAdd] = useState([]);

    useEffect(() => {
        getResult();
    }, []);

    function getResult() {
        axios.post('http://localhost:9999/getResult', { resultId: resultId })
            .then(function (response) {
                console.log(response);
                setResult(response.data)
                if (response.data.submittedAnswers != undefined) {
                    setSubmittedAnswers(response.data.submittedAnswers);
                    let initMarks = [];
                    response.data.quiz.questions.forEach(element => {
                        initMarks.push(0);
                    });
                    setMarksToAdd(initMarks);
                } else
                    setSubmittedAnswers([]);
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
        let finishedResult = result;
        marksToAdd.forEach( mark => {
            finishedResult.mark += mark;
        })
        finishedResult.marked = true;
        finishedResult.passed = finishedResult.mark >= finishedResult.quiz.passingMark;
        setResult(finishedResult);
        axios.post('http://localhost:9999/submitMarkedResult', finishedResult)
        .then(function (response) {
            console.log(response);
            history.push("/traineeResults");
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
        submittedAnswers.map(
            (submittedAnswer, index) =>
                <ListGroup.Item key={"sa-" + index}>
                    <span>{index} Question</span>
                    <br />
                    <span>{submittedAnswer.question.content}</span>
                    {/* <Form.Group>
                        {question.type === "SHORT_ANSWER" ?
                            <div>
                                <div>{questions.submittedAnswers[0].answerContent != null ?
                                    <div>
                                        {questions.submittedAnswers[0].answerContent}
                                    </div> :
                                    <div>
                                        No answer submitted
                                    </div>
                                }</div>
                                <Form.Control type="number" min="0" max={question.points} onChange={(e) => setMarks(e, index)} required/>
                                <Form.Text>Total Marks {question.points}</Form.Text>
                            </div>
                            :
                            <span></span>
                        }
                    </Form.Group> */}
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