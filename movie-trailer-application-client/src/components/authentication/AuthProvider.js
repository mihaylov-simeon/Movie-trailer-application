import React, { createContext, useContext, useState, useEffect } from 'react';
import axiosConfig from "../../api/axiosConfig";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const login = () => {
    setIsLoggedIn(true);
    localStorage.setItem('authToken', 'yourAuthToken');
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
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
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
