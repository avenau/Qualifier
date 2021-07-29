import { useState, useEffect } from "react";

function QuizStartPage() {
    const axios = require('axios');
    const quizTemplate = {
                            quizId: 0,
                            name: "",
                            description: "",
                            duration: 0.0,
                            questionCount: 0,
                            passingMark: 101.0,
                            questions: []
                        }
    const [quiz, setQuiz] = useState(quizTemplate);

    useEffect(() => {
        axios
        .get('http://localhost:9999/getStartQuizDetails', {   
            params: {
                id:1
            },
        }).then(response => {
            setQuiz(response.data)
        })
        .catch(() => {});  
    }, [quiz.passingMark]);

    return (
        <div>
            <p>{quiz.name}</p>
            <p>Time Limit: {quiz.duration}</p>
            <p>Number of Questions: {quiz.questionCount}</p>
            <p>Marks Required: {quiz.passingMark}%</p>
            
        </div>
    )

}
export default QuizStartPage;