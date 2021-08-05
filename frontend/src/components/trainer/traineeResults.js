import { List } from '@material-ui/core';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

const { Container, ListGroup, Row, Col, Button } = require('react-bootstrap');

function TraineeResults(props) {
    const axios = require('axios');
    const history = useHistory();

    const traineeId = 14;//CHANGE TO SELECTED TRAINEE ID
    const [results, setResults] = useState([]);

    useEffect(() => {
        axios.post('http://localhost:9999/getTraineesResults', { userId: traineeId })
            .then(function (response) {
                console.log(response);
                setResults(response.data);
                console.log(results);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {

            })
    }, [])

    const resultsList = results.map(
        (result) =>
            <ListGroup.Item key={"#" + result.resultId}>
                <Row className="align-items-center">
                    <Col sm>
                        {result.quiz.name}
                    </Col>
                    <Col sm="auto">
                        {
                            result.quiz.marked ?
                                <span>marked</span> :
                                <span>un-marked</span>
                        }
                    </Col>
                    <Col sm="auto">
                        {
                            result.quiz.marked ?
                                <Button>view</Button> :
                                <Button onClick={() => history.push("/markQuiz/" + result.resultId)}>mark</Button>
                        }
                    </Col>
                </Row>
            </ListGroup.Item>

    );

    return (
        <Container>
            <h3>Quiz Attempts</h3>
            <ListGroup>
                {resultsList.length > 0 ? resultsList : <ListGroup.Item>No Results</ListGroup.Item>}
            </ListGroup>
        </Container>
    )
}

export default TraineeResults;