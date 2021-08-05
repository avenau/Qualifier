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
import CreatePlacement from './components/sales/createPlacement';
import Profile from './components/profile';
import QuizStartPage from './components/quiz/QuizStartPage';
import AttemptQuiz from './components/quiz/AttemptQuiz';
import BrowseQuiz from './components/quiz/BrowseQuiz';
import FinishQuiz from './components/quiz/FinishQuiz';
import Home from './pages/Home'
import { useSelector } from 'react-redux';
import PublicRoute from './utils/PublicRoute';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './components/navbar/NavBar';
import AddSkillPage from './components/skills/addSkillPage';
import DoesNotExistPage from './pages/NotExistPage';
import SearchPlacements from './components/trainee/placements';
import MySkills from './components/trainee/mySkills';
import MarkQuiz from './components/quiz/MarkQuiz';
import SearchTrainee from './components/trainee/TraineeSearch';
import CreateQuiz from './components/trainer/CreateQuiz';
import TraineeResults from './components/trainer/traineeResults';
import ViewQuiz from './components/quiz/ViewQuiz';
import TrainerSkillsPage from './components/trainer/TrainerSkillsPage';
import FinishCreateQuiz from './components/trainer/FinishCreateQuiz'; 

import './customTheme.css'

//To add your page 
/*
  Copy and paste on of the routes
  The path is the link that the use needs to get to page e.g.
  path='/hello', on browser you will need to type localhost:3000/hello
  component={myComponent} is whatever component you want to show on page
*/
function App() {
  const auth = useSelector(state => state.auth);
  return (
    <Router>
      <NavigationBar />

      <Switch>
        <Route exact path='/' component={Home} />
        <Route exact path='/profile' component={Profile} />
        <Route exact path='/suggestskill' component={SuggestSkill} />
        <Route exact path='/createPlacement' component={CreatePlacement} />
        <Route exact path='/startquiz' component={QuizStartPage} />
        <Route exact path='/placements' component={SearchPlacements} />
        <Route exact path='/startquiz/:quiz_id' component={QuizStartPage} />
        <Route exact path='/quiz/:quiz_id' component={AttemptQuiz} />
        <Route exact path='/browsequiz' component={BrowseQuiz} />
        <Route exact path='/finishquiz' component={FinishQuiz} />
        <Route exact path ='/trainer/finishquiz' component={FinishCreateQuiz}/>
        <Route exact path='/createPlacement' component={CreatePlacement} />
        <Route exact path='/startquiz' component={QuizStartPage} />
        <Route exact path='/trainer/addskills' component={AddSkillPage} />
        <Route exact path='/myskills' component={MySkills} />
        <Route exact path='/markquiz' component={MarkQuiz} />
        <Route exact path='/searchtrainee' component={SearchTrainee} />
        <Route exact path='/trainer/createquiz/:quiz_id' component={CreateQuiz} /> 
        <Route exact path='/markquiz/:result_id' component={MarkQuiz} />
        <Route exact path='/viewquiz/:result_id' component={ViewQuiz} />
        <Route exact path='/traineeResults' component={TraineeResults} />
        <Route exact path ='/trainer/searchskills' component={TrainerSkillsPage}/>


        <Route path='/login' component={Login} />
        <Route exact path="/*" component={DoesNotExistPage} />
      </Switch>

    </Router>

  );
}

export default App;
