import logo from './logo.svg';
import './App.css';
import SuggestSkill from './components/trainee/suggestSkill';
import CreatePlacement from './components/sales/createPlacement';
import Profile from './components/profile';

function App() {
  return (
    <div className="App">
      <CreatePlacement></CreatePlacement>
      <Profile></Profile>
      <SuggestSkill></SuggestSkill>

    </div>
  );
}

export default App;
