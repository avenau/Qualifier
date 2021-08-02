import { useState } from "react";
import SuggestSkill from 'suggestSkill';


function MySkills() {
    let history = useHistory();
    const axios = require('axios');

    const addUnverifiedSkill = (evt) => {
        evt.preventDefault();
        if (skill.trim().length > 0) {
            setSkillNameError("");
            axios.post('http://localhost:9999/addUnverifiedSkill', { skillId: 0, name: skill })
                .then(function (response) {
                    console.log(response);
                    setSkillNameError(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                })
                .then(function () {
                    console.log('finally');
                })
        } else {
            setSkillNameError("Please select a skill to add");
        }   
    }

    return (
        <div>
            <h1>My Skills</h1>
            <form onSubmit={addUnverifiedSkill}>
            <label for="skill">Skills</label>
                <select id="skill" name="Skills">
                    <option value="skill">Fetch Skills</option>
                </select>
                <input type="submit" value="Add Skill"></input>
            </form>
            <SuggestSkill></SuggestSkill>
        </div>
    );
}

export default MySkills;
