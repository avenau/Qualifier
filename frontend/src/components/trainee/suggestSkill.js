import { useState } from "react";
import { Form, Button } from "react-bootstrap";

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
        <Form onSubmit={submitSuggestedSkill}>
            <Form.Group className="mb-3">
                <Form.Label>Skill Name: </Form.Label>
                <Form.Control type="text" value={skill} onChange={e => setSkill(e.target.value)} required />
                <Form.Text>{skillNameError}</Form.Text>
            </Form.Group>
            <Button type="submit">Suggest Skill</Button>
        </Form>
    );
}

export default SuggestSkill;
