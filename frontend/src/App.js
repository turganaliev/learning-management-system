import React, { useState } from 'react';

function App() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      });
      const data = await response.json();
      console.log('Registered:', data);
      alert('Registration successful!');
    } catch (error) {
      console.error('Error:', error);
      alert('Registration failed!');
    }
  };

  return (
    <div>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <input name="firstName" placeholder="First Name" onChange={handleChange} /><br/>
        <input name="lastName" placeholder="Last Name" onChange={handleChange} /><br/>
        <input name="username" placeholder="Username" onChange={handleChange} /><br/>
        <input name="email" placeholder="Email" onChange={handleChange} /><br/>
        <input name="password" type="password" placeholder="Password" onChange={handleChange} /><br/>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default App;