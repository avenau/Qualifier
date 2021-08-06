import { useEffect, useState } from "react";
import { Badge, Button, Col, Container, Form, ListGroup, Row } from "react-bootstrap";
import { useHistory, useLocation } from "react-router-dom";


function MarkQuiz() {
    let axios = require("axios");
    const history = useHistory();

    const resultId = useLocation().pathname.split("/")[2];
    const [result, setResult] = useState({});
    const [submittedAnswers, setSubmittedAnswers] = useState([]);
    const [questions, setQuestions] = useState([]);
    const [marksToAdd, setMarksToAdd] = useState([]);

    useEffect(() => {
        getResult();
    }, []);

    function getResult() {
        axios.post('http://localhost:9999/getResult', [resultId])
            .then(function (response) {
                console.log(response);
                setResult(response.data)
                if (response.data.submittedAnswers != undefined) {
                    setSubmittedAnswers(response.data.submittedAnswers);
                    setQuestions(response.data.quiz.questions)
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
        console.log(marksToAdd);
        marksToAdd.forEach(mark => {
            finishedResult.mark += mark;
        })
        finishedResult.marked = true;
        finishedResult.passed = finishedResult.mark >= finishedResult.quiz.passingMark;
        setResult(finishedResult);
        console.log(finishedResult);
        let returnArray = [ finishedResult.resultId, finishedResult.mark ]
        axios.post('http://localhost:9999/submitMarkedResult', returnArray)
            .then(function (response) {
                console.log(response);
                history.goBack();
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {

            })
    }

    const setMarks = (event, index) => {
        console.log(event.target.value)
        let markArray = marksToAdd.slice();
        console.log(index);
        markArray[index] = parseInt(event.target.value);
        setMarksToAdd(markArray);
    }

    function getTypeAsPrettyString(questionType) {
        switch (questionType) {
            case "MULTIPLE_CHOICE":
                return "Multiple Choice";
            case "SHORT_ANSWER":
                return "Short Answer";
            case "MULTI_SELECT":
                return "Multi-select";
            default:
                break;
        }
    }

    function displayShortAnswerQuestionAnswer(question) {
        let returnContent = ""
        submittedAnswers.forEach(
            submittedAnswer => {
                if (submittedAnswer.question.questionId == question.questionId) {
                    if (submittedAnswer.answerContent != null)
                        returnContent = submittedAnswer.answerContent
                }
            }
        )
        if (returnContent.length == 0) {
            returnContent = "No answer submitted";
        }
        return returnContent;
    }

    function displayIfAnswerIsSelected(question, answer) {
        {
            let returnComponent = <span></span>
            submittedAnswers.forEach(
                submittedAnswer => {
                    if (submittedAnswer.answer.answerId == answer.answerId && submittedAnswer.question.questionId == question.questionId) {
                        returnComponent =
                            <Badge bg="dark">Selected</Badge>
                    }
                }
            )
            return returnComponent
        }
    }

    function getViewableAnswers(question, index) {
        switch (question.type) {
            case "MULTIPLE_CHOICE":
                return (
                    <Container>
                        {question.answers.map(
                            (answer) => {
                                return (<Row>
                                    {answer.correct ?
                                        <Col sm={2}>
                                            <Badge bg="success">Correct</Badge>
                                        </Col>
                                        : <Col sm={2}>
                                        </Col>
                                    }
                                    <Col sm>
                                        {answer.content}
                                    </Col>
                                    <Col sm>
                                        {displayIfAnswerIsSelected(question, answer)}
                                    </Col>
                                </Row>);
                            }
                        )
                        }
                    </Container>
                );
            case "SHORT_ANSWER":
                return (
                    <Container>
                        <Row>
                            <Col>
                                <p>{displayShortAnswerQuestionAnswer(question)}</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Control type="number" min="0" max={question.points} onChange={(e) => setMarks(e, index)} required />
                                    <Form.Text>Total Marks {question.points}</Form.Text>
                                </Form.Group>
                            </Col>
                        </Row>
                    </Container>
                );
            case "MULTI_SELECT":
                return (
                    <Container>
                        {question.answers.map(
                            (answer) => {
                                return (
                                    <Row>
                                        {answer.correct ?
                                            <Col sm={2}>
                                                <Badge bg="success">Correct</Badge>
                                            </Col>
                                            : <Col sm={2}>
                                            </Col>
                                        }
                                        <Col sm>
                                            {answer.content}
                                        </Col>
                                        <Col sm>
                                            {displayIfAnswerIsSelected(question, answer)}
                                        </Col>
                                    </Row>);
                            }
                        )
                        }
                    </Container>
                );
            default:
                return (
                    <Container>
                        <Row>
                            <Col>
                                <p>No Answers</p>
                            </Col>
                        </Row>
                    </Container>
                );
        }
    }


    const questionsList =
        questions.map(
            (question, index) =>
                <ListGroup.Item key={"sa-" + index}>
                    <Container>
                        <Row className="align-items-center">
                            <Col sm>
                                <h6>
                                    Question {index + 1} <Badge bg="secondary">{
                                        getTypeAsPrettyString(question.type, index)
                                    }</Badge>
                                </h6>
                            </Col>
                            <Col sm="auto">
                                <span>Points {question.points}</span>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <p>{question.content}</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col>
                                <h6>Answer/s:</h6>
                            </Col>
                        </Row>
                        <Row>
                            {getViewableAnswers(question, index)}
                        </Row>
                    </Container>
                </ListGroup.Item>
        )

    return (
        <Container className="mt-4">
            <Row>
                <Col>
                    <h4>{result.quiz != undefined ? result.quiz.name : ""}</h4>
                </Col>
            </Row>
            <Form onSubmit={submitMarkedQuiz}>
                <ListGroup>
                    {questionsList.length > 0 ? questionsList : <div>NO RESULT FOUND</div>}
                </ListGroup>
                <Row className="mt-4">
                    <Col sm="auto">
                        <Button variant="secondary" onClick={() => history.goBack()}>Back</Button>
                    </Col>
                    <Col sm></Col>
                    <Col sm="auto">
                        <Button type="submit">Mark</Button>
                    </Col>
                </Row>
            </Form>
        </Container>
    )
}

export default MarkQuiz;