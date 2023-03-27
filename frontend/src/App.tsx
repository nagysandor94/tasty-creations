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


function App() {
  return (
    <div className="App">
        <h1 className='appHeader'>TASTY CREATIONS</h1>
        <ul className='pageBanner'>
            <li className='listBanner'><a className='linkBanner' href="/">Home</a></li>
            <li className='listBanner'><a className='linkBanner' href="About">About</a></li>
            <li className='listBanner'><a className='linkBanner' href="Favorites">Favorites</a></li>
        </ul>
      <Routes>
          <Route path='/' element={<Home/>}> </Route>
          <Route path='/About' element={<About/>}> </Route>
          <Route path='/Favorites' element={<Favorites/>}> </Route>
          <Route path='/randomrecipe' element={<RandomRecipe/>}></Route>
          <Route path='/byrecipe' element={<SearchRecipe/>}></Route>
          <Route path='/recipe/:id' element={<Recipe />}></Route>
      </Routes>
    </div>
  );
}

export default App;
