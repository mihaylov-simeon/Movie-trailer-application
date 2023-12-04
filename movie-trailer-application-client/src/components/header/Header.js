import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPhotoVideo } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../authentication/AuthProvider";
import "./Header.css";

const Header = () => {
  const { isLoggedIn, logout, name } = useAuth();
  const [displayName, setDisplayName] = useState(name);
  const navigate = useNavigate();

  useEffect(() => {
    setDisplayName(name);
  }, [name]);

  const handleLogout = () => {
    // Call the logout function from the AuthProvider
    logout();
    // Redirect to the login page
    navigate('/login');
  };
  
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container fluid>
        <Navbar.Brand to="/" as={NavLink} style={{ color: "white" }}>
          <FontAwesomeIcon icon={faPhotoVideo} /> The Movie Trailers Spot
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: "100px" }}
            navbarScroll
          >
            <NavLink className="nav-link" to="/">
              Movies
            </NavLink>
            <NavLink className="nav-link" to="/favorites">
              My Trailers
            </NavLink>
            <NavLink className="nav-link" to="/aboutUs">
              About Us
            </NavLink>
            <NavLink className="nav-link" to="/contactUs">
              Contact Us
            </NavLink>
            <NavLink className="nav-link" to="/user">
              Profile
            </NavLink>
            
          </Nav>
          {!isLoggedIn && (
            <Button as={NavLink} to="/login" variant="outline-info" className="me-2">
              Login
            </Button>
          )}
          {isLoggedIn && (
            <div className="me-2" style={{ color: "white" }}>
               <span>Hello, {displayName}!</span>
            </div>
          )}
          {isLoggedIn && (
            <Button variant="outline-info" className="me-2" onClick={handleLogout}>
              Logout
            </Button>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
