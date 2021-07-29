import logo from './logo.svg';
import './App.css';
import SuggestSkill from './components/trainee/suggestSkill';
import QuizStartPage from './components/quiz/QuizStartPage';

function App() {
  return (
    <div className="App">
      <SuggestSkill></SuggestSkill>
      <QuizStartPage></QuizStartPage>
    </div>
  );
}

export default App;
