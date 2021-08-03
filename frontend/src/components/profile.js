import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { getUser } from "../utils/Auth"
import axios from "axios";

function Profile() {
    let history = useHistory();
    const axios = require('axios');
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

    

    return(
        <div>
            <h1>{profile.name}</h1>
            <p>Stream: {profile.stream}</p>
            <p>Email: {profile.email}</p>
            <p>Address: {profile.address}</p>
            <p>Phone Number: {profile.phoneNumber}</p>
            <p>City: {profile.city}</p>
            <p>Date of Birth: {profile.dob}</p>
            <button onClick={() => {history.push('/profile')} }>Edit Profile</button>
            <h1>Skills</h1>
            {profile.pinnedSkills.map(skillLevel =>{
                 return (
                     <p>{skillLevel.skill.name}</p>
                 )                               
            })}
            <p> -------------- </p>
            {profile.skills.map(skillLevel =>{
                 return (
                     <p>{skillLevel.skill.name}</p>
                 )                               
            })}
            <button onClick={() => {history.push('/mySkills')} }>My Skills</button>
        </div>
    );

}

export default Profile;


