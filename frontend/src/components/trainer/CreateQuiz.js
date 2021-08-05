
import { Redirect, useHistory, useLocation } from "react-router-dom";
import {browserHistory} from 'react-router'
import { useEffect, useState } from "react";
import {Modal, Container, Button, Form, Col, Row, InputGroup, FormControl, Card, Table} from 'react-bootstrap';
function CreateQuiz() {
    const history = useHistory();
    const axios = require('axios');
    const skillLevelId = useLocation().pathname.split("/")[3];
    const [isLoading, setLoading] = useState(true);
    const [startHook, setStartHook] = useState({
                                          "quizId": 132,
                                          "name": "",
                                          "description": "",
                                          "duration": 0.0,
                                          "questionCount": 0,
                                          "passingMark": 0.0,
                                          "questions": []
                                      });
    const [hookQuestions, setQuestions] = useState([])
    
    console.log("SKILL LEVEL " + skillLevelId);
    // let startInfo = {
    //                         "quizId": 132,
    //                         "name": "",
    //                         "description": "",
    //                         "duration": 0.0,
    //                         "questionCount": 0,
    //                         "passingMark": 0.0,
    //                         "questions": []
    //                     }

    useEffect (() => {
        axios
        .get('http://localhost:9999/quiz/create/'+ skillLevelId)
        .then((response) => {
            setStartHook(response.data);
            setQuestions(response.data.questions);
            setLoading(false);
            console.log("RUNNING");

            
        })
        .catch((error) => {
            console.log("THERE IS ERROR")
            console.log("============ERROR")
            console.log('Error', error.message);
            window.alert("error");
            // history.push("/*");

        })
        .finally(() => {
          console.log("START HOOK " + startHook.duration);
        })
    }, [])


    useEffect (() => {
      console.log("REFRESHING the LENGTH " + startHook.questions.length);
    }, [startHook.questions])

    const submitQuiz = (() => {
        let finalQuiz = startHook;
        finalQuiz.questions = hookQuestions;
        console.log(JSON.stringify(finalQuiz));
        // /createNewQuiz
        axios
        .post('http://localhost:9999/createNewQuiz', finalQuiz)   
        .then((response) => {
            let status = response.data.status;
            console.log(status);
            history.push({
               pathname: '/trainer/finishquiz',
               state: { detail: finalQuiz }
           });
 
          console.log("FINISH HISTORY PUSH");
        })
        .catch( (error) => {
            console.log(error.message)
            window.alert("Quiz not submitted!")
        })

    })

    
    if (isLoading) {
      return <div className="App">Loading...</div>;
    }


    function AddMultipleSelect() {
      const [show, setShow] = useState(false);
    
      const handleClose = () => setShow(false);
      const handleShow = () => setShow(true);
      let shortQuestion = {
        questionContent: "",
        questionType: "MULTI_SELECT",
        questionPoints: "",
        answers: []
      }
      let ans1 = {
        content : "",
        correct : false
      }

      let ans2 = {
        content : "",
        correct : false
      }

      let ans3 = {
        content : "",
        correct : false
      }

      let ans4 = {
        content : "",
        correct : false
      }

      const addQuestion = (() => {
        handleClose();
        console.log("LENGTH BEGINNING " + startHook.questions.length)
        
        const allAnswers = [ans1, ans2, ans3, ans4];
        shortQuestion.answers = allAnswers;
        
        setQuestions((prevState) => [...prevState, shortQuestion]);

        console.log("JSON STRING " + JSON.stringify(hookQuestions));
        
      })

    
      return (
        <>
          <Button variant="primary" onClick={handleShow}>
            Add Multiple Select
          </Button>{' '}
    
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Multiple Select</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form hasValidation onSubmit={addQuestion}>
                <Form.Group className="mb-3" controlId="formGridAddress1" >
                  
                  <Form.Control className="mb-2" onChange={((event) => {shortQuestion.questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                  <Form.Control className="mb-2" onChange={((event) => {shortQuestion.questionPoints = event.target.value})}  type="number" rows={1} placeholder="Mark" />          
                  <InputGroup className="mb-2">
                    <InputGroup.Checkbox onChange = {((event) => {if (ans1.correct === true) { ans1.correct = false} else {ans1.correct = true}})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans1.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-2">
                  <InputGroup.Checkbox onChange = {((event) => {if (ans2.correct === true) { ans2.correct = false} else {ans2.correct = true}})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans2.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-2">
                  <InputGroup.Checkbox onChange = {((event) => {if (ans3.correct === true) { ans3.correct = false} else {ans3.correct = true}})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans3.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-2">
                  <InputGroup.Checkbox onChange = {((event) => {if (ans4.correct === true) { ans4.correct = false} else {ans4.correct = true}})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans4.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                </Form.Group>
                <Button variant="secondary" onClick={handleClose}>
                  Close
                </Button>
                <Button variant="primary" type="submit">
                  Save Changes
                </Button>
              </Form>
            </Modal.Body>
            <Modal.Footer>

            </Modal.Footer>
          </Modal>
        </>
      );
    }
                  


      function AddMultipleChoice() {
        const [show, setShow] = useState(false);
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);
        let shortQuestion = {
          questionContent: "",
          questionType: "MULTIPLE_CHOICE",
          questionPoints: "",
          answers: []
        }
        let ans1 = {
          content : "",
          correct : false
        }

        let ans2 = {
          content : "",
          correct : false
        }

        let ans3 = {
          content : "",
          correct : false
        }

        let ans4 = {
          content : "",
          correct : false
        }

        const addQuestion = (() => {
          handleClose();
          console.log("LENGTH BEGINNING " + startHook.questions.length)
          
          const allAnswers = [ans1, ans2, ans3, ans4];
          shortQuestion.answers = allAnswers;
          
          setQuestions((prevState) => [...prevState, shortQuestion]);

          console.log("JSON STRING " + JSON.stringify(hookQuestions));
          
        })

      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Multiple Choice
            </Button>{' '}
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Multiple Choice</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Form hasValidation onSubmit={addQuestion}>
                  <Form.Group className="mb-2" controlId="formGridAddress1" >
                    
                    <Form.Control className="mb-2" onChange={((event) => {shortQuestion.questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                    <Form.Control className="mb-2" onChange={((event) => {shortQuestion.questionPoints = event.target.value})}  type="number" rows={1} placeholder="Mark" />          
                    <InputGroup className="mb-2">
                      <InputGroup.Radio onChange = {((event) => {ans1.correct = true; ans2.correct = false; ans3.correct = false; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans1.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-2">
                    <InputGroup.Radio onChange = {((event) => {ans1.correct = false; ans2.correct = true; ans3.correct = false; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans2.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-2">
                    <InputGroup.Radio onChange = {((event) => {ans1.correct = false; ans2.correct = false; ans3.correct = true; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans3.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-2">
                    <InputGroup.Radio onChange = {((event) => {ans1.correct = false; ans2.correct = false; ans3.correct = false; ans4.correct = true})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans4.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                  </Form.Group>
                  <Button variant="secondary" onClick={handleClose}>
                    Close
                  </Button>
                  <Button variant="primary" type="submit">
                    Save Changes
                  </Button>
                </Form>
              </Modal.Body>
              <Modal.Footer>

              </Modal.Footer>
            </Modal>
          </>
        );
      }

      function AddShortAnswer() {
        const [show, setShow] = useState(false);
        let questionContent = "";
        let questionPoints = 0;
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);

        const addQuestion = (() => {
          handleClose();
          console.log("LENGTH BEGINNING " + startHook.questions.length)
          let shortQuestion = {
                                questionContent: questionContent,
                                questionType: "SHORT_ANSWER",
                                questionPoints: questionPoints,
                                answers: [
                                            {answerId: 19,
                                            content: "Short Answer",
                                            correct: true}
                                          ]
                              }
          
          setQuestions((prevState) => [...prevState, shortQuestion]);

          console.log("JSON STRING " + JSON.stringify(hookQuestions));
          
        })
      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Short Answer
            </Button>{' '}
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Short Answer</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Form.Group className="mb-3" controlId="formGridAddress1">
                
                      <Form.Control className="mb-2" onChange={((event) => {questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                      <Form.Control onChange={((event) => {questionPoints = event.target.value})}  type="number" rows={1} placeholder="Mark" />
                </Form.Group>
              </Modal.Body>
              <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                  Close
                </Button>
                <Button variant="primary" onClick={addQuestion}>
                  Save Changes
                </Button>
              </Modal.Footer>
            </Modal>
          </>
        );
      }


      function DetailForm () {
          return (
            <Form className= "pt-2">
                <Row className="mb-3">
                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.name = event.target.value})} type="name" placeholder="Name" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.duration = event.target.value})}  type="number" placeholder="Duration" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.passingMark = event.target.value})} type="number" placeholder="Passing Mark" />
                    </Form.Group>


                </Row>

                <Form.Group className="mb-3" controlId="formGridAddress1">
                    <Form.Control onChange={((event) => {startHook.description = event.target.value})}  as="textarea" rows={3} placeholder="Description" />
                </Form.Group>
                {console.log("SECOND JSON " + JSON.stringify(hookQuestions))}

                <AddedQuestions/>

                <Button variant="primary" onClick={submitQuiz}>
                    Submit
                </Button>
            </Form>
            
          )
      }

      const AddedQuestions = (() => {
        return(
          <div>
          {hookQuestions.map ((question) => (
                            //  questionContent: questionContent,
                            //  questionType: "SHORT_ANSWER",
                            //  questionMark: questionMark,
                            //  answers: []
            <Card>
              <Card.Header as="h5">{question.questionContent}</Card.Header>
              <Card.Body>
                <Card.Subtitle className="mb-2 text-muted">Type: {question.questionType} </Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted">Mark: {question.questionPoints}</Card.Subtitle>
                <Card.Text>
                <Card.Subtitle className="mb-2 text-muted">Answers:</Card.Subtitle>
                <Table striped bordered>
                  <thead>
                    <tr>
                      <th>Answer</th>
                      <th>Correct</th>
                    </tr>
                  </thead>
                  
                  {question.answers.map((answer) => (
                    
                    <tbody>
                      <tr>
                        <td>{answer.content}</td>
                        <td>{JSON.stringify(answer.correct)}</td>
                      </tr>
                    </tbody>

                  ))}
                </Table>
                </Card.Text>
              </Card.Body>
            </Card>


            ))}</div>
        )
      })




    return (
        <Container>
              <h3 className="pt-3">Quiz Editor</h3>
              {/* <Row>
                <Col><AddMultipleChoice/></Col>
                <Col><AddMultipleSelect /></Col>
                <Col><AddShortAnswer /></Col>

              </Row> */}
              <AddMultipleChoice/>
              <AddMultipleSelect />
              <AddShortAnswer />

            <DetailForm/>


            
        </Container>
        
    )
}
export default CreateQuiz;

/*
{
    "quizId": 42,
    "name": null,
    "description": null,
    "duration": 0.0,
    "questionCount": 0,
    "passingMark": 0.0,
    "questions": [
                    {
                      "questionContent":"1/10a McEvoy Rd Padstow",
                      "questionType":"MULTIPLE_CHOICE",
                      "questionMark":"",
                      "answers":[
                        {"content":"1/10a McEvoy Rd Padstow","correct":false},
                        {"content":"Unit 1, 10A McEvoy Rd","correct":true},
                        {"content":"Unit 1, 10A McEvoy Rd","correct":false},
                        {"content":"10 Ralph St Alexandria","correct":false}]
                    },
                  ]
}
*/

/*

*/