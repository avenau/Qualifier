import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { getJwtToken } from './Auth';

import { useSelector } from 'react-redux';
import { asVisitor } from './Auth';
import Notification from './Notification';
import { useLayoutEffect } from 'react';

const PublicRoute = ({component: Component, ...rest}) => 
{

    const auth = useSelector(state => state.auth);

    useLayoutEffect(() => {
        if (!asVisitor(auth)){
            Notification({  type: 'warning',
                            title: 'Invalid request',
                            msg: 'already logged in'
                        });
            }
    }, []);

    return (
        <Route
            {...rest}
            render={(props) => !getJwtToken() ? 
                                <Component {...props} />
                                :
                                <Redirect to={{pathname: '/', state: {from: props.location}}}/>
                    }
        />
    )
}

export default PublicRoute;