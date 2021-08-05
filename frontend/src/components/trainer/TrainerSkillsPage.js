import { useEffect, useState } from "react";
import { Container, Row, Col, Tab , Button, Card, ListGroup, Form, InputGroup, FormControl, Table} from 'react-bootstrap';
import { Redirect, useHistory, useLocation } from "react-router-dom";
import CreateQuiz from './CreateQuiz';
import { ImBin } from "react-icons/im"

function TrainerSkillsPage() {
    const axios = require('axios');
    let history = useHistory();
    const [isLoading, setLoading] = useState(true);
    let updateTextbox = "";
    const [skillName, setName] = useState("");
    const [skillId, setSkillId] = useState(-5);
    const [quizId, setQuizId]  = useState(-5);
    // const [quizzes, setQuizzes] = useState([{
    //     quizId: 0,
    //     name: "",
    //     description: "",
    //     duration: 0.0,
    //     questionCount: 0,
    //     passingMark: 101.0,
    //     questions: []   
    // }])
    const [skills, setSkills] = useState([{
        quizId: 0,
        name: "",
        description: "",
        duration: 0.0,
        questionCount: 0,
        passingMark: 101.0,
        questions: []   
    }])

    
       const axiosConfig = {
            headers: { Authorization: `Bearer ${sessionStorage.jwtToken}`}
        };
    

    useEffect(() => {
        axios
        .get('http://localhost:9999/getAllSkillDTOs', axiosConfig)
        .then((response) => {
            setSkills(response.data);
            setLoading(false);
        })
        .catch((error) => {
            history.push("/*");
        })

    }, [skillName, quizId])
    

    function hasSkillLevel(skillLevels, level) {
        let flag = false;
        skillLevels.map((skillLevel) => {
            if (skillLevel.knowledgelevel == level){

                if (skillLevel.quizId == null){

                    flag = false
                } else {

                    flag = true;
                }
                
            }
        })

        return flag;
    }

    function getSkillLevelIdCreate(skillLevels, level) {
        let flag = null;
        skillLevels.map((skillLevel) => {
            if (skillLevel.knowledgelevel == level){

                if (skillLevel.quizId == null){

                    flag = skillLevel.skillLevelId;
                } else {

                    flag = null;
                }
                
            }
        })

        return flag;
    }

    function getQuizIdCreate(skillLevels, level) {
        let flag = null;
        skillLevels.map((skillLevel) => {
            if (skillLevel.knowledgelevel == level){

                flag = skillLevel.quizId;
                
                
            }
        })

        return flag;
    }

    const handleCreate = ((str) => {
        console.log("EVENT TARGET ID " + str.target.id)
        if (str.target.id == ""){
            window.alert("The SkillLevel doesnt exist");
        } else {
            history.push("/trainer/createquiz/" + str.target.id);
        }
        
        // axios
        // .get('http://localhost:9999/quiz/create/'+event.target.id, axiosConfig)
        // .then((response) => {
        //     history.push("/trainer/createquiz");
        //     // <CreateQuiz startInfo={response.data}/>
            
            
        // })
        // .catch((error) => {
        //     console.log("THERE IS ERROR")
        //     // history.push("/*");
        // })
    })

    const handleChangeUpdate = ((event) => {
        updateTextbox = event.target.value;
        setSkillId(event.target.id);
        console.log("UPDATE TEXTBOX " + updateTextbox);
        console.log("UPDATE SKILLID " + skillId);
    })

    const handleChangeId = ((event) => {
        updateTextbox = event.target.value;
        setSkillId(event.target.id)
        console.log("UPDATE TEXTBOX " + updateTextbox);
        console.log("UPDATE SKILLID " + skillId);
    })

    const submitUpdateName = (() => {
        let newSkillName = {
                                skillId: skillId, 
                                skillName: updateTextbox 
                            }
        axios
        .post('http://localhost:9999/updateSkillName', newSkillName )
        .then((response) => {
            console.log("JSON STRING " + JSON.stringify(response.data));
            setName(updateTextbox);
            if (response.data.status == "already exist"){
                window.alert("The skill name already exist!");
            }

        })
        .catch((error) => {
            console.log("ERROR MESSAGE submite update name: " + error.message)
            console.log("SkillId: " + JSON.stringify(skillId) + " SkillName: " + updateTextbox)
        })
        .finally (() => {
            document.getElementById(skillId).value = "";
        })
    })

    const deleteSkill = (() => {
        // /skill/remove/{id}
        console.log("SKILL ID BEFORE DELETE " + {id: skillId})
        axios
        .get('http://localhost:9999/skill/remove/' + skillId)
        .then((response) => {
            setSkills(response.data);
            setLoading(false);
        })
        .catch((error) => {
            window.alert("WRONG SKILL ID");
            console.log(error.message);
        })
    })

    function deleteQuiz(event) {
        console.log("QUIZ ID: " + event.target.id);
        setQuizId(event.target.id);
        console.log("QUIZ HOOK " + quizId);
        let qId = event.target.id;

        axios
        .get('http://localhost:9999/quiz/remove/'+ qId)
        .then((response) => {
            
            console.log("HELLO RESPONSE " + response.data);
            setQuizId(event.target.id);

            
        })
        .catch((error) => {
            
            console.log(error.message);
        })
    }


    // "/quiz/remove"
    
    if (isLoading) {
        return <div className="App">Loading...</div>;
    }
    

    return (
        <Container className = "d-flex justify-content-center pt-5">
            <Card className="w-75">
                <Card.Header as="h5">
                    <Row>
                        <Col sm={3} className = "pt-2">
                            Skill List
                        </Col>
                        
                        <Col className = "d-flex justify-content-end">
                            <Form className = "pt-1">
                                <Row className="align-items-center">
                                    <InputGroup className="">
                                        <FormControl
                                        placeholder="Search Skill"
                                        aria-label="Search Quiz"
                                        aria-describedby="basic-addon2"
                                        />
                                        <Button variant="outline-secondary" id="button-addon2">
                                            Search
                                        </Button>
                                    </InputGroup>
                                </Row>
                            </Form>
                        </Col>
                    </Row>
                </Card.Header>
                <Card.Body>
                        <Tab.Container id="list-group-tabs-example" defaultActiveKey={"#" + skills[0].skillId}>
                            <Row>
                                <Col sm={4}>
                                    <ListGroup>
                                        {skills.map(skill => (
                                            <ListGroup.Item onClick={(() => {setSkillId(skill.skillId); console.log("CHANGING THE SKILL ID: " + skill.skillId + " " + skillId)})} action href= {'#' + skill.skillId}>
                                                {skill.name}
                                            </ListGroup.Item>  
                                        ))}
                                    </ListGroup>
                                </Col>
                                <Col sm={8}>
                                    <Tab.Content>
                                    {console.log("ALL " + JSON.stringify(skills))}
                                        {skills.map(skill => (
                                            
                                            <Tab.Pane eventKey={'#' + skill.skillId}>
                                                <Row>
                                                    <Col sm={8}>
                                                        
                                                        <h3 >Skills Details</h3>
                                                        <Row className="pb-2">
                                                            <Col>
                                                                <h6 class="mb-2 text-muted">Skill Name: {skill.name}</h6>
                                                            </Col>
                                                        </Row>
                                                        <InputGroup className="mb-3 w-100">
                                                            <FormControl
                                                            placeholder={skill.name}
                                                            aria-describedby="basic-addon2"
                                                            onChange={handleChangeUpdate}
                                                            id={skill.skillId}
                                                            />
                                                            <Button variant="outline-secondary" onClick={submitUpdateName}>
                                                                Update
                                                            </Button>
                                                        </InputGroup>
                                                        <h3 >Quizzes</h3>
                                                        {/* <h4>Skill LEvel List {skill.skillLevels.length}</h4> */}
                                                        {/* <h4> Include Test {hasSkillLevel(skill.skillLevels, "BEGINNER")}</h4> */}
                                                        
                                                        <Table striped bordered>
                                                            <tbody>

                                                                <tr>
                                                                <td>Beginner</td>
                                                                <td>
                                                                {/* {console.log("ALL " + JSON.stringify(skill))} */}
                                                                    {hasSkillLevel(skill.skillLevels, "BEGINNER")
                                                                        ? <form id={ getQuizIdCreate(skill.skillLevels, "BEGINNER")} onSubmit={ deleteQuiz.bind(this)}></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "BEGINNER")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Intermediate</td>
                                                                <td>
                                                                    {hasSkillLevel(skill.skillLevels, "INTERMEDIATE")
                                                                        ? <form id = {getSkillLevelIdCreate(skill.skillLevels, "INTERMEDIATE")} ></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "INTERMEDIATE")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Expert</td>
                                                                <td>
                                                                    {hasSkillLevel(skill.skillLevels, "EXPERT")
                                                                        ? <form id = {getSkillLevelIdCreate(skill.skillLevels, "EXPERT")} ></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "EXPERT")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                            </tbody>
                                                        </Table>
                                                    </Col>
                                                </Row>
                                            </Tab.Pane> 
                                        ))}
                                        
    
                                    </Tab.Content>
                                </Col>
                            </Row>
                        </Tab.Container>
                </Card.Body>
            </Card>

        </Container>
    )

}
export default TrainerSkillsPage;