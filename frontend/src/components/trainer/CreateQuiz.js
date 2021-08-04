
import { Redirect, useHistory, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import {Modal, Container, Button, Form, Col, Row, InputGroup, FormControl} from 'react-bootstrap';
function CreateQuiz() {
    const skillLevelId = 5;
    const startInfo = {
                            "quizId": 132,
                            "name": "",
                            "description": "",
                            "duration": 0.0,
                            "questionCount": 0,
                            "passingMark": 0.0,
                            "questions": []
                        }

    useEffect (() => {
        
    })

    function AddMultipleSelect() {
        const [show, setShow] = useState(false);
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);
      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Multi Select
            </Button>
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Multi Select</Modal.Title>
              </Modal.Header>
              <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
              <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                  Close
                </Button>
                <Button variant="primary" onClick={handleClose}>
                  Save Changes
                </Button>
              </Modal.Footer>
            </Modal>
          </>
        );
      }

      function AddMultipleChoice() {
        const [show, setShow] = useState(false);
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);
      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Multiple Choice
            </Button>
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Multiple Choice</Modal.Title>
              </Modal.Header>
              <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
              <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                  Close
                </Button>
                <Button variant="primary" onClick={handleClose}>
                  Save Changes
                </Button>
              </Modal.Footer>
            </Modal>
          </>
        );
      }

      function AddShortAnswer() {
        const [show, setShow] = useState(false);
      
        const handleClose = () => setShow(false);
        const handleShow = () => setShow(true);
      
        return (
          <>
            <Button variant="primary" onClick={handleShow}>
              Add Short Answer
            </Button>
      
            <Modal show={show} onHide={handleClose}>
              <Modal.Header closeButton>
                <Modal.Title>Short Answer</Modal.Title>
              </Modal.Header>
              <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
              <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                  Close
                </Button>
                <Button variant="primary" onClick={handleClose}>
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
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridEmail">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />
                    </Form.Group>


                </Row>

                <Form.Group className="mb-3" controlId="formGridAddress1">
                    <Form.Label>Address</Form.Label>
                    <Form.Control as="textarea" rows={3} placeholder="1234 Main St" />
                </Form.Group>


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