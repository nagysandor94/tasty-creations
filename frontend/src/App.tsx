import React from 'react';
import logo from './logo.svg';
import './Styling/App.css';
import { Nav } from 'react-bootstrap';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Favorites from './pages/Favorites';
import RandomRecipe from "./pages/RandomRecipe";
import SearchRecipe from './pages/SearchRecipe';
import Recipe from './pages/Recipe';
import { Link } from 'react-router-dom';
import background from "../images/bkgrnd.png";


function App() {
  return (
    <div className="App">

        <h1 className='appHeader'>TASTY CREATIONS</h1>
        <ul className='pageBanner'>
          <li className='listBanner'> <Link className='linkBanner' to="/">Home</Link></li>
            <li className='listBanner'><Link className='linkBanner' to="/about">About</Link></li>
            <li className='listBanner'><Link className='linkBanner' to="/favorites">Favorites</Link></li>
        </ul>
      <Routes>
          <Route path='/' element={<Home/>}> </Route>
          <Route path='/about' element={<About/>}> </Route>
          <Route path='/favorites' element={<Favorites/>}> </Route>
          <Route path='/randomrecipe' element={<RandomRecipe/>}></Route>
          <Route path='/byrecipe' element={<SearchRecipe/>}></Route>
          <Route path='/recipe/:id' element={<Recipe />}></Route>
      </Routes>
    </div>
  );
}

export default App;
