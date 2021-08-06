import { useEffect, useState } from "react";
import { Container, Row, Col, Tab, Button, Card, ListGroup, Form, InputGroup, FormControl } from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";
import TraineeResults from "../trainer/traineeResults";

function SearchTrainee() {

    const axios = require('axios');

    const accountType = sessionStorage.getItem('accountType');

    const [searchTerm, setSearchTerm] = useState("");
    const [searchError, setSearchError] = useState("");
    const [isLoading, setLoading] = useState(true);
    let history = useHistory();
    const [traineeResult, setTraineeResult] = useState([{
        userId: 0,
        firstName: "",
        lastName: "",
        email: "",
        city: "",
    }])

    useEffect(() => {
        axios.get('http://localhost:9999/getAllTrainees')
            .then((response) => {
                console.log(response);
                setTraineeResult(response.data);
                setLoading(false);

            })
            .catch((error) => {
                history.push("/*");
            })

    }, [])

    const submitTraineeSearch = (evt) => {
        evt.preventDefault();
        if (searchTerm.trim().length > 0) {
            setSearchError("");
            const config = { headers: { 'Content-Type': 'application/json' } };
            axios.post('http://localhost:9999/searchTrainees', searchTerm, config)
                .then(function (response) {
                    console.log(response);
                    if (response.data.length === 0) {
                        window.alert("No search results");
                    } else {
                        setTraineeResult(response.data);
                    }


                })
                .catch(function (error) {
                    console.log(error);
                })
                .then(function () {
                    console.log('finally');
                })
        } else {
            setSearchError("Search cannot be empty");
        }
    }

    if (isLoading) {
        return <div className="App">Loading...</div>;
    }

    return (
        <Container className="d-flex justify-content-center pt-5">
            <Card className="w-75">
                <Card.Header as="h5">
                    <Row>
                        <Col sm={3} className="pt-2">
                            Trainees
                        </Col>

                        <Col className="d-flex justify-content-end">
                            <Form className="pt-1" onSubmit={submitTraineeSearch}>
                                <Row className="align-items-center">
                                    <InputGroup className="">
                                        <FormControl
                                            placeholder="Search Trainees"
                                            aria-label="Trainees"
                                            aria-describedby="basic-addon2"
                                            value={searchTerm}
                                            onChange={e => setSearchTerm(e.target.value)}
                                        />
                                        <Button variant="outline-secondary" id="button-addon2" type="submit">
                                            Search
                                        </Button>
                                    </InputGroup>
                                </Row>
                            </Form>
                        </Col>
                    </Row>
                </Card.Header>
                <Card.Body>
                    <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + traineeResult[0].userId}>
                        <Row>
                            <Col sm={4}>
                                <ListGroup>
                                    {traineeResult.map(trainee => (
                                        <ListGroup.Item key={'#' + trainee.userId} action href={'#' + trainee.userId}>
                                            {trainee.firstName}
                                        </ListGroup.Item>
                                    ))}
                                </ListGroup>
                            </Col>
                            <Col sm={8}>
                                <Tab.Content>
                                    {traineeResult.map(trainee => (
                                        <Tab.Pane eventKey={'#' + trainee.userId}>
                                            <Row>
                                                <Col sm={8}>
                                                    <h3>{trainee.firstName} {trainee.lastName}</h3>
                                                    <p>{trainee.email}</p>
                                                    <p>{trainee.city}</p>
                                                    <ListGroup>
                                                        {trainee.pinnedSkills.map(
                                                            (skill, index) =>
                                                                <ListGroup.Item key={"skill-" + skill.skillLevelId}>
                                                                    {skill.skill.name}: {skill.level}
                                                                </ListGroup.Item>
                                                        )}
                                                    </ListGroup>
                                                    <ListGroup>
                                                        {console.log(JSON.stringify(trainee.skills))}
                                                        {trainee.skills.map(
                                                            (skill, index) =>
                                                                
                                                                <ListGroup.Item key={"skill-" + skill.skillLevelId}>                                                                  
                                                                    {skill.skill.name}: {skill.level}
                                                                </ListGroup.Item>
                                                        )}
                                                    </ListGroup>
                                                    {accountType == "trainer" ?
                                                        <TraineeResults traineeId={trainee.userId}></TraineeResults>
                                                        : <span></span>}
                                                </Col>
                                            </Row>
                                        </Tab.Pane>
                                    ))}


                                </Tab.Content>
                            </Col>
                        </Row>
                    </Tab.Container>
                </Card.Body>
            </Card>

        </Container>
    );
}

export default SearchTrainee;
