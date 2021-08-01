import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Nav} from 'react-bootstrap';
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

    })

    

    return (
        <Tab.Container id="left-tabs-example" defaultActiveKey="first">
        <Row>
            <Col sm={3}>
            <Nav variant="pills" className="flex-column">
                <Nav.Item>
                <Nav.Link eventKey="first">Tab 1</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                <Nav.Link eventKey="second">Tab 2</Nav.Link>
                </Nav.Item>
            </Nav>
            </Col>
            <Col sm={9}>
            <Tab.Content>
                <Tab.Pane eventKey="first">
                    <h1>First Pane</h1>
                </Tab.Pane>
                <Tab.Pane eventKey="second">
                    <h1>Second Pane</h1>
                </Tab.Pane>
            </Tab.Content>
            </Col>
        </Row>
        </Tab.Container>
    )

}
export default BrowseQuiz;