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
// import useDropdownMenu from 'react-accessible-dropdown-menu-hook';
import homeIcon from "./images/home.png"
import searchIcon from "./images/search.png"
import aboutIcon from "./images/about.png"
import favoriteIcon from "./images/favorite.png"



function App() {

    return (
        <div className="App">


            <div className="appHeader">
                <h1 className='appName'>TASTY CREATIONS</h1>
                <div className='pageBanner'>
                    <Link className='bannerItem' to="/home">
                        <img className="bannerIcon" src={homeIcon}/>
                        <div className='linkBanner'>Home</div>
                    </Link>
                    <Link className='bannerItem'to="/about">
                        <img className="bannerIcon" src={aboutIcon}/>
                        <div className='linkBanner' >About</div>
                    </Link>
                    <div className="bannerItem">
                        <img className="bannerIcon" src={searchIcon}/>
                    <div className="dropdown">
                        <button className="dropbtn">Search</button>
                        <div className="dropdown-content">
                            <Link className='link' to="/randomrecipe">Get a Random Recipe</Link>
                            <Link className='link' to="/byrecipe">Search Recipes</Link>
                            <Link className='link' to="/byingredients">By Ingredients</Link>
                        </div>
                    </div>
                    </div>

                    <Link className='bannerItem'to="/favorites">
                        <img className="bannerIcon" src={favoriteIcon}/>
                        <div className='linkBanner' >Favorites</div>
                    </Link>
                    {/* <div className='bannerItem'>
                        <Link className='linkBanner' to="/search">Search</Link>
                    </div> */}

                </div>
            </div>
            <div className="appContent">
                <Routes>
                    <Route path='/' element={<Welcome />}> </Route>
                    <Route path='/home' element={<Welcome />}> </Route>
                    <Route path='/about' element={<About />}> </Route>
                    <Route path='/favorites' element={<Favorites />}> </Route>
                    {/* <Route path='/search' element={<Home />}> </Route> */}
                    <Route path='/randomrecipe' element={<RandomRecipe />}></Route>
                    <Route path='/byrecipe' element={<SearchRecipe />}></Route>
                    <Route path='/recipe/:id' element={<Recipe />}></Route>
                    <Route path='/byingredients' element={<SearchByIngredient />}></Route>
                </Routes>
            </div>
            <div className="appFooter">
                <img className="logo" src={logoTasty}></img>
                <text>App by Poppies ©</text>
            </div>
        </div>

    );
}

export default App;
