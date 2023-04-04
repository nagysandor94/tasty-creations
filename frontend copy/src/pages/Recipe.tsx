import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { RecipeModel } from '../model/RecipeModel';
import IngredientItem from './IngredientItem';
import Instructions from './Instructions';
import addToFav from "../images/AddToFav.png";
import addedToFav from "../images/AddedToFav.png";
import { useParams } from 'react-router-dom';
import "../Styling/Recipe.css"

const Recipe = () => {
    const baseUrl = "https://tasty-creation.azurewebsites.net/";
    //const baseUrl = "http://localhost:8080/";
    const [recipe, setRecipe] = useState<RecipeModel>();
    const [instructionList, setInstructionList] = useState<string[]>([]);
    const [isInFav, setIsInFav] = useState<boolean>(false);
    const { id } = useParams<string>();

    function getRecipe(recipeId: string) {
        axios.get<RecipeModel>(baseUrl + "api/getrecipe/" + recipeId)
            .then(response => {
                setRecipe(response.data);
                setInstructionList(response.data.instructions.split("."));
                setIsInFav(response.data.isInFav)
                console.log(response.data.isInFav)
            });
    }

    useEffect(() => {
        if (id) {
            getRecipe(id);
        }
    }, []);

    function addRecipeToFavorite() {
        axios.post(baseUrl + "api/addfavorite", recipe);
        setIsInFav(true);
    }

    function removeRecipeFromFavorite() {
        let url = baseUrl + "api/removefavorite/" + recipe?.id;
        axios.delete(url);
        setIsInFav(false);
    }

    return (
        <div className='randomRecipe'>
            <h1 className="randomRecipeHeader">{recipe?.title}</h1>
            <div className="recipeHeading">
                <img className="iconFavorite" src={addToFav} onClick={addRecipeToFavorite}></img>
                {isInFav &&
                    <img className="iconFavorite" src={addedToFav} onClick={removeRecipeFromFavorite}></img>
                }
                <img className="recipeImage" src={recipe?.image}></img>
            </div>

            <div className="ingredientsSection">
                <h4 className="ingredientsHeader">Ingredients</h4>
                <div className="ingredientsList">
                    {recipe?.extendedIngredients.map(item => <IngredientItem key={recipe.extendedIngredients.indexOf(item)} id={item.id} aisle={item.aisle} image={item.image}
                        name={item.name} amount={item.amount} unit={item.unit}
                        unitShort={item.unitShort} unitLong={item.unitLong}
                        originalString={item.originalString}
                        metaInformation={item.metaInformation} measures={item.measures} />)}
                </div>
            </div>

            <div className="instructionsList">
                <h3 className="instructionsHeader">Instructions</h3>
                <ol className="instructionsList" >
                   {instructionList.map(item => <Instructions key={instructionList.indexOf(item)} name={item} />)}
                </ol>
            </div>
            <br /><br />

        </div>
    );

}


export default Recipe;
