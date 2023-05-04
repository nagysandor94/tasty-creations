import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { IIngredients, Result } from '../model/RecipeModel';
import { baseUrl } from '../model/utilities';
import '../Styling/SearchByIngredient.css';
import '../Styling/SearchRecipe.css';


const SearchByIngredient = () => {
    const [newIngredient, setNewIngredient] = useState<string>("");
    const [ingredients, setIngredients] = useState<IIngredients[]>([]);
    const [responseSearch, setSearchResponse] = useState<Result[]>();

    function addIngredient() {
        if (!newIngredient) {
            alert("Please add an ingredient!");
            return;
        }

        const ingredient = {
            ingredientId: Math.floor(Math.random() * 1000),
            ingredientName: newIngredient,
        };

        setIngredients((oldList) => [...oldList, ingredient]);

        let arrayOfIngredient = ingredients.map(function (ingredient) {
            return ingredient;
        });
        console.log(arrayOfIngredient);

        let arrayOfIngredients = arrayOfIngredient.push(ingredient);
        console.log(arrayOfIngredients);

        let ingredientsSerialized = JSON.stringify(arrayOfIngredient);

        sessionStorage.setItem("myIngredients", ingredientsSerialized);
        console.log(sessionStorage.getItem("myIngredients"))
        setNewIngredient("");
    }

    function searchRecipe() {
        let queryParam: string = ingredients.map((ingredient) => ingredient.ingredientName).toString();
        console.log(queryParam);
        axios.get(baseUrl + "api/findbyingredients", {
            params: {
                query: queryParam
            }
        })
            .then(response => {
                setSearchResponse(response.data);
                let searchserialized = JSON.stringify(response.data);
                sessionStorage.setItem("mySearchByIngredient", searchserialized);
            });

    }

    useEffect(() => {
        if (sessionStorage.getItem("mySearchByIngredient")) {
            const searchHistory = JSON.parse(sessionStorage.getItem("mySearchByIngredient") || "");
            if (searchHistory) {
                setSearchResponse(searchHistory);

            }
        }


        if (sessionStorage.getItem("myIngredients")) {
            const ingredientshHistory = JSON.parse(sessionStorage.getItem("myIngredients") || "");
            console.log(ingredientshHistory);


            if (ingredientshHistory) {
                setIngredients(ingredientshHistory);

            }
        }

    }, []);

    function deleteItem(id: number) {
        const newArray = ingredients.filter((ingredient) => ingredient.ingredientId !== id);
        setIngredients(newArray);
        let newArraySerialized = JSON.stringify(newArray);
        sessionStorage.setItem("myIngredients", newArraySerialized);
    }


    return (
        <>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            <div className="text">
                <h1>What's in your fridge?</h1>
                {/* <p>Add a new ingredient to your list!</p> */}
            </div>

            <div className='input_div'>

                <input
                    type="text"
                    placeholder="Add a new ingredient..."
                    className='input'
                    value={newIngredient}
                    onChange={(e) => setNewIngredient(e.target.value)}
                />


                <button type='submit' className='addButton' onClick={() => addIngredient()}>+</button>

            </div>





            <div className='fridgeContainer'>

                {ingredients.map((ingredient) => {
                    return (
                        <div className='fridgeList' key={ingredient.ingredientId}>
                            <div className='ingredientinline' key={ingredient.ingredientId} >
                                {ingredient.ingredientName}
                                <button
                                    className="delete-button"
                                    onClick={() => deleteItem(ingredient.ingredientId)}
                                >
                                    ❌
                                </button>
                            </div>
                        </div>
                    );
                })}

                

            </div>

            <button className='searchButton' onClick={() => searchRecipe()}><i className="fa fa-search"></i></button>





            <div className="favoritesSection">
                <h1>Search results</h1>
                <div className='favoritesDashboard'>
                    {responseSearch?.map((response) => {
                        return (<div className='favoritesCard' key={response.id}>
                            <Link className="favCardLink" to={`/recipe/${response.id}`}>
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
    );
}

export default SearchByIngredient;
