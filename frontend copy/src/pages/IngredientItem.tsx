import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {ExtendedIngredient, RecipeModel} from "../model/RecipeModel";
import "../Styling/ingredientItem.css"



const IngredientItem = (props:ExtendedIngredient) => {
    const data=props;
    let imgUrl="https://spoonacular.com/cdn/ingredients_100x100/"+data.image
    return(
        <div className='ingredientItem'>
            <div>{data.name}</div>
            <br/>
            <img className="ingredientImage" src={imgUrl}></img>
            <br/>
            <div className="ingredientAmount">{data.amount}</div>
            <div>{data.measures.metric.unitShort}</div>
        </div>
    )
}

export default IngredientItem;
