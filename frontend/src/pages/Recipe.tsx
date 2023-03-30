import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { RecipeModel } from '../model/RecipeModel';
import IngredientItem from './IngredientItem';
import Instructions from './Instructions';
import addToFav from "../images/AddToFav.png";
import addedToFav from "../images/AddedToFav.png";
import { useParams } from 'react-router-dom';

const Recipe = () => {
    const baseUrl = "https://tasty-creation.azurewebsites.net/";

    const [recipe,setRecipe]=useState<RecipeModel>();
    const [instructionList,setInstructionList]=useState<string[]>([]);
    const [isInFav,setIsInFav]=useState<boolean>(false);
    const { id } = useParams<string>();

    function getRecipe(recipeId : string) {
        axios.get<RecipeModel>( baseUrl + "api/getrecipe/" + recipeId)
            .then(response=>{
                setRecipe(response.data);
                setInstructionList(response.data.instructions.split("."));
                //console.log(response.data);
            });
    }

    useEffect(()=>{
        if(id){
            getRecipe(id);
        }
    },[]);

    function addRecipeToFavorite() {
        axios.post( baseUrl + "api/addfavorite",recipe);
        setIsInFav(true);
    }

    function removeRecipeFromFavorite() {
        let url= baseUrl + "api/removefavorite/"+recipe?.id;
        axios.delete(url);
        setIsInFav(false);
    }

    return(
        <div className='randomRecipe'>
            <h1>Recipe</h1>
            <div className="recipeHeading">
            <img className="recipeImage" src={recipe?.image}></img>
                <div className="recipeName">{recipe?.title}</div>
                <img className="iconFavorite" src={addToFav} onClick={addRecipeToFavorite}></img>
                {isInFav &&
                <img className="iconFavorite" src={addedToFav} onClick={removeRecipeFromFavorite}></img>
                }
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


export default Recipe;
