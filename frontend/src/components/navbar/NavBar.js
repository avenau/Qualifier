import { Navbar, Nav, NavDropdown, Container, NavLink } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { removeUserSession, setAccountSession } from '../../utils/Auth';
import { MdNotifications } from "react-icons/md";
import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { updateAccountType, updateLength } from "../../redux/toolbar";
import './NavBar.css'


function NavigationBar() {
    const history = useHistory();
    const accountType = useSelector((state) => state.toolbar.accountType);
    const sessionLength = useSelector((state) => state.toolbar.sessionLength);
    const dispatch = useDispatch();
    let nav = <p>HELLO</p>;

    useEffect (() => {
        // console.log("Login type: asdf " + accountType);
        // if (sessionStorage.length == 0){
        //     return(
        //         <LoggedOut/>
        //     )
        // } else if (sessionStorage.getItem("accountType") === "admin"){
        //     setType(sessionStorage.getItem("accountType"));
        //     console.log("Login type: admin " + accountType);
        //     return (
        //         <Admin/>
        //     )
        // } else if (sessionStorage.getItem("accountType") === "trainee"){
        //     setType(sessionStorage.getItem("accountType"));
        //     console.log("Login type: trainee " + accountType);
        //     return (
        //         <Trainee/>
        //     )
        // }
        console.log("RUNNING USEEFFECT");
    }, [accountType])

    function LogoutButton () {

        return (
            <NavLink  onClick={logoutFunction} activeClassName='active'>
                Log Out
            </NavLink>
        )
    }

    function ProfileButton () {
        return (
            <Nav.Link  onClick={() => {history.push('/profile')} }>{sessionStorage.getItem('username')}</Nav.Link>
        )
    }

    const logoutFunction = (() => {
        console.log("Logout type: " + sessionStorage.getItem("accountType"));
        removeUserSession();
        
        dispatch(updateAccountType());
        dispatch(updateLength());
        
        history.push('/');
    })

    function LoggedOut () {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                <Navbar.Brand onClick={() => {history.push('/')} }>SkillUp</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">

                    </Nav>
                    <Nav>
                        <NavLink  onClick={() => {history.push('/login')} } activeClassName='active'>
                            Login
                        </NavLink>
                    </Nav>
                </Navbar.Collapse>
                </Container>
            </Navbar>
        )
    };

    function Admin () {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand onClick={() => {history.push('/')} }>SkillUp</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="#features">Users</Nav.Link>                   
                        </Nav>
                        
                        <Nav>
                            <LogoutButton/>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
        </Navbar>
        )
    };

    function Trainee (){
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand onClick={() => {history.push('/')} }>SkillUp</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link onClick={() => {history.push('/placements')} } >Placements</Nav.Link>  
                            <Nav.Link onClick={() => { history.push('/searchtrainee') }}>Trainees</Nav.Link>  
                            <Nav.Link onClick={() => {history.push('/browsequiz')} }>Quizzes</Nav.Link>
                            
                                            
                        </Nav>
                        
                        <Nav>
                            <ProfileButton/>
                            <Nav.Link><MdNotifications/></Nav.Link> 
                            <LogoutButton/>                          
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        )
    }

    function Trainer (){
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand onClick={() => {history.push('/')} }>SkillUp</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                    <NavDropdown title= "Skills" id="nav-dropdown">
                        <NavDropdown.Item onClick={() => {history.push('/trainer/addskills')} }>Add Skills</NavDropdown.Item>
                        <NavDropdown.Item onClick={() => {history.push('/trainer/searchskills')} }>Search Skills</NavDropdown.Item>
                    </NavDropdown>
                        <Nav className="me-auto">  
                            <Nav.Link onClick={() => { history.push('/searchtrainee') }}>Trainees</Nav.Link>
                                            
                        </Nav>
                        
                        <Nav>
                            <Nav.Link>{sessionStorage.getItem('username')}</Nav.Link>
                            <Nav.Link><MdNotifications/></Nav.Link> 
                            <LogoutButton/>                          
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        )
    }

    function Sales (){
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand onClick={() => {history.push('/')} }>SkillUp</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">  
                        <NavDropdown title= "Placements" id="nav-dropdown">
                            <NavDropdown.Item onClick={() => { history.push('/placements') }}>Browse Placements</NavDropdown.Item>
                            <NavDropdown.Item onClick={() => {history.push('/createPlacement')} }>Create Placements</NavDropdown.Item>
                        </NavDropdown>
                              
                            <Nav.Link onClick={() => { history.push('/searchtrainee') }}>Trainees</Nav.Link>
                            
                                            
                        </Nav>
                        
                        <Nav>
                            <Nav.Link>{sessionStorage.getItem('username')}</Nav.Link>
                            <Nav.Link><MdNotifications/></Nav.Link> 
                            <LogoutButton/>                          
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        )
    }


    if (sessionLength != 0 && accountType !== "admin" && accountType !== "trainee" && accountType !== "trainer" && accountType !== "sales"){
        dispatch(updateAccountType());
        dispatch(updateLength());
    }

    if (sessionLength == 0){
            nav = <LoggedOut/>
        
    } else if (accountType === "admin"){
            nav = <Admin/>

    } else if (accountType === "trainee"){
            nav = <Trainee/>
    } else if (accountType === "trainer"){
            nav = <Trainer/>
    } else if (accountType === "sales"){
        nav = <Sales/>
}


    


    return (
        <div>{nav}</div>
        
    )
}
export default NavigationBar;

/*
       <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
            <Container>
            <Navbar.Brand onClick={() => {history.push('/')} }>Qualifier</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="me-auto">
                <Nav.Link href="#features">Features</Nav.Link>
                

                <Nav.Link href="#pricing">Pricing</Nav.Link>
                <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
                    <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                    <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
                    <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
                </NavDropdown>
                </Nav>
                <Nav>
                <NavLink  onClick={() => {history.push('/login')} } activeClassName='active'>
                    Login
                </NavLink>
                <Nav.Link eventKey={2} href="#memes">
                    Dank memes
                </Nav.Link>
                </Nav>
            </Navbar.Collapse>
            </Container>
        </Navbar>

*/