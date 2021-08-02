import { useEffect, useState } from "react";
import { Button, ListGroup } from "react-bootstrap";

function MySkills() {
    const axios = require('axios');

    //CHANGE THIS TO SESSION TRAINEES ID
    const traineeId = 31;

    const [pinnedSkills, setPinnedSkills] = useState([]);
    const [skills, setSkills] = useState([]);

    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        getSkillsOnLoad();
        getPinnedSkillsOnLoad();
    }, []);

    function getSkillsOnLoad() {
        axios.post('http://localhost:9999/getSkills', { userId: traineeId })
            .then(function (response) {
                console.log(response);
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
        axios.post('http://localhost:9999/getPinnedSkills', { userId: traineeId })
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

    const pinnedSkillsList = pinnedSkills.map(
        (skill, index) =>
            <ListGroup.Item key={"pinnedSkill-" + index}>
                <Button onClick={() => unpinSkill(index)}>UNPIN</Button>
                {skill.skill.name}: {skill.level}
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

    const skillsList = skills.map(
        (skill, index) =>
            <ListGroup.Item key={"skill-" + index}>
                <Button onClick={() => pinSkill(index)}>PIN</Button>
                {skill.skill.name}: {skill.level}
            </ListGroup.Item>
    );

    return (
        <div>
            <h1>Pinned Skills</h1>
            <ListGroup>
                {pinnedSkillsList.length > 0 ? pinnedSkillsList : <ListGroup.Item>No Pinned Skills</ListGroup.Item>}
            </ListGroup>
            <span>{errorMessage}</span>
            <h1>My Skills</h1>
            <ListGroup>
                {skillsList.length > 0 ? skillsList : <ListGroup.Item>No Skills</ListGroup.Item>}
            </ListGroup>
        </div>
    );

}

export default MySkills;
