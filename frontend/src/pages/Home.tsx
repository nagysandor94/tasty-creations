import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {RecipeModel} from "../model/RecipeModel";



const Home = () => {


    return(
        <div className='homePage'>
        <h1>HOME</h1>
            <ul className='homePageCards'>
                <li className='card'><a href="/randomrecipe">Random Recipe</a></li>
                <li className='card'><a href="byrecipe">By Recipe</a></li>
                <li className='card'><a href="byingredients">By Ingredients</a></li>
                <li className='card'><a href="partyrecipe">Recipe for Party</a></li>
                <br/>
            </ul>
        </div>
    )
}

export default Home;
