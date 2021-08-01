import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
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
                            skills: []
                        }
    const [profile, setProfile] = useState(profileTemplate);

    useEffect(() => {
        axios
        .get('http://localhost:9999/getUser', {   
            params: {
                id:1
            },
        }).then(response => {
            setProfile(response.data)
        })
        .catch(() => {});  
    });

    

    return(
        <div>
            <h1>{profile.name}</h1>
            <p>Stream: [{profile.stream}</p>
            <p>Email: {profile.email}</p>
            <p>Address: {profile.address}</p>
            <p>Phone Number: {profile.phoneNumber}</p>
            <p>City: {profile.city}</p>
            <p>Date of Birth: {profile.dob}</p>
            <button onClick={() => {history.push('/profile')} }>Edit Profile</button>
            <h1>Skills</h1>
            <button onClick={() => {history.push('/mySkills')} }>My Skills</button>
        </div>
    );

}

export default Profile;


