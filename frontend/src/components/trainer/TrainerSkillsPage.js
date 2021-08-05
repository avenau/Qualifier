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
    let skillId = -5;
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

    }, [skills.length])

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
        skillId = event.target.id
        console.log("UPDATE TEXTBOX " + updateTextbox);
    })

    const submitUpdateName = (() => {
        axios
        .post('http://localhost:9999/updateSkillName', { newSkillName:{skillId: skillId, skillName: updateTextbox }} )
        .then((response) => {
            console.log("JSON STRING " + JSON.stringify(response.data));
        })
        .catch((error) => {
            console.log("ERROR MESSAGE submite update name: " + error.message)
            console.log("SkillId: " + JSON.stringify(skillId) + " SkillName: " + updateTextbox)
        })
    })


    
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
                                            <ListGroup.Item action href= {'#' + skill.skillId}>
                                                {skill.name}
                                            </ListGroup.Item>  
                                        ))}
                                    </ListGroup>
                                </Col>
                                <Col sm={8}>
                                    <Tab.Content>
                                        {skills.map(skill => (
                                            
                                            <Tab.Pane eventKey={'#' + skill.skillId}>
                                                <Row>
                                                    <Col sm={8}>
                                                        
                                                        <h3 >Skills Details</h3>
                                                        <Row className="pb-2">
                                                            <Col>
                                                                <h6 class="mb-2 text-muted">Skill Name: {skill.name}</h6>
                                                            </Col>
                                                            <Col>
                                                                <Button variant="danger"><ImBin/></Button>
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
                                                                    {hasSkillLevel(skill.skillLevels, "BEGINNER")
                                                                        ? <form id = {getSkillLevelIdCreate(skill.skillLevels, "BEGINNER")} ><Button type = "submit"  >Delete</Button></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "BEGINNER")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Intermediate</td>
                                                                <td>
                                                                    {hasSkillLevel(skill.skillLevels, "INTERMEDIATE")
                                                                        ? <form id = {getSkillLevelIdCreate(skill.skillLevels, "INTERMEDIATE")} ><Button type = "submit"  >Delete</Button></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "INTERMEDIATE")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                <td>Expert</td>
                                                                <td>
                                                                    {hasSkillLevel(skill.skillLevels, "EXPERT")
                                                                        ? <form id = {getSkillLevelIdCreate(skill.skillLevels, "EXPERT")} ><Button type = "submit"  >Delete</Button></form>
                                                                        : <form id = {getSkillLevelIdCreate(skill.skillLevels, "EXPERT")} onSubmit={handleCreate.bind(this)}><Button type = "submit"  >Create</Button></form>
                                                                    }
                                                                </td>
                                                                </tr>
                                                            </tbody>
                                                        </Table>


                                                        {/* <Button variant="primary" onClick={(()=>{
                                                            history.push('/startquiz/' + quiz.quizId);
                                                        })}>Take Quiz</Button> */}
                                                        {/* <p class="pt-2"><strong>Time Limit: </strong>{quiz.duration} sec<br/>
                                                        <strong>No. Questions: </strong>{quiz.questionCount}<br/>
                                                        <strong>Pass Mark: </strong>{quiz.passingMark}% */}
                                                        {/* </p> */}
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