import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { IIngredients, Result } from '../model/RecipeModel';


const SearchByIngredient = () => {
    const [newIngredient, setNewIngredient] = useState<string>("");

    const [ingredients, setIngredients] = useState<IIngredients[]>([]);

    const [responseSearch, setSearchResponse] = useState<Result[]>();


    function addIngredient() {
        if (!newIngredient) {
            alert("Press enter an ingredient.");
            return;
        }

        const ingredient = {
            ingredientId: Math.floor(Math.random() * 1000),
            ingredientName: newIngredient,
        };

        setIngredients((oldList) => [...oldList, ingredient]);

        setNewIngredient("");
    }

    function searchRecipe() {
        let queryParam: string = ingredients.map((ingredient) => ingredient.ingredientName).toString();
        console.log(queryParam);
        axios.get('http://localhost:8080/api/findbyingredients', {
            params: {
                query: queryParam
            }
        })
            .then(response => {
                setSearchResponse(response.data);
                console.log(responseSearch);
            });

    }

    useEffect(() => {
    }, [setSearchResponse]);

    /* Deletes an item based on the `item.id` key */
    function deleteItem(id: number) {
        const newArray = ingredients.filter((ingredient) => ingredient.ingredientId !== id);
        setIngredients(newArray);
    }


    return (
        <>
            <div className="">
                <h1>What's in your fridge?</h1>
                <p>Add a new ingredient to your list!</p>

                <input
                    type="text"
                    placeholder="Add an ingredient..."
                    value={newIngredient}
                    onChange={(e) => setNewIngredient(e.target.value)}
                />


                <button onClick={() => addIngredient()}>Add</button>


                <div className='fridgeContainer'>
                    <div className=''>
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
                </div>
                <button onClick={() => searchRecipe()}>Search recipes by ingredients</button>

            </div>
            <div>
                {responseSearch?.map((response) => {
                    return (<div key={response.id}>
                        <Link to={`/recipe/${response.id}`}>
                            <img key={response.id} src={response.image} alt="Avatar" />
                            <div>

                                <h4 key={response.id}>{response.title}</h4>
                            </div>
                        </Link>
                    </div>)
                })}
            </div>
        </>
    );
}

export default SearchByIngredient;
