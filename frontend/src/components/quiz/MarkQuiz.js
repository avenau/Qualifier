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
                setResult(response.data);
                console.log(result);
                if (result.quiz.questions != undefined) {
                    setQuestions(result.quiz.questions);
                    console.log(questions);
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
                <Form.Group key={"question-" + index}>
                    <Form.Label>{index} Question</Form.Label>
                </Form.Group>
        )

    return (
        <Form onSubmit={submitMarkedQuiz}>
            {questionsList.length > 0 ? questionsList : <div>NO RESULT FOUND</div>}
            <Button type="submit">Mark</Button>
        </Form>
    )
}

export default MarkQuiz;