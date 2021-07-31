import logo from './logo.svg';
import './App.css';
import React from 'react';
import { 
	BrowserRouter as Router, 
	NavLink, 
	Route, 
	Switch 
} from 'react-router-dom';
import Login from './components/userLogin/Login';
import SuggestSkill from './components/trainee/suggestSkill';
import { useSelector } from 'react-redux';
import PublicRoute from './utils/PublicRoute';

function App() {
  const auth = useSelector(state => state.auth);

  return (
    <div className="App">
      <Router>
        <div className='nav'>
          <NavLink to='/login' activeClassName='active'>
            Login
          </NavLink>
        </div>

        <div className='content'>
          <switch>
            <PublicRoute path='/login' component={Login} />
          </switch>
        </div>
      </Router>
      <SuggestSkill></SuggestSkill>
    </div>
  );
}

export default App;
