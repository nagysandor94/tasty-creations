import React, {useEffect, useState} from 'react';
import {RecipeModel} from "../model/RecipeModel";
import axios from "axios";
import IngredientItem from "./IngredientItem";
import Instructions from "./Instructions";
import '../Styling/RandomRecipe.css';
import addToFav from "../images/AddToFav.png";
import addedToFav from "../images/AddedToFav.png";
import nextIcon from "../images/NextIcon.png";



const RandomRecipe = () => {
    const [recipe,setRecipe]=useState<RecipeModel>();
    const [instructionList,setInstructionList]=useState<string[]>([]);
    const [isInFav,setIsInFav]=useState<boolean>(false);

    function getRandomRecipe() {
        axios.get<RecipeModel>('http://localhost:8080/api/random')
            .then(response=>{
                setRecipe(response.data);
                setInstructionList(response.data.instructions.split("."));
                setIsInFav(response.data.isInFav);
                console.log(response.data);
            });
    }

    useEffect(()=>{
        getRandomRecipe();
    },[]);

    function addRecipeToFavorite() {
        axios.post("http://localhost:8080/api/addfavorite",recipe);
        setIsInFav(true);
    }

    function removeRecipeFromFavorite() {
        let url="http://localhost:8080/api/removefavorite/"+recipe?.id;
        axios.delete(url);
        setIsInFav(false);
    }

    return(
        <div className='randomRecipe'>
            <h1>RandomRecipe</h1>
            <div className="recipeHeading">
            <img className="recipeImage" src={recipe?.image}></img>
                <div className="recipeName">{recipe?.title}</div>
                <img className="iconFavorite" src={addToFav} onClick={addRecipeToFavorite}></img>
                {isInFav &&
                <img className="iconFavorite" src={addedToFav} onClick={removeRecipeFromFavorite}></img>
                }
                <img className="nextIcon" src={nextIcon} onClick={getRandomRecipe}></img>
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
            <img className="recipeImageFooter" src={recipe?.image}></img>
            </div>

    );
}

export default RandomRecipe;
