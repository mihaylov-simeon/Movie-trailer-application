import React, { createContext, useContext, useState, useEffect } from 'react';
import axiosConfig from "../../api/axiosConfig";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [name, setUserName] = useState("");
  const [email, setEmail] = useState("");

  const updateName = (newName) => {
    setUserName(newName);
    localStorage.setItem('name', newName);
  };

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      const storedUserName = localStorage.getItem('name');
      const storedEmail = localStorage.getItem('email');

      if (storedUserName) {
        setUserName(storedUserName);
      }

      if (storedEmail) {
        setEmail(storedEmail);
      }

      setIsLoggedIn(true);
    }
  }, []);

  const login = (name, email) => {
    setIsLoggedIn(true);
    setUserName(name);
    setEmail(email);
    localStorage.setItem('authToken', 'yourAuthToken');
    localStorage.setItem('name', name);
    localStorage.setItem('email', email);
  };

  const logout = async () => {
    try {
      await axiosConfig.post("/logout");
    } catch (error) {
      console.error("Logout failed:", error);
    }

    setIsLoggedIn(false);
    localStorage.removeItem('authToken');
    localStorage.removeItem('name');
    localStorage.removeItem('email');
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, name, email, updateName }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
