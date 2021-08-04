import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Button, Card, ListGroup, Form, InputGroup, FormControl, Table} from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";
import { ImBin } from "react-icons/im"

function TrainerSkillsPage() {
    const axios = require('axios');
    let history = useHistory();
    const [isLoading, setLoading] = useState(true);
    // const [quizzes, setQuizzes] = useState([{
    //     quizId: 0,
    //     name: "",
    //     description: "",
    //     duration: 0.0,
    //     questionCount: 0,
    //     passingMark: 101.0,
    //     questions: []   
    // }])
    const [skills, setSkills] = useState([{
        quizId: 0,
        name: "",
        description: "",
        duration: 0.0,
        questionCount: 0,
        passingMark: 101.0,
        questions: []   
    }])

    
       const axiosConfig = {
            headers: { Authorization: `Bearer ${sessionStorage.jwtToken}`}
        };
    

    useEffect(() => {
        axios
        .get('http://localhost:9999/getAllSkills', axiosConfig)
        .then((response) => {
            setSkills(response.data);
            setLoading(false);
        })
        .catch((error) => {
            history.push("/*");
        })

    }, [skills.length])

    function hasSkillLevel(skillLevels, level) {
        skillLevels.map((skillLevel) => {
            if (skillLevel.knowledgelevel == level){
                if (skillLevel.quizId == null){
                    return false;
                }
                return true;
            }
        })
        return false;
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
                            Skill List
                        </Col>
                        
                        <Col className = "d-flex justify-content-end">
                            <Form className = "pt-1">
                                <Row className="align-items-center">
                                    <InputGroup className="">
                                        <FormControl
                                        placeholder="Search Skill"
                                        aria-label="Search Quiz"
                                        aria-describedby="basic-addon2"
                                        />
                                        <Button variant="outline-secondary" id="button-addon2">
                                            Search
                                        </Button>
                                    </InputGroup>
                                </Row>
                            </Form>
                        </Col>
                    </Row>
                </Card.Header>
                <Card.Body>
                        <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + skills[0].skillId}>
                            <Row>
                                <Col sm={4}>
                                    <ListGroup>
                                        {skills.map(skill => (
                                            <ListGroup.Item action href= {'#' + skill.skillId}>
                                                {skill.name}
                                            </ListGroup.Item>  
                                        ))}
                                    </ListGroup>
                                </Col>
                                <Col sm={8}>
                                    <Tab.Content>
                                        {skills.map(skill => (
                                            <Tab.Pane eventKey={'#' + skill.skillId}>
                                                <Row>
                                                    <Col sm={8}>
                                                        
                                                        <h3 >Skills Details</h3>
                                                        <Row className="pb-2">
                                                            <Col>
                                                                <h6 class="mb-2 text-muted">Skill Name: {skill.name}</h6>
                                                            </Col>
                                                            <Col>
                                                                <Button variant="danger"><ImBin/></Button>
                                                            </Col>
                                                        </Row>
                                                        <InputGroup className="mb-3 w-100">
                                                            <FormControl
                                                            placeholder={skill.name}
                                                            aria-label="Recipient's username"
                                                            aria-describedby="basic-addon2"
                                                            />
                                                            <Button variant="outline-secondary" id="button-addon2">
                                                                Update
                                                            </Button>
                                                        </InputGroup>
                                                        <h3 >Quizzes</h3>
                                                        {/* <h4>Skill LEvel List {skill.skillLevels.length}</h4> */}
                                                        {/* <h4> Include Test {hasSkillLevel(skill.skillLevels, "BEGINNER")}</h4> */}
                                                        
                                                        <Table striped bordered>
                                                            <tbody>

                                                                <tr>
                                                                <td>Beginner</td>
                                                                <td>
                                                                    {/* {hasSkillLevel(skill.skillLevels, "BEGINNER")
                                                                        ? <p>Edit</p> 
                                                                        : <Card.Link href="">Create</Card.Link>
                                                                    } */}
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Intermediate</td>
                                                                <td>
                                                                    {/* {hasSkillLevel(skill.skillLevels, "INTERMEDIATE")
                                                                        ? <p>Edit</p> 
                                                                        : <Card.Link href="">Create</Card.Link>
                                                                    } */}
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Expert</td>
                                                                <td>
                                                                    {/* {hasSkillLevel(skill.skillLevels, "INTERMEDIATE")
                                                                        ? <p>Edit</p> 
                                                                        : <Card.Link href="">Create</Card.Link>
                                                                    } */}
                                                                </td>
                                                                </tr>
                                                            </tbody>
                                                        </Table>


                                                        {/* <Button variant="primary" onClick={(()=>{
                                                            history.push('/startquiz/' + quiz.quizId);
                                                        })}>Take Quiz</Button> */}
                                                        {/* <p class="pt-2"><strong>Time Limit: </strong>{quiz.duration} sec<br/>
                                                        <strong>No. Questions: </strong>{quiz.questionCount}<br/>
                                                        <strong>Pass Mark: </strong>{quiz.passingMark}% */}
                                                        {/* </p> */}
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
    )

}
export default TrainerSkillsPage;