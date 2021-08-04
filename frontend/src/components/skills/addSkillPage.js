import { useState, useEffect } from "react";
import { InputGroup, FormControl, Button, Container, Card, Row, Alert } from 'react-bootstrap';

function AddSkillPage() {

    const axios = require('axios');
    const [skill, setSkill] = useState("");
    const [show, setShow] = useState(false);
    const [alertColor, setAlertColor] = useState("danger");
    const [alertMessage, setAlertMessage] = useState("already exist!")
    useEffect(() => {

    })

    const addingSkill = (() => {
        axios
        .post('http://localhost:9999/addSkill', skill)   
        .then((response) => {
            let status = response.data.status;
            console.log(status);
            if (status =="already exist"){
                console.log("ALRWEADYT");
                setShow(true);
                setAlertMessage(<p><strong>{skill}</strong> already exist!</p>);
                setAlertColor("danger");
            } else if (status == "success"){
                setShow(true);
                setAlertMessage(<p><strong>{skill}</strong> successfully added!</p>);
                setAlertColor("success");
            }
        })
        .catch()
        .finally()

    })

    const textBoxOnChange = ((event) => {
        setSkill(event.target.value);
    })


    return(
        <Container className="d-flex justify-content-center">
            
            <Row className="d-flex justify-content-center">
                <Alert show= {show} variant={alertColor} onClose={() => setShow(false)} dismissible>
                     {alertMessage}
                </Alert>
                <InputGroup className="mb-3 pt-5 w-50">
                    <FormControl
                    placeholder="Enter Skill"
                    aria-label="Adding New Skill"
                    aria-describedby="basic-addon2"
                    onChange={textBoxOnChange}
                    />
                    <Button variant="outline-secondary" onClick={addingSkill}>
                        Add Skill
                    </Button>
                </InputGroup>

                <Card>
                    <Card.Header as="h5">Suggested Skills</Card.Header>
                    <Card.Body>
                        <Card.Title>Special title treatment</Card.Title>
                        <Card.Text>
                        With supporting text below as a natural lead-in to additional content.
                        </Card.Text>
                        <Button variant="primary">Go somewhere</Button>
                    </Card.Body>
                </Card>
            </Row>
        </Container>
    )

}
export default AddSkillPage;

//dd