import { useState } from "react";

function SearchPlacements(){

    const axios = require('axios');

    const [placement, setPlacement] = useState("");
    const [searchError, setSearchError] = useState("");

    const submitPlacementSearch = (evt) => {
        evt.preventDefault();
        if (placement.trim().length > 0) {
            setSearchError("");
            const config = { headers: {'Content-Type': 'application/json'} };
            axios.post('http://localhost:9999/searchPlacements', placement, config)
                .then(function (response) {
                    console.log(response);
                    setSearchError(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                })
                .then(function () {
                    console.log('finally');
                })
        } else {
            setSearchError("Search cannot be empty");
        }   
    }

    return (
        <div>
            <form onSubmit={submitPlacementSearch}>
                <label>Skill Name: </label>
                <input type="text" value={placement} onChange={e => setPlacement(e.target.value)} required />
                <input type="submit" value="Search Placement" />
            </form>
            <div>{searchError}</div>
        </div>
    );
}

export default SearchPlacements;