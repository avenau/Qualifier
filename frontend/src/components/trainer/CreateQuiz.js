
import { Redirect, useHistory, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import {Modal, Container, Button, Form, Col, Row, InputGroup, FormControl} from 'react-bootstrap';
function CreateQuiz() {
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

    
    if (isLoading) {
      return <div className="App">Loading...</div>;
    }


    function AddMultipleSelect() {
      const [show, setShow] = useState(false);
    
      const handleClose = () => setShow(false);
      const handleShow = () => setShow(true);
      let shortQuestion = {
        questionContent: "",
        questionType: "MULTIPLE_SELECT",
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
          </Button>
    
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Multiple Select</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form hasValidation onSubmit={addQuestion}>
                <Form.Group className="mb-3" controlId="formGridAddress1" >
                  
                  <Form.Control onChange={((event) => {shortQuestion.questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                  <Form.Control onChange={((event) => {shortQuestion.questionMark = event.target.value})}  type="number" rows={1} placeholder="Mark" />          
                  <InputGroup className="mb-3">
                    <InputGroup.Check onChange = {((event) => {(ans1.correct)? ans1.correct = false : ans1 = true})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans1.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-3">
                  <InputGroup.Check onChange = {((event) => {(ans2.correct)? ans2.correct = false : ans2 = true})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans2.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-3">
                  <InputGroup.Check onChange = {((event) => {(ans3.correct)? ans3.correct = false : ans3 = true})} name="multipleChoice" aria-label="Radio button for following text input" />
                    <Form.Control onChange={((event) => {ans3.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                  </InputGroup>

                  <InputGroup className="mb-3">
                  <InputGroup.Check onChange = {((event) => {(ans4.correct)? ans4.correct = false : ans4 = true})} name="multipleChoice" aria-label="Radio button for following text input" />
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
            </Button>
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Multiple Choice</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Form hasValidation onSubmit={addQuestion}>
                  <Form.Group className="mb-3" controlId="formGridAddress1" >
                    
                    <Form.Control onChange={((event) => {shortQuestion.questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                    <Form.Control onChange={((event) => {shortQuestion.questionMark = event.target.value})}  type="number" rows={1} placeholder="Mark" />          
                    <InputGroup className="mb-3">
                      <InputGroup.Radio onChange = {((event) => {ans1.correct = true; ans2.correct = false; ans3.correct = false; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans1.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-3">
                    <InputGroup.Radio onChange = {((event) => {ans1.correct = false; ans2.correct = true; ans3.correct = false; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans2.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-3">
                    <InputGroup.Radio onChange = {((event) => {ans1.correct = false; ans2.correct = false; ans3.correct = true; ans4.correct = false})} name="multipleChoice" aria-label="Radio button for following text input" />
                      <Form.Control onChange={((event) => {ans3.content = event.target.value})} as="textarea" rows={1} placeholder="Answer" />
                    </InputGroup>

                    <InputGroup className="mb-3">
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
        let questionMark = 0;
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);

        const addQuestion = (() => {
          handleClose();
          console.log("LENGTH BEGINNING " + startHook.questions.length)
          let shortQuestion = {
                                questionContent: questionContent,
                                questionType: "SHORT_ANSWER",
                                questionPoints: questionMark,
                                answers: []
                              }
          
          setQuestions((prevState) => [...prevState, shortQuestion]);

          console.log("JSON STRING " + JSON.stringify(hookQuestions));
          
        })
      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Short Answer
            </Button>
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Short Answer</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Form.Group className="mb-3" controlId="formGridAddress1">
                
                      <Form.Control onChange={((event) => {questionContent = event.target.value})}  as="textarea" rows={3} placeholder="Question" />
                      <Form.Control onChange={((event) => {questionMark = event.target.value})}  type="number" rows={1} placeholder="Mark" />
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
            <Form>
                <Row className="mb-3">
                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.name = event.target.value})} type="email" placeholder="Name" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.duration = event.target.value})}  type="email" placeholder="Duration" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">

                    <Form.Control onChange={((event) => {startHook.passingMark = event.target.value})} type="email" placeholder="Passing Mark" />
                    </Form.Group>


                </Row>

                <Form.Group className="mb-3" controlId="formGridAddress1">
                    <Form.Control onChange={((event) => {startHook.description = event.target.value})}  as="textarea" rows={3} placeholder="Description" />
                </Form.Group>
                {console.log("SECOND JSON " + JSON.stringify(hookQuestions))}
                {hookQuestions.map ((question) => (

                  <div>
                    {JSON.stringify(question)}
                  </div>

                  ))}


                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
            
          )
      }




    return (
        <Container>
            <h3 className="pt-3">Quiz Editor</h3>
            <AddMultipleChoice/>
            <AddMultipleSelect/>
            <AddShortAnswer/>
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
    "questions": []
}
*/