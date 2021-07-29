import { useState } from "react";

function SuggestSkill() {

    const axios = require('axios');

    const [skill, setSkill] = useState("");

    const submitSuggestedSkill = (evt) => {
        evt.preventDefault();
        axios.post('http://localhost:9999/saveSuggestedSkill', {suggestedSkillId : 1, name : skill})
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    }

    return (
        <div>
            <form onSubmit={submitSuggestedSkill}>
                <label>Skill: </label>
                <input type="text" value={skill} onChange={e => setSkill(e.target.value)} />
                <input type="submit" value="Suggest Skill" />
            </form>

        </div>
    );
}

export default SuggestSkill;
