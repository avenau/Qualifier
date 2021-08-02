import { useState } from "react";

function CreatePlacement(){

    const axios = require('axios');

    const [placementName, setName] = useState("");
    const [placementDescription, setDescription] = useState("");
    const [placementLocation, setLocation] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const submitPlacement = (evt) => {
        evt.preventDefault();
        var skills = new FormData();
        skills.append('skillsNeeded', '');
        var client = new FormData();
        client.append('client',' ');
        console.log(startDate);
        axios.post('http://localhost:9999/savePlacement', {placementId:1, name:placementName, startDate:startDate, completionDate:endDate, 
                                                                    description:placementDescription, location:placementLocation,
                                                                    client, skills})
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

    const handleStartDateNameChange = (event) => {
    this.setStartDate(event.target.value);
    this.setEndDate(event.target.value);
  };

    return(
        <div>
            <form onSubmit={submitPlacement}>
                <label>Placement Title</label>
                <input type="text" value={placementName} onChange={e => setName(e.target.value)} />
                <label>Description</label>
                <input type="text" value={placementDescription} onChange={e => setDescription(e.target.value)}/>
                <label> Location </label>
                <input type="text" value={placementLocation} onChange={e => setLocation(e.target.value)}/>
                <label>Start Date</label>
                <input type="date" value={startDate} onChange={e => setStartDate(e.target.value)}/>
                <label>End Date</label>
                <input type="date" value={endDate} onChange={e => setEndDate(e.target.value)}/>
                <label for="client">Client</label>
                <select id="client" name="Client">
                    <option value="client">Fetch Clients</option>
                </select>
                {/* List of skills WIP
                <label>Skills</label>   
                <input type="checkbox" id="skill1" name="skill1" value="Skill1"/>
                <label for="skill1"> Skill #1</label>
                <br/>
                <input type="checkbox" id="skill2" name="skill2" value="Skill2"/>
                <label for="skill2"> Skill #2</label> */}
                <input type="submit" value="Add Placement"/>
            </form>
        </div>
    )
}
export default CreatePlacement;
