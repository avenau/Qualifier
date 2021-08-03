import { useState} from "react";
import { useHistory } from "react-router-dom";
import BrowseQuiz from "../components/quiz/BrowseQuiz"
import QuizStartPage from"../components/quiz/BrowseQuiz"
import { Button, Container } from 'react-bootstrap';

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
            <button onClick={() => {history.push('/profile')} }>My Profile</button>
            <button onClick={() => {history.push('/suggestskill')} }>Suggest Skill</button>
            <button onClick={() => {history.push('/startquiz')} }>Start Skill Quiz</button>
            <button onClick={() => {history.push('/placements')} }>Search For Placements</button>
            <button onClick={() => {history.push('/createPlacement')} }>Create Placement</button>
            <Button onClick={() => {history.push('/suggestskill')} }>Suggest Skill</Button>
            <Button variant="primary" onClick={() => {history.push('/browsequiz')} }>Browse Quiz</Button>
            <Button onClick={() => {history.push('/myskills')} }>Trainee: My Skills Page</Button>
        </Container>
    )
}
export default HomePage;