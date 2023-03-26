import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {ExtendedIngredient, RecipeModel} from "../model/RecipeModel";



const IngredientItem = (props:ExtendedIngredient) => {
    const data=props;
    let imgUrl="https://spoonacular.com/cdn/ingredients_100x100/"+data.image

    return(
        <div className='ingredientItem'>
            <text>{data.name}</text>
            <br/>
            <img className="ingredientImage" src={imgUrl}></img>
            <br/>
            <text className="ingredientAmount">{data.amount}</text>
        </div>
    )
}

export default IngredientItem;
