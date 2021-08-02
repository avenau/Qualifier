// import * as jwtToken from './jwtToken';

export const loggedIn = () =>
{
    console.log(sessionStorage.getItem('jwtToken'));
    return {
        type: 'SIGN_IN'
    }
}

export const updateJwtToken = () => 
{
    console.log("in this function");
    return {
        type: 'UPDATE'
    }
}

export const updateAuth = () =>
{
    return {
        type: 'UPDATE'
    }
}

export const deleteAuth = () =>
{
    return {
        type: 'DELETE'
    }
}