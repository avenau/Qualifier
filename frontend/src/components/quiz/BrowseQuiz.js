import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Button, Card, ListGroup, Form, InputGroup, FormControl} from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";

function BrowseQuiz() {
    const axios = require('axios');
    let history = useHistory();
    const [quizzes, setQuizzes] = useState([{
        quizId: 0,
        name: "",
        description: "",
        duration: 0.0,
        questionCount: 0,
        passingMark: 101.0,
        questions: []   
    }])


    useEffect(() => {
        axios
        .get('http://localhost:9999/getAllQuizzes')
        .then((response) => {
            setQuizzes(response.data);
        })
        .catch((error) => {
            history.push("/*");
        })

    }, [quizzes.length])

    

    return (
        <Container className = "d-flex justify-content-center pt-5">
            <Card className="w-75">
                <Card.Header as="h5">
                    Quizzes
                    <Form className = "pt-2">
                        <Row className="align-items-center">
                            <InputGroup className="mb-3 w-50">
                                <FormControl
                                placeholder="Search Quiz"
                                aria-label="Search Quiz"
                                aria-describedby="basic-addon2"
                                />
                                <Button variant="outline-secondary" id="button-addon2">
                                    Search
                                </Button>
                            </InputGroup>
                        </Row>
                    </Form>
                </Card.Header>
                <Card.Body>
                        <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + quizzes[0].quizId}>
                            <Row>
                                <Col sm={4}>
                                    <ListGroup>
                                        {quizzes.map(quiz => (
                                            <ListGroup.Item action href= {'#' + quiz.quizId}>
                                                {quiz.name}
                                            </ListGroup.Item>  
                                        ))}
                                    </ListGroup>
                                </Col>
                                <Col sm={8}>
                                    <Tab.Content>
                                        {quizzes.map(quiz => (
                                            <Tab.Pane eventKey={'#' + quiz.quizId}>
                                                <Row>
                                                    <Col sm={8}>
                                                        <h3>{quiz.name}</h3>
                                                        <p>{quiz.description}</p>
                                                    </Col>
                                                    <Col>
                                                        <Button variant="primary" onClick={(()=>{
                                                            history.push('/startquiz/' + quiz.quizId);
                                                        })}>Take Quiz</Button>
                                                        <p class="pt-2"><strong>Time Limit: </strong>{quiz.duration} sec<br/>
                                                        <strong>No. Questions: </strong>{quiz.questionCount}<br/>
                                                        <strong>Pass Mark: </strong>{quiz.passingMark}%
                                                        </p>
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
export default BrowseQuiz;