import {InputText} from "./props/Input";
import {useEffect, useRef, useState} from "react";
import {Button} from "./props/Button";
import {useNavigate} from "react-router-dom";


export function Registration({ setIsAuthenticated }) {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [secondPassword, setSecondPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();
    const secondPasswordRef = useRef(null);

    function handlePassword(e) {
        setPassword(e.target.value);
    }

    function handleSecondPassword(e) {
        setSecondPassword(e.target.value);
    }


    function handleLogin(e) {
        setLogin(e.target.value);
    }


    function validate() {
        if (password !== secondPassword) {
            return false;
        }


        return !((login.trim().length > 0 || password.trim().length > 0 || secondPassword.trim() > 0)
            && (login.trim() === "" || password.trim() === "" || secondPassword.trim() === ""));


    }

    function handleSubmit() {
        let requestContent = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: login,
                password: password,
            }),
        };

        if (!validate()) {
            secondPasswordRef.current.classList.add("is-invalid");
            return;
        }

        fetch("/api/register", requestContent)
            .then(response => {
                if (response.ok) {
                    response.json().then((data) => {
                        if (data.token) {
                            localStorage.setItem("jwt", data.token);
                            setIsAuthenticated(true);
                            navigate("/main");
                        }
                    });
                } else {
                    if(response.status === 409) {
                        setErrorMessage("Имя пользователя занято");
                    }
                }
            })
            .catch(() => {
                setErrorMessage("Ошибка подключения к серверу");
            });;
    }


    return (

        <div className="registrationContainer">
            <div className="authNavigate">
                <Button
                    onClick={() => navigate("/login")}
                    value="LogIn"
                    id="toAuthButton"
                />
            </div>

            <div className="registration">
                <h1>Registration</h1>
                <InputText id="username"
                           type="text"
                           name="Login"
                           onChange={(e) => handleLogin(e)}
                           value={login}
                           class="authInputs"
                />
                <InputText id="password"
                           type="password"
                           name="Password"
                           onChange={(e) => handlePassword(e)}
                           value={password}
                           class="authInputs"
                />
                <InputText id="password"
                           type="password"
                           name="Password"
                           onChange={(e) => handleSecondPassword(e)}
                           value={secondPassword}
                           class="authInputs"
                           ref = {secondPasswordRef}

                />
                {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
                <Button onClick={handleSubmit}
                        value="Register"
                        id="registerButton"
                        class="authInputs"
                />
            </div>
        </div>
    )

}
