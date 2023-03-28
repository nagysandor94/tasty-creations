import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {RecipeModel} from "../model/RecipeModel";



const Home = () => {


    return(
        <div className='homePage'>
        <h1>HOME</h1>
            <ul className='homePageCards'>
                <li className='card'><a className="cardLink" href="/randomrecipe">Random Recipe</a></li>
                <li className='card'><a className="cardLink" href="byrecipe">By Recipe</a></li>
                <li className='card'><a className="cardLink" href="byingredients">By Ingredients</a></li>
                <li className='card'><a className="cardLink" href="partyrecipe">Recipe for Party</a></li>
                <br/>
            </ul>
        </div>
    )
}

export default Home;
