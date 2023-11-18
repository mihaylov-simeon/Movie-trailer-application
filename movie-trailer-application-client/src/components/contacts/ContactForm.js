import React from 'react';
import './ContactForm.css';

const ContactForm = () => {

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        // Your form handling logic goes here
    
        const formData = new FormData(e.target);
    
        try {
          const response = await fetch("https://formspree.io/f/mgejbnga", {
            method: "POST",
            body: formData,
            headers: {
              'Accept': 'application/json',
            },
          });
    
          if (response.ok) {
            alert("Form submitted successfully!");
          } else {
            alert("Form submission failed.");
          }
        } catch (error) {
          console.error("Error submitting the form:", error);
        }
    
        // Clear the form fields
        e.target.reset();
      };
  return (
    <div>
      <form 
        className='contact-form'
        action="https://formspree.io/f/mgejbnga"
        method="POST"
        onSubmit={handleSubmit}
        >
      <h1>Contact</h1>
      <label className='contact-label'>
        <input name='firstName' type="text" className='input-contacts' required />
        <div className="label-text-contacts">First Name</div>
      </label>
      <label className='contact-label'>
        <input name='lastName' type="text" className='input-contacts' required />
        <div className="label-text-contacts">Last Name</div>
      </label>
      <label className='contact-label'>
        <input name='email' type="email" className='input-contacts' required />
        <div className="label-text-contacts">Email Address</div>
      </label>
      <label className='contact-label'>
        <textarea name='message' className='text-contacts-area' placeholder='Your message'></textarea>
      </label>
      <button className='contacts-btn'>Submit</button>
    </form>
      </div>
  );
};

export default ContactForm;