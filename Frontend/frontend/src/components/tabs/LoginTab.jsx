import React, { useState } from 'react';
import axios from 'axios';

const LoginTab = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [registerMode, setRegisterMode] = useState(false);
    const [token, setToken] = useState(localStorage.getItem("token") || "");

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("https://localhost:8081/api/auth/login", { username, password });
            setToken(response.data.token);
            localStorage.setItem("token", response.data.token);
            setUsername("");
            setPassword("");
        } catch (error) {
            console.error("Logowanie nie powiodło się:", error);
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await axios.post("https://localhost:8081/api/auth/register", { username, password });
            alert("Rejestracja zakończona sukcesem. Możesz się zalogować.");
            setRegisterMode(false);
        } catch (error) {
            console.error("Rejestracja nie powiodła się:", error);
        }
    };

    const logout = () => {
        setToken("");
        localStorage.removeItem("token");
    };

    return (
        <div>
            {!token ? (
                <div>
                    <h2>{registerMode ? "Rejestracja" : "Logowanie"}</h2>
                    <form onSubmit={registerMode ? handleRegister : handleLogin}>
                        <div>
                            <label>Nazwa użytkownika:</label>
                            <input
                                type="text"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </div>
                        <div>
                            <label>Hasło:</label>
                            <input
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit">{registerMode ? "Zarejestruj" : "Zaloguj"}</button>
                    </form>
                    <button onClick={() => setRegisterMode(!registerMode)}>
                        {registerMode ? "Przełącz na logowanie" : "Przełącz na rejestrację"}
                    </button>
                </div>
            ) : (
                <div>
                    <h2>Witaj!</h2>
                    <button onClick={logout}>Wyloguj</button>
                </div>
            )}
        </div>
    );
};

export default LoginTab;
