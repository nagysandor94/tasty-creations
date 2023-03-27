import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {RecipeModel} from "../model/RecipeModel";
import { Link } from 'react-router-dom';



const Home = () => {


    return(
        <div className='homePage'>
        <h1>HOME</h1>
            <ul className='homePageCards'>
                <li className='card'><Link to="/randomrecipe">Random Recipe</Link></li>
                <li className='card'><Link to="/byrecipe">By Recipe</Link></li>
                <li className='card'><Link to="/byingredients">By Ingredients</Link></li>
                <li className='card'><Link to="partyrecipe">Recipe for Party</Link></li>
                <br/>
            </ul>
        </div>
    )
}

export default Home;
