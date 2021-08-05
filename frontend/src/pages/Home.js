import { useState } from "react";
import { useHistory } from "react-router-dom";
import BrowseQuiz from "../components/quiz/BrowseQuiz"
import QuizStartPage from "../components/quiz/BrowseQuiz"
import { Button, Container, Row } from 'react-bootstrap';

function HomePage() {
    let history = useHistory();

    //To Redirect Page
    /*
        history.push('route') -> This will redirect you to the page
        route being the path that was set in App.js
    */

    return (
        <Container>
            <h1>HOME</h1>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/profile') }}>My Profile</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/startquiz') }}>Start Skill Quiz</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/placements') }}>Search For Placements</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/createPlacement') }}>Create Placement</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/suggestskill') }}>Suggest Skill</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/browsequiz') }}>Browse Quiz</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/myskills') }}>Trainee: My Skills Page</Button>
            </Row>
            <Row className="mt-4">
                <Button variant="secondary" onClick={() => { history.push('/traineeResults') }}>Trainee Results</Button>
            </Row>
        </Container>
    )
}
export default HomePage;