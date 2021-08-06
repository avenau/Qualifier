import {useLocation, useHistory} from "react-router-dom";
import { useState, useEffect } from "react";
import { Form, Check, Button, Container, Card } from 'react-bootstrap';

function FinishCreateQuiz(){
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

        return (
            <div>
                <p>Quiz name: {JSON.stringify(data)}</p>
            </div>
        )
    }


    return (
        <Container className="pt-4 d-flex justify-content-center">
            <Card className="text-center w-50">
                <Card.Header><strong>Quiz Saved</strong></Card.Header>
                <Card.Body>
                    <Card.Title>Quiz: {data.name}</Card.Title>
                    <Card.Text>
                        <strong>Description: </strong> {data.description} <br/>
                        <strong>Duration: </strong> {data.duration} seconds <br/>
                        <strong>Passing Mark: </strong> {data.passingMark} <br/>
                    </Card.Text>
                    <Button variant="primary" onClick={() => {history.push('/trainer/searchskills')} }>View Other Quizzes</Button>
                </Card.Body>
            </Card>
        </Container>
    )
}

export default FinishCreateQuiz;