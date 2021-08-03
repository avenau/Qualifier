import { useEffect, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useLocation } from "react-router-dom";


function MarkQuiz() {
    let axios = require("axios");

    const resultId = 33;//useLocation().pathname.split("/")[2];
    const [result, setResult] = useState({});
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        getResult();
    }, [/*questions.length*/]);

    function getResult() {
        axios.post('http://localhost:9999/getResult', { resultId: resultId })
            .then(function (response) {
                console.log(response);
                setResult(response.data)
                if (response.data.quiz.questions != undefined) {
                    setQuestions(response.data.quiz.questions);
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
    }

    const questionsList =
        questions.map(
            (question, index) =>
                <div key={"question-" + index}>
                    <span>{index} Question</span>
                    <br/>
                    <span>{question.content}</span>
                    <div>
                        {question.answers.map(
                            (answer, index) =>
                                <div key={"id-" + answer.answerId + "-answer-" + index}>
                                    {answer.content}
                                </div>
                        )}
                    </div>
                    <div>
                        {question.type}
                    </div>
                    <div>
                    <Form.Group>
                    {   question.type === "SHORT_ANSWER" ? 
                        <Form.Text type="text" /> :
                        <span></span>
                    }
                    </Form.Group>
                    </div>
                </div>
        )

    return (
        <Container>
            <Form onSubmit={submitMarkedQuiz}>
                {questionsList.length > 0 ? questionsList : <div>NO RESULT FOUND</div>}
                <Button type="submit">Mark</Button>
            </Form>
        </Container>
    )
}

export default MarkQuiz;