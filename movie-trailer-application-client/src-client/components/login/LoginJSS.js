import React, { useState } from "react";
import { useNavigate } from "react-router-dom"
import "./LoginStyle.css";
import axiosConfig from "../../api/axiosConfig";

const Login = () => {
  const navigate = useNavigate();
  const [isLoginFormVisible, setIsLoginFormVisible] = useState(true);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });

  const showLoginForm = () => {
    setIsLoginFormVisible(true);
  };

  const showRegisterForm = () => {
    setIsLoginFormVisible(false);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axiosConfig.post("/login", {
        email: formData.email,
        password: formData.password,
      });

      // Handle successful login
      console.log("Logged in:", response.data);

      // Check for the success message or any other indicator in the response
      if (response.status === 200) {
        // Redirect to the aboutus page
        navigate("/");
      } else {
        // Handle other scenarios if needed
      }
    } catch (error) {
      // Handle login error
      console.error("Login failed:", error);
    }
  };

  const handleRegisterSubmit = async () => {
    try {
      const response = await axiosConfig.post("/register", {
        name: formData.name,
        email: formData.email,
        password: formData.password,
      });
      // Handle successful registration
      console.log("Registered:", response.data);
    } catch (error) {
      // Handle registration error
      console.error("Registration failed:", error);
    }
  };

  return (
    <div className={`login-page form ${isLoginFormVisible ? "is-login-visible" : "is-register-visible"}`}>
      {isLoginFormVisible && (
        <form className="login-form" onSubmit={handleLoginSubmit}>
          <span id="log-or-create">Log in to your account</span>
          <input type="text" name="email" placeholder="email" value={formData.email} onChange={handleFormChange} />
          <input
            type="password"
            name="password"
            placeholder="password"
            value={formData.password}
            onChange={handleFormChange}
          />
          <button type="submit">Login</button>
          <p className="message">
            Not registered?{" "}
            <a href="#" id="create-account" onClick={showRegisterForm}>
              Create an account
            </a>
          </p>
        </form>
      )}
      {!isLoginFormVisible && (
        <form className="register-form" onSubmit={handleRegisterSubmit}>
          <span id="log-or-create">Create an account</span>
          <input type="text" name="name" placeholder="name" value={formData.name} onChange={handleFormChange} />
          <input type="text" name="email" placeholder="email" value={formData.email} onChange={handleFormChange} />
          <input
            type="password"
            name="password"
            placeholder="password"
            value={formData.password}
            onChange={handleFormChange}
          />
          <button type="submit">Create</button>
          <p className="message">
            Already registered?{" "}
            <a href="#" id="login-link" onClick={showLoginForm}>
              Sign In
            </a>
          </p>
        </form>
      )}
    </div>
  );
};

export default Login;
