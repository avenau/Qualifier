import jwtTokenReducer  from './jwtToken';
import authReducer from './auth';

import {combineReducers} from 'redux';

const allReducers = combineReducers({
    jwtToken: jwtTokenReducer,
    auth: authReducer,
})

export default allReducers;