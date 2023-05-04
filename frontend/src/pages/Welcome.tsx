import React, {useEffect, useState} from 'react';
import '../Styling/Welcome.css';
import axios from "axios";
import {FavoritesListResponse, RecipeModel} from "../model/RecipeModel";
import { Link } from 'react-router-dom';
import { baseUrl } from '../model/utilities';
import addToFav from "../images/heart.png";
import addedToFav from "../images/lover.png";
import nextIcon from "../images/NextIcon.png";



const Welcome = () => {
    const [recipe1, setRecipe1] = useState<RecipeModel>();
    const [recipe2, setRecipe2] = useState<RecipeModel>();
    const [isInFav, setIsInFav] = useState<boolean>(false);
    const [favoritesList,setFavoritesList]=useState<FavoritesListResponse>();
    function getRandomRecipe() {
        axios.get<RecipeModel>(baseUrl + "api/random")
            .then(response => {
                setRecipe1(response.data);
                setIsInFav(response.data.isInFav);
            });
        axios.get<RecipeModel>(baseUrl + "api/random")
            .then(response => {
                setRecipe2(response.data);
                setIsInFav(response.data.isInFav);
            });
    }

    useEffect(() => {
        getRandomRecipe();
    }, []);


    return(
        <div>
            <div className="welcomeRecipeHeading">Recipe in highlight! {recipe1?.title}</div>
            <div className="welcomeRecipeSection">
                <Link className="favCardLink" to={`/recipe/${recipe1?.id}`}>
                <div>
                <img className="welcomeRecipeImage" src={recipe1?.image} alt="Avatar"></img>
                </div>
                <div>
                <p className="recipeSummary">
                    {recipe1?.summary}
                </p>
                </div>
                </Link>
            </div>
            <div className="welcomeRecipeHeading">Recipe in highlight! {recipe2?.title}</div>
            <div className="welcomeRecipeSection">
                <Link className="favCardLink" to={`/recipe/${recipe2?.id}`}>
                    <div>
                        <img className="welcomeRecipeImage" src={recipe2?.image} alt="Avatar"></img>
                    </div>
                    <div>
                        <p className="recipeSummary">
                            {recipe2?.summary}
                        </p>
                    </div>
                </Link>
            </div>
        </div>
    )
}

export default Welcome;
