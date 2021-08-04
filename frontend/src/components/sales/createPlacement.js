import { useEffect, useState } from "react";
import { Dropdown, Button, ListGroup } from "react-bootstrap";

function CreatePlacement(){

    const axios = require('axios');

    const [allClients, setAllClients] = useState([]);
    const [allSkills, setAllSkills] = useState([]);

    const [placementName, setName] = useState("");
    const [placementDescription, setDescription] = useState("");
    const [placementLocation, setLocation] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [clientName, setClientName] = useState("");
    const [placementSkills, setPlacementSkill] = useState("")

    useEffect(() => {
        getAllClientsOnLoad();
        getAllSkillsOnLoad();
    }, []);


    const submitPlacement = (evt) => {
        evt.preventDefault();
        
        console.log(clientName);
        console.log(placementSkills);
        axios.post('http://localhost:9999/savePlacement', {placementId:1, name:placementName, startDate:startDate, completionDate:endDate, 
                                                                    description:placementDescription, location:placementLocation,
                                                                    client:clientName, skills:placementSkills})
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

    function getAllClientsOnLoad() {
        axios.get('http://localhost:9999/getAllClients')
            .then(function (response) {
                console.log(response);
                setAllClients(response.data);
                console.log(allClients);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    function getAllSkillsOnLoad() {
        axios.get('http://localhost:9999/getAllSkills')
            .then(function (response) {
                console.log(response);
                setAllSkills(response.data);
                console.log(allSkills);
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                console.log('finally');
            })
    };

    const handleStartDateNameChange = (event) => {
    this.setStartDate(event.target.value);
    this.setEndDate(event.target.value);
  };

  const allClientsList = allClients.map(
    (client,index)=>
        <Dropdown.Item key={client} value={clientName} onSelect={e => setClientName(e.target.value)}>
            {client.name}
        </Dropdown.Item>                             
  );

  const allSkillsList = allSkills.map(
        (skill,index)=>
            <Dropdown.Item key={"skill-" + index} value={placementSkills} onSelect={e => setPlacementSkill(e.target.value)}>
                {skill.name}
            </Dropdown.Item>                             
      );

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
                <Dropdown>
                    <Dropdown.Toggle variant="success" id="dropdown-basic">
                        Select Client
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        {allClientsList}
                    </Dropdown.Menu>
                </Dropdown>
                <Dropdown>
                    <Dropdown.Toggle variant="success" id="dropdown-basic">
                        Select Skills
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        {allSkillsList}
                    </Dropdown.Menu>
                </Dropdown>
                <input type="submit" value="Add Placement"/>
            </form>
        </div>
    )
}
export default CreatePlacement;
