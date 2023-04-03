import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {RecipeModel} from "../model/RecipeModel";
import { Link } from 'react-router-dom';



const Home = () => {


    return(
        <div>
        <div className='homePage'>
            <div className='homePageCards'>
                <Link className="homePageLink" to="/randomrecipe"><h3 className='card'>Random Recipe</h3></Link>
                <Link className="homePageLink"  to="/byrecipe"><h3 className='card'>By Recipe</h3></Link>
                <Link className="homePageLink"  to="/byingredients"><h3 className='card'>By Ingredients</h3></Link>
                <br/>
            </div>
        </div>
        </div>
    )
}

export default Home;
