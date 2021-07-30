import logo from './logo.svg';
import './App.css';
import SuggestSkill from './components/trainee/suggestSkill';
import QuizStartPage from './components/quiz/QuizStartPage';
<<<<<<< HEAD
=======
import Home from './pages/Home'
>>>>>>> master

import { BrowserRouter as Router, Route, Switch, IndexRoute } from "react-router-dom";
import DoesNotExistPage from './pages/NotExistPage';

//To add your page 
/*
  Copy and paste on of the routes
  The path is the link that the use needs to get to page e.g.
  path='/hello', on browser you will need to type localhost:3000/hello
  component={myComponent} is whatever component you want to show on page
*/
function App() {
  return (
<<<<<<< HEAD
    <div className="App">
      <SuggestSkill></SuggestSkill>
      <QuizStartPage></QuizStartPage>
    </div>
=======
    <Router>
      
      <Switch>
        <Route exact path='/' component={Home} />
        <Route exact path='/suggestskill' component={SuggestSkill}/>
        <Route exact path='/startquiz' component={QuizStartPage}/>
        <Route exact path="/*" component={DoesNotExistPage} />
      </Switch>

    </Router>
>>>>>>> master
  );
}

export default App;
