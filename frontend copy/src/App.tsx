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



function App() {
  return (
    <div className="App">
        <h1 className='appHeader'>TASTY CREATIONS</h1>
        <div className='pageBanner'>
            <Link className='linkBanner' to="/home">Home</Link>
            <Link className='linkBanner' to="/about">About</Link>
            <Link className='linkBanner' to="/favorites">Favorites</Link>
        </div>
      <Routes>
          <Route path='/' element={<Welcome/>}></Route>
          <Route path='/home' element={<Home/>}> </Route>
          <Route path='/about' element={<About/>}> </Route>
          <Route path='/favorites' element={<Favorites/>}> </Route>
          <Route path='/randomrecipe' element={<RandomRecipe/>}></Route>
          <Route path='/byrecipe' element={<SearchRecipe/>}></Route>
          <Route path='/recipe/:id' element={<Recipe />}></Route>
          <Route path='/byingredients' element={<SearchByIngredient />}></Route>
      </Routes>
    </div>
  );
}

export default App;
