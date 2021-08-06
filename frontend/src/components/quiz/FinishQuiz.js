import {useLocation, useHistory} from "react-router-dom";
import { useState, useEffect } from "react";
import { Form, Check, Button, Container, Card } from 'react-bootstrap';

function FinishQuiz(){
    const location = useLocation();
    const history = useHistory();
    let data = location.state.detail;
    console.log("PASSED DATA " + JSON.stringify(data));
    function PassMessage () {

            if (data.passed){
                return (
                    <p>Congratulations you have <strong>passed</strong> the quiz!</p>
                )
            } else {
                return (
                    <p>Unfortunately you have <strong>failed</strong> the quiz!</p>
                )
            }

    }

    function ViewResult () {

        if (data.marked == true){
            return (
                <Card className="text-center w-50">
                    <Card.Header>Quiz Outcome</Card.Header>
                    <Card.Body>
                        <Card.Title>Quiz Results</Card.Title>
                        <Card.Text>
                            <strong>Mark: </strong> {data.mark} <br/>
                            <PassMessage/>
                        </Card.Text>
                        <Button variant="primary" onClick={() => {history.push('/browsequiz')} }>More Quizzes</Button>
                    </Card.Body>
                </Card>
            )
        } else {
            return (
                <Card className="text-center w-50">
                    <Card.Header>Quiz Outcome</Card.Header>
                    <Card.Body>
                        <Card.Title>Your Quiz is being marked!</Card.Title>
                        <Card.Text>
                        Your mark will be avaliable once your trainer is done marking!
                        </Card.Text>
                        <Button variant="primary" onClick={() => {history.push('/browsequiz')} }>More Quizzes</Button>
                    </Card.Body>
                </Card>
            )
        }

    }


    return (
        <Container className="pt-4 d-flex justify-content-center">
            <ViewResult/>
        </Container>
    )
}

export default FinishQuiz;