import { List } from '@material-ui/core';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

const { Container, ListGroup, Row, Col, Button } = require('react-bootstrap');

function TraineeResults(props) {
    const axios = require('axios');
    const history = useHistory();

    const traineeId = props.traineeId;
    const accountType = sessionStorage.getItem('accountType');

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

    function createButton(result) {
        let button = <span></span>;

        if (accountType == "trainer")
            if (result.marked)
                button = <Button onClick={() => history.push("/viewQuiz/" + result.resultId)}>View</Button>
            else
                button = <Button onClick={() => history.push("/markQuiz/" + result.resultId)}>Mark</Button>
        else if (accountType == "trainee")
            button = <Button onClick={() => history.push("/viewQuiz/" + result.resultId)}>View</Button>

        return button;
    }

    const resultsList = results.map(
        (result) =>
            <ListGroup.Item key={"#" + result.resultId}>
                <Row className="align-items-center">
                    <Col sm>
                        {result.quiz.name}
                    </Col>
                    <Col sm="auto">
                        {
                            result.marked ?
                                <span>marked</span> :
                                <span>un-marked</span>
                        }
                    </Col>
                    <Col sm="auto">
                        {createButton(result)}
                    </Col>
                </Row>
            </ListGroup.Item>

    );

    return (
        // <Container>
        <ListGroup className="mt-4">
            <h3>Quiz Attempts</h3>
            {resultsList.length > 0 ? resultsList : <ListGroup.Item>No Results</ListGroup.Item>}
        </ListGroup>
        // </Container>
    )
}

export default TraineeResults;