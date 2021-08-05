import React, { useState } from 'react';
import axios from 'axios';
import { setAccountSession } from '../../utils/Auth';
import { BACKEND_LOGIN_URI, UNAUTHORIZED } from '../../utils/Const';

import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

//redux crab
import { useDispatch } from 'react-redux';
import { updateAuth } from '../../actions';

// utils
import Notification from '../../utils/Notification';

import { updateAccountType, updateLength } from "../../redux/toolbar";



function Login(props) 
{
    const [error, setError] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    function validateForm() {
        return username.length > 0 && password.length > 0;
    }

    // React redux stuff
    const dispatch = useDispatch();

    /*
       const axiosConfig = {
            headers: { Authorization: `Bearer ${sessionStorage.token}`}
        };
        .post(/getPqages, { page:id }, axiosConfig)
        .catch()
    */

    // handle button click of login form
    const handleLogin = (event) => 
    {
        event.preventDefault();
        setError(null);

        axios
        .post(BACKEND_LOGIN_URI, { username: username, password: password })
        .then(response => {
            // Login failure : data.statusCode 
            if (response.data.statusCode === UNAUTHORIZED)
            {
                setError(response.msg);
                Notification({  type: 'warning',
                                msg: response.data.msg,
                                title: 'Login Failure'
                            });
                return;
            }
            console.log("response data" + response.data.userId);
            console.log("response " + response);
            setAccountSession(response.data.jwtToken, response.data.username, response.data.accountType, response.data.userId);
            dispatch(updateAccountType());
            dispatch(updateLength());

            dispatch(updateAuth());
            
            Notification({  type: 'success',
                            msg: response.msg,
                            title: 'Login Success'
                        });
            props.history.push('/');
        })
        // = internal server error
        .catch(error => {
            console.log(error);
            if (error.response.status === 401) {
                setError(error.response.message);
            }
            else setError("Something in the backend went wrong. Please try again later.");

            Notification({  type: 'error',
                            msg: 'internal server error ggwp',
                            title: 'Server Error'
            })
        });
    }

    return (
        <div>
            <h2>Login page</h2>
            <div className="Auth">
            <Form onSubmit={handleLogin}>
                {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
                <Form.Group size="lg" controlId="email">
                <Form.Label>Email</Form.Label>
                <br/>
                <Form.Control
                    autoFocus
                    type="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <br/><br/>
                </Form.Group>
                <Form.Group size="lg" controlId="password">
                <Form.Label>Password</Form.Label>
                <br/>
                <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                </Form.Group>
                <br/>
                <Button block size="lg" type="submit" disabled={!validateForm()}>
                Login
                </Button>
            </Form>
            </div>
        </div>
    );
}

export default Login;