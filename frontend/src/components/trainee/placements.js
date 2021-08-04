import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Button, Card, ListGroup, Form, InputGroup, FormControl} from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";

function SearchPlacements(){

    //CHANGE THIS TO SESSION TRAINEES ID
    const traineeId = sessionStorage.getItem("userId");

    const axios = require('axios');
    const axiosConfig = {
        headers: { Authorization: `Bearer ${sessionStorage.jwtToken}`}
    };

    const [searchTerm, setSearchTerm] = useState("");
    const [searchError, setSearchError] = useState("");
    const [isLoading, setLoading] = useState(true);
    let history = useHistory();
    const [placementResult, setPlacementResult] = useState([{
        placementId: 0,
        name: "",
        startDate: "",
        completionDate: "",
        description: "",
        location: "", 
    }])

    useEffect(() => {
        axios.get('http://localhost:9999/getAllPlacements', axiosConfig)
        .then((response) => {
            console.log(response);
            setPlacementResult(response.data);
            setLoading(false);
        })
        .catch((error) => {
            history.push("/*");
        })

    }, [])

    const submitPlacementSearch = (evt) => {
        evt.preventDefault();
        if (searchTerm.trim().length > 0) {
            setSearchError("");
            const config = { headers: {'Content-Type': 'application/json', Authorization: `Bearer ${sessionStorage.jwtToken}`} };
            axios.post('http://localhost:9999/searchPlacements', searchTerm, config)
                .then(function (response) {
                    console.log(response);
                    setPlacementResult(response.data);
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

    const applyForPlacement = (index) => {
        axios.post('http://localhost:9999/applyForPlacement', [traineeId, placementResult[index].placementId])
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        })
        .then(function () {
            console.log('finally');
        })
    }
    
    if (isLoading) {
        return <div className="App">Loading...</div>;
    }

    return (
        <Container className = "d-flex justify-content-center pt-5">
        <Card className="w-75">
            <Card.Header as="h5">
                <Row>
                    <Col sm={3} className = "pt-2">
                        Placements
                    </Col>
                    
                    <Col className = "d-flex justify-content-end">
                        <Form className = "pt-1" onSubmit={submitPlacementSearch}>
                            <Row className="align-items-center">
                                <InputGroup className="">
                                    <FormControl
                                    placeholder="Search Placements"
                                    aria-label="Placements"
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
                    <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + placementResult[0].placementId}>
                        <Row>
                            <Col sm={4}>
                                <ListGroup>
                                    {placementResult.map(placement => (
                                        <ListGroup.Item key={'#' + placement.placementId} action href= {'#' + placement.placementId}>
                                            {placement.name}
                                        </ListGroup.Item>  
                                    ))}
                                </ListGroup>
                            </Col>
                            <Col sm={8}>
                                <Tab.Content>
                                    {placementResult.map(
                                        (placement,index) => (
                                        <Tab.Pane eventKey={'#' + placement.placementId}>
                                            <Row>
                                                <Col sm={8}>
                                                    <h3>{placement.name}</h3>
                                                    <button value="Apply" onClick={() => applyForPlacement(index)}>Apply</button>
                                                    <p>{placement.description}</p>
                                                    <p>{placement.client.name}</p>
                                                    <p>{placement.location}</p>
                                                    <p>Start Date: {placement.startDate}</p>
                                                    <p>Completion Date: {placement.completionDate}</p>
                                                    <ListGroup>
                                                        {placement.skillsNeeded.map(
                                                            (skillLevel, index) =>
                                                                <ListGroup.Item key={"skill-" + skillLevel.skillLevelId}>
                                                                    {skillLevel.skill.name}: {skillLevel.level}
                                                                </ListGroup.Item>
                                                        )}
                                                    </ListGroup>

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

export default SearchPlacements;