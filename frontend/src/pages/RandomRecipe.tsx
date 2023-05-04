import React, {useEffect, useState} from 'react';
import {RecipeModel} from "../model/RecipeModel";
import { baseUrl } from '../model/utilities';
import axios from "axios";
import IngredientItem from "./IngredientItem";
import Instructions from "./Instructions";
import '../Styling/RandomRecipe.css';
import addToFav from "../images/heart.png";
import addedToFav from "../images/lover.png";
import nextIcon from "../images/NextIcon.png";



const RandomRecipe = () => {

    const [recipe, setRecipe] = useState<RecipeModel>();
    const [instructionList, setInstructionList] = useState<string[]>([]);
    const [isInFav, setIsInFav] = useState<boolean>(false);

    function getRandomRecipe() {
        axios.get<RecipeModel>(baseUrl + "api/random")
            .then(response => {
                setRecipe(response.data);
                setInstructionList(response.data.instructions.split("."));
                setIsInFav(response.data.isInFav);
                //console.log(response.data);
            });
    }

    useEffect(() => {
        getRandomRecipe();
    }, []);

    function addRecipeToFavorite() {
        axios.post(baseUrl + "api/addfavorite", recipe);
        setIsInFav(true);
    }

    function removeRecipeFromFavorite() {
        let url = baseUrl + "removefavorite/" + recipe?.id;
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
                <img className="nextIcon" src={nextIcon} onClick={getRandomRecipe}></img>
                <img className="recipeImage" src={recipe?.image}></img>
            </div>
        <div className="recipeSection">
            <div className="ingredientsSection">
                <h4></h4>
                <div className="wrap-collapsible">
                    <input id="collapsible" className="ingredientListToggleCheck" type="checkbox"/>
                    <label htmlFor="collapsible" className="lbl-toggle">Ingredients</label>
                    <div className="collapsible-content">
                        <div className="ingredientsList">
                            {recipe?.extendedIngredients.map(item => <IngredientItem key={recipe.extendedIngredients.indexOf(item)} id={item.id} aisle={item.aisle} image={item.image}
                                                                                     name={item.name} amount={item.amount} unit={item.unit}
                                                                                     unitShort={item.unitShort} unitLong={item.unitLong}
                                                                                     originalString={item.originalString}
                                                                                     metaInformation={item.metaInformation} measures={item.measures} />)}
                        </div>
                    </div>
                </div>
            </div>
            <div className="instructionsList">
                <h3 className="instructionsHeader">Instructions</h3>
                <ol>
                    {instructionList.map(item => <Instructions key={instructionList.indexOf(item)} name={item} />)}
                </ol>
                {/* <img className="recipeImageFooter" src={recipe?.image}></img> */}
            </div>
</div>
            <br /><br />
        </div>

    );
}

export default RandomRecipe;
