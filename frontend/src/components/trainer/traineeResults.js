import { List } from '@material-ui/core';
import { useEffect, useState } from 'react';

const { Container, ListGroup } = require('react-bootstrap');

function TraineeResults(props) {
    const axios = require('axios');
    
    const traineeId = 14;//CHANGE TO SELECTED TRAINEE ID
    const [results, setResults] = useState([]);

    useEffect(() => {
        axios.post('http://localhost:9999/getTraineesResults', { userId: traineeId })
        .then(function (response){
            console.log(response);
            setResults(response.data);            
        })
        .catch(function (error){
            console.log(error);
        })
        .then(function () {
            
        })
    }, [])

    const resultsList = results.map(
        (result) => {
            <ListGroup.Item key={"#" + result.resultId}>
                {/* {result.quiz.} */}
            </ListGroup.Item>
        }
    )

    return (
        <Container>
            <h3>Results</h3>
            <ListGroup>
                {resultsList > 0 ? resultsList : <ListGroup.Item>No Results</ListGroup.Item>}
            </ListGroup>
        </Container>
    )
}

export default TraineeResults;