import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Button, Card, ListGroup, Form, InputGroup, FormControl} from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";

function SearchPlacements(){

    const axios = require('axios');

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
        axios.get('http://localhost:9999/getAllPlacements')
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
            const config = { headers: {'Content-Type': 'application/json'} };
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

    const skillLevelList = placementResult.map((placementObject)=>{
        if(placementObject.skillsNeeded !== undefined){
            placementObject.skillsNeeded.map(
            (skill, index) =>
                <ListGroup.Item key={"skill-" + index}>
                    {skill.skill.name}: {skill.level}
                </ListGroup.Item>
        )}else{
            <div>No skills</div>
        }
    }
        );
    

    if (isLoading) {
        return <div className="App">Loading...</div>;
    }

    return (
        // <div>
        //     <form onSubmit={submitPlacementSearch}>
        //         <label>Skill Name: </label>
        //         <input type="text" value={searchTerm} onChange={e => setPlacement(e.target.value)} required />
        //         <input type="submit" value="Search Placement" />
        //     </form>
        //     <div>{searchError}</div>
        // </div>
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
                    <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + placementResult[0].quizId}>
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
                                    {placementResult.map(placement => (
                                        <Tab.Pane eventKey={'#' + placement.placementId}>
                                            <Row>
                                                <Col sm={8}>
                                                    <h3>{placement.name}</h3>
                                                    <p>{placement.description}</p>
                                                    <p>{placement.client.name}</p>
                                                    <ListGroup>
                                                        {skillLevelList.length > 0 ? skillLevelList : <ListGroup.Item>No Skills</ListGroup.Item>}
                                                    </ListGroup>

                                                </Col>
                                                {/* <Col>
                                                    <Button variant="primary" onClick={(()=>{
                                                        history.push('/startquiz/' + placement.quizId);
                                                    })}>Take Quiz</Button>
                                                    <p class="pt-2"><strong>Time Limit: </strong>{placement.duration} sec<br/>
                                                    <strong>No. Questions: </strong>{placement.questionCount}<br/>
                                                    <strong>Pass Mark: </strong>{placement.passingMark}%
                                                    </p>
                                                </Col> */}
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