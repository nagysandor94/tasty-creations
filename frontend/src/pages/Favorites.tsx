import React, {useEffect, useState} from 'react';
import {FavoritesListResponse} from "../model/RecipeModel";
import axios from "axios";
import {Link} from "react-router-dom";
import addToFav from "../images/AddToFav.png";
import addedToFav from "../images/lover.png";
import"../Styling/Favorites.css";
import {wait} from "@testing-library/user-event/dist/utils";

const Favorites = () => {

    const baseUrl = "https://tasty-creation.azurewebsites.net/";
    // const baseUrl = "http://localhost:8080/";
    const [favoritesList,setFavoritesList]=useState<FavoritesListResponse>();

    function getFavorites(){
        axios.get<FavoritesListResponse>(baseUrl + "api/favorites")
            .then(response=>{setFavoritesList(response.data)})
    }

    async function removeRecipeFromFavorite(props: string) {
        let url = baseUrl + "api/removefavorite/" + props;
        await axios.delete(url);
        getFavorites();
    }

    useEffect(()=>{
        getFavorites();
    },[])

    return(
        <div className="favoritesSection">
        <h4>Favorites</h4>
            <div className="favoritesDashboard">
            {favoritesList?.favoritesList.map(response=>{
                return(
                <div className="favoritesCard" key={response.id}>
                    <img className="iconFavoriteCard" src={addedToFav} onClick={()=>removeRecipeFromFavorite(response.id)}></img>
                    <Link className="favCardLink" to={`/recipe/${response.id}`}>
                        <img className="favoriteRecipeImage" key={response.id} src={response.image} alt="Avatar" />
                        <div>
                            <h4 className="favoriteRecipeName" key={response.id}>{response.title}</h4>
                        </div>
                    </Link>
                </div>)
                })
            }
            </div>
        </div>
    )
}

export default Favorites;
