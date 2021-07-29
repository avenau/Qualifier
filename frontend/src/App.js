import logo from './logo.svg';
import './App.css';
import SuggestSkill from './components/trainee/suggestSkill';
import Profile from './components/profile';

function App() {
  return (
    <div className="App">
      <Profile></Profile>
      <SuggestSkill></SuggestSkill>

    </div>
  );
}

export default App;
