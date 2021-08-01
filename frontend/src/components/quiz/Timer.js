import {useEffect, useState} from "react";

 function Timer(props) {
  const [startTimer, setStartTimer] = useState(false);
  const [startTime, setStartTime] = useState(-10);
  var axios = require('axios');

  const submitQuiz= (() => {
    console.log("SUBMITTING QUIZ");
    axios
    .post('http://localhost:9999/submitQuiz', { quizId: 6})
    .then((response) => {

    })
    .catch(()=>{

    })
   })



  useEffect(() => {

    if (startTime > 0) {
      setTimeout(() => {
        
        setStartTime(startTime - 1);
      }, 1000);
    }
    
    if (startTime === 0 && startTimer) {
      setStartTimer(false);
      submitQuiz();
    } else if (startTime === -10 && props.duration !== 0){
        setStartTime(props.duration);
    }

    setStartTimer(true);
  }, [startTime, startTimer]);

  return (
      <div>CountDown: {startTime} </div>

  );
}
export default Timer;
