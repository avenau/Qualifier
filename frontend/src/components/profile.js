import { useState, useEffect } from "react";
import axios from "axios";

function Profile() {

    const axios = require('axios');
    const profileTemplate = {
                            uid: 0,
                            name: "",
                            stream: "",
                            email: "",
                            address:"",
                            phonenumber:"",
                            city:"",
                            dob:"",
                            skills: []
                        }
    const [profile, setProfile] = useState(profileTemplate);

    useEffect(() => {
        axios
        .get('http://localhost:9999/getProfile', {   
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
            <p>Stream: [Stream]</p>
            <p>Email: [Email]</p>
            <p>Address: [Address]</p>
            <p>Phone Number: [Phone Number]</p>
            <p>City: [City]</p>
            <p>Date of Birth: [DOB]</p>
            <h1>Skills</h1>
        </div>
    );

}

export default Profile;


