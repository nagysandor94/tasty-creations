import React, {useEffect, useState} from 'react';
import {RecipeModel} from "../model/RecipeModel";
import axios from "axios";
import IngredientItem from "./IngredientItem";
import Instructions from "./Instructions";
import '../Styling/RandomRecipe.css';
const RandomRecipe = () => {
    const [recipe,setRecipe]=useState<RecipeModel>();
    const [instructionList,setInstructionList]=useState<string[]>([]);

    function getRandomRecipe() {
        axios.get<RecipeModel>('http://localhost:8080/api/random')
            .then(response=>{
                setRecipe(response.data);
                setInstructionList(response.data.instructions.split("."));
                console.log(response.data);
            });
    }

    useEffect(()=>{
        getRandomRecipe();
    },[]);

    return(
        <div className='randomRecipe'>
            <h1>RandomRecipe</h1>
            <div className="recipeHeading">
            <img className="recipeImage" src={recipe?.image}></img>
                <div className="recipeName">{recipe?.title}</div>
            </div>
            <h4>Ingredients</h4>
            <div className="ingredientsList">
            {recipe?.extendedIngredients.map(item=><IngredientItem id={item.id} aisle={item.aisle} image={item.image}
                                                                   name={item.name} amount={item.amount} unit={item.unit}
                                                                   unitShort={item.unitShort} unitLong={item.unitLong}
                                                                   originalString={item.originalString}
                                                                   metaInformation={item.metaInformation} measures={item.measures}/>)}
            </div>
            <h4>Instructions</h4>
            <ol className="instructionsList">
            {instructionList.map(item=><Instructions name={item}/>)}
            </ol>
            </div>
    );
}

export default RandomRecipe;
