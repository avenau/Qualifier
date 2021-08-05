import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { Dropdown, Button, ListGroup, Container } from "react-bootstrap";
import axios from "axios";
import TraineeResults from "./trainer/traineeResults";

function Profile() {
    const axios = require('axios');

    const axiosConfig = {
        headers: { Authorization: `Bearer ${sessionStorage.jwtToken}` }
    };

    const traineeId = sessionStorage.getItem('uId');
    const accountType = sessionStorage.getItem('accountType');

    let history = useHistory();

    const [pinnedSkills, setPinnedSkills] = useState([]);
    const [skills, setSkills] = useState([]);
    const profileTemplate = {
        uid: 0,
        firstname: "",
        lastname: "",
        stream: "",
        email: "",
        address: "",
        phoneNumber: "",
        city: "",
        dob: "",
        skills: [],
        pinnedSkills: []
    }
    const [profile, setUser] = useState(profileTemplate);

    useEffect(() => {
        getSkillsOnLoad();
        getPinnedSkillsOnLoad();
        getUserOnLoad();
    }, []);

    // useEffect(() => {
    //     axios
    //     .get('http://localhost:9999/getUser', {   
    //         params: {
    //             username:sessionStorage.getItem("username")
    //         },
    //     }).then(response => {
    //         setUser(response.data)
    //     })
    //     .catch(() => {});  
    // }, [profile.skills.length]);

    function getUserOnLoad() {
        axios
            .get('http://localhost:9999/getUser', {
                params: {
                    username: sessionStorage.getItem("username")
                },
            }).then(response => {
                setUser(response.data)
            })
            .catch(() => { });
    }

    function getSkillsOnLoad() {
        axios.post('http://localhost:9999/getSkills', [traineeId], axiosConfig)
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
        axios.post('http://localhost:9999/getPinnedSkills', [traineeId], axiosConfig)
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

    const skillsList = skills.map(
        (skill, index) =>
            <ListGroup.Item key={"skill-" + index}>
                {skill.skill.name}: {skill.level}
            </ListGroup.Item>
    );

    const pinnedSkillsList = pinnedSkills.map(
        (skill, index) =>
            <ListGroup.Item key={"pinnedSkill-" + index}>
                {skill.skill.name}: {skill.level}
            </ListGroup.Item>
    );

    return (
        <div>
            <Container>
                <h1>{profile.firstName} {profile.lastName}</h1>
                <ListGroup>
                    <ListGroup.Item>
                        <p>Stream: {profile.stream}</p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <p>Email: {profile.email}</p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <p>Address: {profile.address}</p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <p>Phone Number: {profile.phoneNumber}</p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <p>City: {profile.city}</p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <p>Date of Birth: {profile.dob}</p>
                    </ListGroup.Item>
                </ListGroup>
                <Button className="mt-4" onClick={() => { history.push('/profile') }}>Edit Profile</Button>
            </Container>
            <Container className="mt-4">
                <h1>Skills</h1>
                <ListGroup>
                    {pinnedSkillsList.length > 0 ? pinnedSkillsList : <ListGroup.Item>No Pinned Skills</ListGroup.Item>}
                </ListGroup>
                <p></p>
                <ListGroup>
                    {skillsList.length > 0 ? skillsList : <ListGroup.Item>No Skills</ListGroup.Item>}
                </ListGroup>
                <br></br>
                <Button variant="secondary" onClick={() => { history.push('/mySkills') }}>My Skills</Button>
                {accountType == "trainee" ?
                    <TraineeResults traineeId={traineeId}></TraineeResults>
                    : <span></span>}
            </Container>
        </div>
    );

}

export default Profile;


