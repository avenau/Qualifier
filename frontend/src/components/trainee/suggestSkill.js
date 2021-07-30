import { useState } from "react";

function SuggestSkill() {

    const axios = require('axios');

    const [skill, setSkill] = useState("");
    const [skillNameError, setSkillNameError] = useState("");

    const submitSuggestedSkill = (evt) => {
        evt.preventDefault();
        if (skill.trim().length > 0) {
            setSkillNameError("");
            axios.post('http://localhost:9999/saveSuggestedSkill', { suggestedSkillId: 0, name: skill })
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
            setSkillNameError("Skill name cannot be empty");
        }   
    }

    return (
        <div>
            <form onSubmit={submitSuggestedSkill}>
                <label>Skill Name: </label>
                <input type="text" value={skill} onChange={e => setSkill(e.target.value)} required />
                <input type="submit" value="Suggest Skill" />
            </form>
            <div>{skillNameError}</div>
        </div>
    );
}

export default SuggestSkill;
