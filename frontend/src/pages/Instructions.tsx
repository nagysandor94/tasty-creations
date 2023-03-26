import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {ExtendedIngredient, RecipeModel} from "../model/RecipeModel";



const IngredientItem = (props:ExtendedIngredient) => {
    const data=props;

    return(
        <div className='ingredientItem'>
            <text>{data.name}</text>
            <text>{data.amount}</text>
        </div>
    )
}

export default IngredientItem;
