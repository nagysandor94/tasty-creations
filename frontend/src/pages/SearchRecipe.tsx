import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { IIngredients, SearchRecipeResponse } from "../model/RecipeModel";
import '../Styling/SearchByIngredient.css';
import '../Styling/SearchRecipe.css';
import { baseUrl } from '../model/utilities';


const SearchRecipe = () => {
    const [search, setSearch] = useState<string>("");
    const [responseSearch, setSearchResponse] = useState<SearchRecipeResponse>();
    const [items, setItems] = useState<SearchRecipeResponse>();

    const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        getSearchRecipe();

    }
    function getSearchRecipe() {
        axios.get<SearchRecipeResponse>( baseUrl+ "api/searchrecipebyname", {
            params: {
                query: search
            }
        })
            .then(response => {
                setSearchResponse(response.data);
                let searchserialized = JSON.stringify(response.data);
                sessionStorage.setItem("mySearch", searchserialized);
            });

    }

    useEffect(() => {

        console.log(sessionStorage);

        if (sessionStorage.getItem("mySearch")) {
            const searchHistory = JSON.parse(sessionStorage.getItem("mySearch") || "");
            //console.log(searchHistory);

            if (searchHistory) {
                setSearchResponse(searchHistory);

            }
        }



    }, []);


    return (

        <>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            <form className="searchRecipeForm">
                    <input className="searchInput"
                        type="text"
                        placeholder='Search recipe...'
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />

                <button className='searchButton' onClick={event => handleSubmit(event)}><i className="fa fa-search"></i></button>
            </form>

            <div className="favoritesSection">
                <h1>Search results</h1>
                <div className='favoritesDashboard'>
                    {responseSearch?.results.map((response) => {
                        return (<div className="favoritesCard" key={response.id}>
                            <Link className="favCardLink"  to={`/recipe/${response.id}`}>
                                <img className="favoriteRecipeImage" key={response.id} src={response.image} alt="Avatar" />
                                <div>
                                    <h4 className="favoriteRecipeName" key={response.id}>{response.title}</h4>
                                </div>
                            </Link>
                        </div>)
                    })}
                </div>
            </div>
        </>
    )
}

export default SearchRecipe;
