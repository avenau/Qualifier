const jwtTokenReducer = (state=sessionStorage.getItem('jwtToken'), 
                        action) =>
{
    switch(action.type)
    {
        case 'UPDATE':
            state=sessionStorage.getItem('jwtToken');
            return state;
        case 'DELETE':
            sessionStorage.removeItem('jwtToken');
            state=null;
            return state;
        default:
            return state;
    }
}

export default jwtTokenReducer;