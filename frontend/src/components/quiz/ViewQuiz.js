import { useEffect, useState } from "react";
import { Badge, Button, Col, Container, Form, ListGroup, Row } from "react-bootstrap";
import { useHistory, useLocation } from "react-router-dom";


function ViewQuiz() {
    let axios = require("axios");
    const history = useHistory();

    const resultId = useLocation().pathname.split("/")[2];
    const [result, setResult] = useState({});
    const [submittedAnswers, setSubmittedAnswers] = useState([]);
    const [questions, setQuestions] = useState([]);

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
                } else
                    setSubmittedAnswers([]);
                if (response.data.quiz.questions != undefined) {
                    setQuestions(response.data.quiz.questions)
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

    function getTypeAsPrettyString(questionType) {
        switch (questionType) {
            case "MUTIPLE_CHOICE":
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
            case "MUTIPLE_CHOICE":
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
                    </Container>
                );
            case "MULTI_SELECT":
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
                            {getViewableAnswers(question)}
                        </Row>
                    </Container>
                </ListGroup.Item>
        )

    function getPassFailOrUnmarkedBadge() {
        let badge = <span></span>

        if (result.passed != undefined && result.marked != undefined) {
            if (!result.marked)
                badge = <Badge bg="dark">Unmarked</Badge>
            else {
                if (result.passed)
                    badge = <Badge bg="success">Passed</Badge>
                else
                    badge = <Badge bg="danger">Failed</Badge>
            }
        }

        return badge;
    }

    function getMarksIfMarked() {
        let returnString = "";

        if(result.mark != undefined && result.marked != undefined) {
            if(result.marked)
                returnString = "Marks:" + result.mark;
        }
        
        return returnString;
    }

    return (
        <Container className="mt-4">
            <Row className="align-items-end">
                <Col sm>
                    <h4>{result.quiz != undefined ? result.quiz.name : ""}</h4>
                </Col>
                <Col sm="auto">
                    <h6>{getMarksIfMarked()}</h6>
                </Col>
            </Row>
            <Row>
                <Col>
                    <h5>
                        {getPassFailOrUnmarkedBadge()}
                    </h5>
                </Col>
            </Row>
            <ListGroup>
                {questionsList.length > 0 ? questionsList : <div>NO RESULT FOUND</div>}
            </ListGroup>
            <Row className="mt-4">
                <Col sm="auto">
                    <Button variant="secondary" onClick={() => history.goBack()}>Back</Button>
                </Col>
                <Col sm></Col>

            </Row>
        </Container>
    )
}

export default ViewQuiz;