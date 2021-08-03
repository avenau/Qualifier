import { useState} from "react";
import { useHistory } from "react-router-dom";

function HomePage() {
    let history = useHistory();

    //To Redirect Page
    /*
        history.push('route') -> This will redirect you to the page
        route being the path that was set in App.js
    */

    return (
        <div>
            <h1>HOME</h1>
            <button onClick={() => {history.push('/suggestskill')} }>Suggest Skill</button>
            <button onClick={() => {history.push('/startquiz')} }>Start Skill Quiz</button>
            <button onClick={() => {history.push('/placements')} }>Search For Placements</button>
        </div>
    )
}
export default HomePage;