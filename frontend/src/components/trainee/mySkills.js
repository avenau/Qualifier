import { useEffect, useState } from "react";
import { Button, ListGroup } from "react-bootstrap";

function MySkills() {
    const axios = require('axios');

    //CHANGE THIS TO SESSION TRAINEES ID
    const traineeId = 38;

    const [pinnedSkills, setPinnedSkills] = useState([]);
    const [skills, setSkills] = useState([]);
    const [allSkills, setAllSkills] = useState([]);
    const [newSkill, setNewSkill] = useState([]);

    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        getSkillsOnLoad();
        getPinnedSkillsOnLoad();
        getAllSkillsOnLoad();
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

    function getAllSkillsOnLoad() {
        axios.post('http://localhost:9999/getAllSkills')
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

    function addSkillToTrainee(){
        axios.post('http://localhost:9999/addUnverifiedSkill'), {SkillLevel:newSkill, userId:traineeId}
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

    const allSkillsList = allSkills.map(
        (skill, index) =>
            <ListGroup.Item key={"skill-" + index}>
                {skill.skill.name}: {skill.level}
            </ListGroup.Item>
    )

    return (
        <div>
            <h1>My Skills</h1>
            <ListGroup>
                {pinnedSkillsList.length > 0 ? pinnedSkillsList : <ListGroup.Item>No Pinned Skills</ListGroup.Item>}
            </ListGroup>
            <span>{errorMessage}</span>
            <p></p>
            <ListGroup>
                {skillsList.length > 0 ? skillsList : <ListGroup.Item>No Skills</ListGroup.Item>}
            </ListGroup>
            <form onSubmit={addSkillToTrainee}> 
                <select id="allSkills" value={newSkill} name="skills" onChange={e => setNewSkill(e.target.value)}>
                    {allSkills.map(skill=>{
                 return (
                     <option value="skill">{skill.name}</option>
                 )                               
            })}
                </select>
                <input type="submit" value="Add Skill"/>
            </form>
        </div>
    );

}

export default MySkills;
