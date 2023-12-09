import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useAuth } from "../authentication/AuthProvider";
import axiosConfig from "../../api/axiosConfig";
import './User.css'

const User = () => {
  const { name: initialName, email: initialEmail, updateName } = useAuth();
  const [name, setName] = useState(initialName || '');  // Initialize with an empty string
  const [email, setEmail] = useState(initialEmail || '');  // Initialize with an empty string

  useEffect(() => {
    // Fetch user information when the component mounts
    axiosConfig.post("/user")
      .then((response) => {
        const { email, name } = response.data;
        setName(name || '');
        setEmail(email || '');
      })
      .catch((error) => {
        console.error("Error fetching user details:", error);
      });
  }, []);

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleUpdateName = () => {
    // Send a request to update the user's name in the backend
    axiosConfig.put("/user", { name })
      .then((response) => {
        console.log(response.data)
        updateName(name);
      })
      .catch((error) => {
        console.error("Error updating name:", error);
      });
  };

  return (
    <div className="user-container">
      <h2>User Profile</h2>
      <Form className="user-form">
        <Form.Group className="mb-3">
          <Form.Label>Email:</Form.Label>
          <Form.Control type="text" value={email} readOnly />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Name:</Form.Label>
          <Form.Control type="text" value={name} onChange={handleNameChange} />
        </Form.Group>
        <Button className="update-name-btn" variant="primary" onClick={handleUpdateName}>
          Update Name
        </Button>
      </Form>
    </div>
  );
};

export default User;
