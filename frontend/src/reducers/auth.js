const initialState = {
    jwtToken: sessionStorage.getItem('jwtToken'),
    accountType: sessionStorage.getItem('accountType'),
    username: sessionStorage.getItem('username')
}

const authReducer = (state=initialState, action) => 
{
    switch (action.type)
    {
        case 'UPDATE':
            return {
                jwtToken: sessionStorage.getItem('jwtToken'),
                accountType: sessionStorage.getItem('accountType'),
                username: sessionStorage.getItem('username')
            };

        case 'DELETE':
            sessionStorage.removeItem('jwtToken');
            sessionStorage.removeItem('accountType');
            sessionStorage.removeItem('username');
            state = {
                jwtToken: null,
                accountType: null,
                username: null
            }
            return state;

        default:
            return state;
    }
}

export default authReducer;