import React, { createContext, useContext, useState, useEffect } from 'react';
import axiosConfig from "../../api/axiosConfig";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [name, setUserName] = useState(""); // New state to store user's name

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      setIsLoggedIn(true);
      const storedUserName = localStorage.getItem('name');
      setUserName(storedUserName || "");
    }
  }, []);

  const login = (name) => {
    setIsLoggedIn(true);
    setUserName(name);
    localStorage.setItem('authToken', 'yourAuthToken');
    localStorage.setItem('name', name); // Store the user's name in localStorage
  };


  const logout = async () => {
    try {
      // Call the backend API to mark the user as active: false
      await axiosConfig.post("/logout");
    } catch (error) {
      console.error("Logout failed:", error);
    }

    // Clear the authentication state
    setIsLoggedIn(false);

    // Clear the token from local storage
    localStorage.removeItem('authToken');
    localStorage.removeItem('name');
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, name }}>
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
