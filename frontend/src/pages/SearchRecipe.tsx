import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { SearchRecipeResponse } from "../model/RecipeModel";

const SearchRecipe = () => {
    const [search, setSearch] = useState<string>("");
    const [responseSearch, setSearchResponse] = useState<SearchRecipeResponse>();

    const handleSubmit = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        getSearchRecipe();

    }
    function getSearchRecipe() {
        axios.get<SearchRecipeResponse>('http://localhost:8080/api/searchrecipebyname?query=' + search)
            .then(response => {
                setSearchResponse(response.data);
                console.log(responseSearch?.results);
            });

    }

    useEffect(() => {
    }, [setSearchResponse]);

    return (
        <>
            <form >
                <label>Enter your name:
                    <input
                        type="text"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                </label>
                <button onClick={event => handleSubmit(event)}>Search</button>
            </form>

            <div>
                {responseSearch?.results.map((response) => {
                    return (<div key={response.id}>
                        <Link to={`recipe/${response.id}`}>
                        <img key={response.id} src={response.image} alt="Avatar" />
                        <div>
                            
                            <h4 key={response.id}>{response.title}</h4>
                        </div>
                        </Link>
                    </div>)
                })}
            </div>
            {/* <img src={responseSearch?.results.map} alt="Avatar" />
                    <div className='container'>
                        <h4><b>John Doe</b></h4>
                        <p>Architect & Engineer</p>
                    </div>
            </div> */}
        </>
    )
}

export default SearchRecipe;
