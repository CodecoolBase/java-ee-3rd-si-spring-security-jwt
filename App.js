import {useState} from "react";
import "./App.css";
import Axios from "axios";

function App() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("No message");

    const login = () => {
        Axios.post(
            "http://localhost:8080/auth/login",
            {
                username,
                password
            },
            {
                withCredentials: true
            }
        )
            .then(() => {
                Axios.get("http://localhost:8080/protected-hello", {
                    withCredentials: true
                }).then(res => {
                    setMessage(res.data);
                }).catch(() => {
                        setMessage("Sorry bro, not authorized")
                    }
                );
            })
            .catch(() => {
                setMessage("Something went wrong");
            });
    };

    return (
        < div
    className = "App" >
        < header
    className = "App-header" >
        < div >
        < input
    type = "text"
    name = "username"
    value = {username}
    onChange = {e
=>
    setUsername(e.target.value)
}
    />
    < input
    type = "password"
    name = "password"
    value = {password}
    onChange = {e
=>
    setPassword(e.target.value)
}
    />
    < button
    type = "submit"
    onClick = {login} >
        Login
    pls
    < /button>
    < /div>
    < div > {message} < /div>
    < /header>
    < /div>
)
    ;
}

export default App;
