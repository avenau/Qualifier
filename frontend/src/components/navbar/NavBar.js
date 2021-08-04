import { Navbar, Nav, NavDropdown, Container, NavLink } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { removeUserSession, setAccountSession } from '../../utils/Auth';
import { MdNotifications } from "react-icons/md";


function NavigationBar() {
    const history = useHistory();
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
        removeUserSession();
        history.push('/');
    })

    function LoggedOut () {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                <Navbar.Brand onClick={() => {history.push('/')} }>Qualifier</Navbar.Brand>
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
                    <Navbar.Brand onClick={() => {history.push('/users')} }>Qualifier</Navbar.Brand>
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
                    <Navbar.Brand onClick={() => {history.push('/users')} }>Qualifier</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link >Placements</Nav.Link>  
                            <Nav.Link >Trainees</Nav.Link>  
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


    const trainee = (() => {
        
    })

    const trainer = (() => {
        
    })

    const sales = (() => {
        
    })

    if (sessionStorage.length == 0){
        return(
            <LoggedOut/>
        )
    } else if (sessionStorage.accountType === "admin"){
        return (
            <Admin/>
        )
    } else if (sessionStorage.accountType === "trainee"){
        return (
            <Trainee/>
        )
    }

    


    return (
        <p>SIGNED IN</p>
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