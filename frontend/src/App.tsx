import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Nav } from 'react-bootstrap';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';


function App() {
  return (
    <div className="App1">
      <Routes>
        <Route path='/' element={<Home/>}> </Route>
      </Routes>

    </div>
  );
}

export default App;
