import { useState } from "react";
import { useHistory } from "react-router-dom";
import BrowseQuiz from "../components/quiz/BrowseQuiz"
import QuizStartPage from "../components/quiz/BrowseQuiz"
import { Button, Container, Row, Card, Image } from 'react-bootstrap';

function HomePage() {
    let history = useHistory();

    //To Redirect Page
    /*
        history.push('route') -> This will redirect you to the page
        route being the path that was set in App.js
    */

    let nav = <LoggedOut/>

    function LoggedOut () {

        return (                      
            <Card className="text-center">
                <Card.Header>SkillUp</Card.Header>
                <Card.Body>
                    <Card.Title>Welcome to SkillUp</Card.Title>
                    <Card.Text>
                    </Card.Text>
                    <Button variant="primary" onClick={() => {history.push('/login')} }>Login</Button>
                </Card.Body>
                <Card.Footer className="text-muted"></Card.Footer>
            </Card>

        )
    }

    
    function LoggedIn () {

        return (                      
            <Card className="text-center">
                <Card.Header>SkillUp</Card.Header>
                <Card.Body>
                    <Card.Title>Welcome back {sessionStorage.getItem('username')}</Card.Title>
                    <Card.Text>
                        
                    </Card.Text>
                </Card.Body>
                <Card.Footer className="text-muted"></Card.Footer>
            </Card>

        )
    }

    if (sessionStorage.length == 0){
        nav = <LoggedOut/>
    } else {
        nav = <LoggedIn/>
    }



    return (
        <Container className="pt-4">
            {nav}
        </Container>
    )
}
export default HomePage;


// <Container>
// <h1>HOME</h1>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/profile') }}>My Profile</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/startquiz') }}>Start Skill Quiz</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/placements') }}>Search For Placements</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/createPlacement') }}>Create Placement</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/suggestskill') }}>Suggest Skill</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/browsequiz') }}>Browse Quiz</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/myskills') }}>Trainee: My Skills Page</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/traineeResults') }}>Trainee Results</Button>
// </Row>
// <Row className="mt-4">
//     <Button variant="secondary" onClick={() => { history.push('/searchtrainee') }}>Search For Trainees</Button>
// </Row>
// </Container>