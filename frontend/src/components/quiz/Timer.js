import {useEffect, useState} from "react";

 function Timer(props) {
  const [startTimer, setStartTimer] = useState(false);
  const [startTime, setStartTime] = useState(-10);



  useEffect(() => {

    if (startTime > 0) {
      setTimeout(() => {
        
        setStartTime(startTime - 1);
      }, 1000);
    }
    
    if (startTime === 0 && startTimer) {
      console.log("done");
      setStartTimer(false);
    } else if (startTime === -10 && props.duration !== 0){
        setStartTime(props.duration);
    }

    setStartTimer(true);
  }, [startTime, startTimer]);

  return (
    <div className="sticky-top">
      <div>CountDown: {startTime} </div>
      <br/>
    </div>
  );
}
export default Timer;
