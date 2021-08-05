import { useEffect, useState } from "react";
import { Dropdown, Button, ListGroup, Container, Row, Col } from "react-bootstrap";
import { useHistory } from "react-router-dom";

function MySkills() {
    const axios = require('axios');

    const traineeId = sessionStorage.getItem('uId');

    const history = useHistory();

    const [pinnedSkills, setPinnedSkills] = useState([]);
    const [skills, setSkills] = useState([]);
    const [allSkills, setAllSkills] = useState([]);
    const [newSkill, setNewSkill] = useState([]);

    const [errorMessage, setErrorMessage] = useState("");
    const [cannotAddSkillErrorMessage, setCannotAddSkillErrorMessage] = useState("");

    const axiosConfig = {
        headers: { Authorization: `Bearer ${sessionStorage.jwtToken}` }
    };


    useEffect(() => {
        getSkillsOnLoad();
        getPinnedSkillsOnLoad();
        getAllSkillsOnLoad();

        setCannotAddSkillErrorMessage("");
        setErrorMessage("");
    }, []);

    function getSkillsOnLoad() {
        axios.post('http://localhost:9999/getSkills', { userId: traineeId }, axiosConfig)
            .then(function (response) {
                console.log("ID " + traineeId)
                setSkills(response.data);
                console.log(skills);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    function getPinnedSkillsOnLoad() {
        axios.post('http://localhost:9999/getPinnedSkills', { userId: traineeId }, axiosConfig)
            .then(function (response) {
                console.log(response);
                setPinnedSkills(response.data);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })

    }

    function getAllSkillsOnLoad() {
        axios.get('http://localhost:9999/getAllSkills')
            .then(function (response) {
                console.log(response);
                setAllSkills(response.data);
                console.log(allSkills);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    function addSkillToTrainee() {
        axios.post('http://localhost:9999/addUnverifiedSkill', { SkillLevel: newSkill, userId: traineeId })
            .then(function (response) {
                console.log(response);
                setNewSkill(response.data);
                console.log(newSkill);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    const unpinSkill = (index) => {
        axios.post('http://localhost:9999/unpinSkill', [traineeId, pinnedSkills[index].skillLevelId])
            .then(function (response) {
                console.log(response);
                setErrorMessage(response.data);

                if (errorMessage.length <= 0) {
                    let tempSkills = skills.slice();
                    let tempPinnedSkills = pinnedSkills.slice();

                    tempSkills.push(pinnedSkills[index]);
                    tempPinnedSkills.splice(index, 1);

                    setSkills(tempSkills);
                    setPinnedSkills(tempPinnedSkills);
                }
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })

    };

    const addUnverifiedSkill = (index) => {
        axios.post('http://localhost:9999/addUnverifiedSkill', [traineeId, allSkills[index].skillId])
            .then(function (response) {
                console.log("addUnverifiedSkill");
                console.log(response);
                if (response.data.skill != undefined) {
                    setCannotAddSkillErrorMessage("");
                    let addedSkills = skills.slice();
                    addedSkills.push(response.data);
                    setSkills(addedSkills);
                } else {
                    setCannotAddSkillErrorMessage("Cannot add skill: You already have a similar skill");
                }
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    }

    const pinnedSkillsList = pinnedSkills.map(
        (skill, index) =>
            <ListGroup.Item key={"pinnedSkill-" + index}>
                <Row className="align-items-center">
                    <Col sm="auto">
                        <Button onClick={() => unpinSkill(index)}>UNPIN</Button>
                    </Col>
                    <Col sm>
                        {skill.skill.name}: {skill.level}
                    </Col>
                </Row>
            </ListGroup.Item>
    );

    const pinSkill = (index) => {
        axios.post('http://localhost:9999/pinSkill', [traineeId, skills[index].skillLevelId])
            .then(function (response) {
                console.log(response);
                setErrorMessage(response.data);
                if (response.data.length <= 0) {
                    let tempSkills = skills.slice();
                    let tempPinnedSkills = pinnedSkills.slice();

                    tempPinnedSkills.push(skills[index]);
                    tempSkills.splice(index, 1);

                    setPinnedSkills(tempPinnedSkills);
                    setSkills(tempSkills);

                }
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })

    };

    const removeSkill = (index) => {
        axios.post('http://localhost:9999/removeTraineeSkill', [traineeId, skills[index].skillLevelId])
            .then(function (response) {
                console.log(response);
                let updateSkills = skills.slice();
                updateSkills.splice(index, 1);
                setSkills(updateSkills);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    const skillsList = skills.map(
        (skill, index) =>
            <ListGroup.Item key={"skill-" + index}>
                <Row className="align-items-center">
                    <Col sm="auto">
                        <Button onClick={() => removeSkill(index)}>REMOVE</Button>
                    </Col>
                    <Col sm="auto">
                        <Button onClick={() => pinSkill(index)}>PIN</Button>
                    </Col>
                    <Col sm>
                        {skill.skill.name}: {skill.level}
                    </Col>
                </Row>
            </ListGroup.Item>
    );

    const allSkillsList = allSkills.map(
        (skill, index) =>
            <Dropdown.Item key={"skill-" + index} onSelect={() => addUnverifiedSkill(index)}>
                {skill.name}
            </Dropdown.Item>
    );

    return (
        <Container>
            <Row>
                <h1>My Skills</h1>
                <ListGroup>
                    {pinnedSkillsList.length > 0 ? pinnedSkillsList : <ListGroup.Item>No Pinned Skills</ListGroup.Item>}
                </ListGroup>
                <p></p>
                <span>{errorMessage}</span>
                <ListGroup>
                    {skillsList.length > 0 ? skillsList : <ListGroup.Item>No Skills</ListGroup.Item>}
                </ListGroup>
            </Row>

            <Row className="mt-4">
                <Col sm>
                    <Button variant="secondary" onClick={() => history.goBack()}>Back</Button>
                </Col>
                <Col sm="auto">
                    <Dropdown>
                        <Dropdown.Toggle variant="success" id="dropdown-basic">
                            Add Skill
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            {allSkillsList}
                        </Dropdown.Menu>
                    </Dropdown>
                </Col>
            </Row>

            <Row>
                <div>{cannotAddSkillErrorMessage}</div>
            </Row>
        </Container>
    );

}

export default MySkills;
