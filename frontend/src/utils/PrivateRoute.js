import React, {useLayoutEffect} from 'react';
import { Route, Redirect } from 'react-router-dom';
import { getJwtToken } from './Auth';
import { useSelector } from 'react-redux';
import { asVisitor } from './Auth';

// utils
import Notification from '../utils/Notification';

const PrivateRoute = ({component: Component, ...rest}) => 
{
    const auth = useSelector(state => state.auth);

    useLayoutEffect(() => {
        if (asVisitor(auth)){
            Notification({  type: 'warning',
                            msg: 'Access denied',
                            title: 'Not even logged in'
                        });
            }
    }, [auth]);

    return (
        <Route
            {...rest}
            render={(props) => getJwtToken() ? 
                                <Component {...props} />
                                :
                                <Redirect to={{pathname: '/login', state: {from: props.location}}}/>
                    }
        />
    )
}

export default PrivateRoute;