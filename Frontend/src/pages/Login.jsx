import React, { useState } from "react";
import { login } from "../services/authService";

const Login = () => {
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const data = await login(email, password);
            alert("Login successful!");
            console.log(data);
            // Handle successful login (e.g., store token, redirect)
        } catch (err) {
            setError(err);
        }
    };

    return (
        <div>
            <h2>Login</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <form onSubmit={handleLogin}>
                <label>Email:</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <label>Password:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">Login</button>
            </form>
            <p>
                Forgot Password? <a href="/reset-password">Reset Here</a>
            </p>
            <p>
                Don't have an account? <a href="/signup">Signup</a>
            </p>
        </div>
    );
};

export default Login;
