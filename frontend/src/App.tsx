import React from 'react';
import './Styling/App.css';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import About from './pages/About';
import Favorites from './pages/Favorites';
import RandomRecipe from "./pages/RandomRecipe";
import SearchRecipe from './pages/SearchRecipe';
import Recipe from './pages/Recipe';
import { Link } from 'react-router-dom';
import SearchByIngredient from './pages/SearchByIngredient';
import Welcome from "./pages/Welcome";
import logoTasty from "./images/logo.png"



function App() {
  return (
    <div className="App">
        <div className="appHeader">
            <img className="logo" src={logoTasty}></img>
        <h1 className='appName'>TASTY CREATIONS</h1>
        <div className='pageBanner'>
            <div className='bannerItem'>
                <Link className='linkBanner' to="/home">Home</Link>
            </div>
            <div className='bannerItem'>
                <Link className='linkBanner' to="/about">About</Link>
            </div>
            <div className='bannerItem'>
                <Link className='linkBanner' to="/favorites">Favorites</Link>
            </div>
            <div className='bannerItem'>
                <Link className='linkBanner' to="/search">Search</Link>
            </div>
        </div>
        </div>
        <div className="appContent">
      <Routes>
          <Route path='/' element={<Welcome/>}> </Route>
          <Route path='/home' element={<Welcome/>}> </Route>
          <Route path='/about' element={<About/>}> </Route>
          <Route path='/favorites' element={<Favorites/>}> </Route>
          <Route path='/search' element={<Home/>}> </Route>
          <Route path='/randomrecipe' element={<RandomRecipe/>}></Route>
          <Route path='/byrecipe' element={<SearchRecipe/>}></Route>
          <Route path='/recipe/:id' element={<Recipe />}></Route>
          <Route path='/byingredients' element={<SearchByIngredient />}></Route>
      </Routes>
        </div>
        <div className="appFooter">
            <text>App by Poppies ©</text>
        </div>
    </div>

  );
}

export default App;
