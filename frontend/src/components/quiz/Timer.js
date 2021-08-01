import React from "react";

export default function Timer(props) {
  const [initialTime, setInitialTime] = React.useState(0);
  const [startTimer, setStartTimer] = React.useState(false);
  let quizId= props.quizId;

  const handleOnClick = () => {
    setInitialTime(5);
    setStartTimer(true);
  };

  React.useEffect(() => {
    if (initialTime > 0) {
      setTimeout(() => {
        console.log("startTime, ", initialTime);
        setInitialTime(initialTime - 1);
      }, 1000);
    }

    if (initialTime === 0 && startTimer) {
      console.log("done");
      setStartTimer(false);
    }
  }, [initialTime, startTimer]);

  return (
    <div>
      <button onClick={handleOnClick}>
        Start
      </button>
      <div>CountDown: {initialTime}</div>;
    </div>
  );
}
