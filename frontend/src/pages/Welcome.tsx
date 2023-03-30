import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {RecipeModel} from "../model/RecipeModel";
import { Link } from 'react-router-dom';
import Home from "./Home";
import '../Styling/Welcome.css';



const Welcome = () => {

    return(
        <div>
            <h1 className="welcomeQuote">A Recipe is a Story that ends with a good meal....</h1>
        </div>
    )
}
export default Welcome;
