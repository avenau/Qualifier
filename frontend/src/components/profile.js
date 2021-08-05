import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { Dropdown, Button, ListGroup } from "react-bootstrap";
import axios from "axios";

function Profile() {
    const axios = require('axios');

    const axiosConfig = {
        headers: { Authorization: `Bearer ${sessionStorage.jwtToken}`}
    };

    const traineeId = sessionStorage.getItem('uId');

    let history = useHistory();
    
    const [pinnedSkills, setPinnedSkills] = useState([]);
    const [skills, setSkills] = useState([]);
    const profileTemplate = {
                            uid: 0,
                            firstname: "",
                            lastname: "",
                            stream: "",
                            email: "",
                            address:"",
                            phoneNumber:"",
                            city:"",
                            dob:"",
                            skills: [],
                            pinnedSkills: []
                        }
    const [profile, setUser] = useState(profileTemplate);

    useEffect(() => {
        getSkillsOnLoad();
        getPinnedSkillsOnLoad();
    }, []);

    useEffect(() => {
        axios
        .get('http://localhost:9999/getUser', {   
            params: {
                username:sessionStorage.getItem("username")
            },
        }).then(response => {
            setUser(response.data)
        })
        .catch(() => {});  
    }, [profile.skills.length]);

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
        axios.post('http://localhost:9999/getPinnedSkills', { userId: traineeId },axiosConfig)
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

    return(
        <div>
            <h1>{profile.firstName} {profile.lastName}</h1>
            <p>Stream: {profile.stream}</p>
            <p>Email: {profile.email}</p>
            <p>Address: {profile.address}</p>
            <p>Phone Number: {profile.phoneNumber}</p>
            <p>City: {profile.city}</p>
            <p>Date of Birth: {profile.dob}</p>
            <button onClick={() => {history.push('/profile')} }>Edit Profile</button>
            <br></br>
            <h1>Skills</h1>
            <ListGroup>
                {pinnedSkillsList.length > 0 ? pinnedSkillsList : <ListGroup.Item>No Pinned Skills</ListGroup.Item>}
            </ListGroup>
            <p></p>
            <ListGroup>
                {skillsList.length > 0 ? skillsList : <ListGroup.Item>No Skills</ListGroup.Item>}
            </ListGroup>
            <br></br>
            <button onClick={() => {history.push('/mySkills')} }>My Skills</button>
        </div>
    );

}

export default Profile;


