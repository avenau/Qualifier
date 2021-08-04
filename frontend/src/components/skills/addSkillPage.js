import { useState, useEffect } from "react";
import { InputGroup, FormControl, Button, Container, Card, Row, Alert, Col, ListGroup } from 'react-bootstrap';
import { FiCheck, FiX } from "react-icons/fi";

function AddSkillPage() {

    const axios = require('axios');
    const [skill, setSkill] = useState("");
    const [show, setShow] = useState(false);
    const [alertColor, setAlertColor] = useState("danger");
    const [alertMessage, setAlertMessage] = useState("already exist!")
    const [suggestedSkills, setSuggestedSkills] = useState([]);
    //const [isLoaded, setLoaded] = false;
    useEffect(() => {
        console.log("USING EFFECT!");
        axios
        .get('http://localhost:9999/getAllSuggestedSkills')
        .then((response) => {
            setSuggestedSkills(response.data);
            //setLoaded(true);
        })
        .catch((error) => {

        })
        console.log("Skill Length " + suggestedSkills.length);
    }, [suggestedSkills.length])

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
        .finally(() =>{
            document.getElementById("addSkillSearch").value = "";
        })

    })

    const acceptSuggestedSkill = ((event) =>{
        let name = event.target.id;


        setSuggestedSkills(suggestedSkills.splice(suggestedSkills.indexOf(name, 1)));

        
        const addSkill = axios
        .post('http://localhost:9999/addSkill', name)   
        .then((response) => {
            let status = response.data.status;
            console.log("Status " + status);
            if (status =="already exist"){

                setShow(true);
                setAlertMessage(<p><strong>{name}</strong> already exist!</p>);
                setAlertColor("danger");
            } else if (status == "success"){
                setShow(true);
                setAlertMessage(<p><strong>{name}</strong> successfully added!</p>);
                setAlertColor("success");
            }

        })
        .catch()
        .finally()

        const removeSkill = axios
        .post('http://localhost:9999/removeSuggestedSkill', name)   
        .then((response) => {

        })
        .catch(() => {
            
        })
        .finally(() => {
            
        })


        Promise.all([addSkill, removeSkill])
        .then((response) => {
            console.log("ALL PROMISE " + response);

        })
    })
    const declineSuggestedSkill = ((event) =>{
        let name = event.target.id;


        setSuggestedSkills(suggestedSkills.splice(suggestedSkills.indexOf(name, 1)));

        const removeSkill = axios
        .post('http://localhost:9999/removeSuggestedSkill', name)   


        Promise.all([removeSkill])
        .then((response) => {
            console.log("ALL PROMISE " + response);
            setShow(true);
            setAlertMessage(<p>Successfully declined <strong>{name}</strong>!</p>);
            setAlertColor("success");

        })

    })

    const textBoxOnChange = ((event) => {
        setSkill(event.target.value);
    })
    // if (isLoaded === false){
    //     return (
    //         <h1>Loading</h1>
    //     )
    // }


    return(
        <Container>
            
            <Row >
                <Alert show= {show} variant={alertColor} onClose={() => setShow(false)} dismissible>
                     {alertMessage}
                </Alert>
                <Col className="d-flex justify-content-center">
                    <InputGroup className="mb-3 pt-5 w-50">
                        <FormControl
                        placeholder="Enter Skill"
                        aria-label="Adding New Skill"
                        aria-describedby="basic-addon2"
                        onChange={textBoxOnChange}
                        id="addSkillSearch"
                        />
                        <Button variant="outline-secondary" onClick={addingSkill}>
                            Add Skill
                        </Button>
                    </InputGroup>
                </Col>
            </Row>
            <Row >
                <Col className="d-flex justify-content-center">
                    <Card className="w-50">
                        <Card.Header as="h5">Suggested Skills</Card.Header>
                        
                        <ListGroup>
                            {suggestedSkills.map((skill) => (
                                <ListGroup.Item>
                                <Row>
                                    <Col>{skill.name} </Col>
                                    <Col xs={1}>
                                        <form id={skill.name} onSubmit={acceptSuggestedSkill}>
                                            <Button variant="success" type="submit"><FiCheck/></Button>{' '} 
                                        </form>
                                    </Col>
                                    <Col xs={2}>
                                        <form id={skill.name} onSubmit={declineSuggestedSkill}>
                                            <Button variant="danger" type="submit"><FiX/></Button>{' '}
                                        </form>
                                    </Col>
                                </Row>
                                
                                </ListGroup.Item>
                            ))}
                            
                        </ListGroup>
                    </Card>
                </Col>
            </Row>
        </Container>
    )

}
export default AddSkillPage;

//dd