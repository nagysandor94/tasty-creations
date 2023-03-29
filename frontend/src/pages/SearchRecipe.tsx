import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { IIngredients, SearchRecipeResponse } from "../model/RecipeModel";
import '../Styling/SearchByIngredient.css';


const SearchRecipe = () => {
    const [search, setSearch] = useState<string>("");
    const [responseSearch, setSearchResponse] = useState<SearchRecipeResponse>();

    const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        getSearchRecipe();
        localStorage.setItem('items', JSON.stringify(responseSearch));

    }
    function getSearchRecipe() {
        axios.get<SearchRecipeResponse>('http://localhost:8080/api/searchrecipebyname', {
            params: {
                query: search
            }
        })
            .then(response => {
                setSearchResponse(response.data);
                console.log(responseSearch?.results);
            });

    }

    useEffect(() => {
        // const items : SearchRecipeResponse = localStorage.getItem('items') || "";
        // if (items) {
        //  setSearchResponse(items);
        // }



    }, [setSearchResponse]);

    return (
        <>
            <form >
                <label>Search recipe:
                    <input
                        type="text"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                </label>
                <button onClick={event => handleSubmit(event)}>Search</button>
            </form>

            <div className="favoritesSection">
            <h1>Search results</h1>
            <div className='favoritesDashboard'>
                {responseSearch?.results.map((response) => {
                    return (<div className="favoritesCard" key={response.id}>
                        <Link to={`/recipe/${response.id}`}>
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
