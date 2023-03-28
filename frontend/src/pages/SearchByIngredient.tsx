import React, { useState } from 'react';
import { IIngredients } from '../model/RecipeModel';


const SearchByIngredient = () => {
    const [newIngredient, setNewIngredient] = useState<string>("");

    const [ingredients, setIngredients] = useState<IIngredients[]>([]);

    const [showEdit, setShowEdit] = useState(-1);
    const [updatedText, setUpdatedText] = useState<string>("");

    // Helper Functions

    /* Adds a new item to the list array*/
    function addItem() {
        // ! Check for empty item
        if (!newIngredient) {
            alert("Press enter an ingredient.");
            return;
        }

        const ingredient = {
            ingredientId: Math.floor(Math.random() * 1000),
            ingredientName: newIngredient,
        };

        // Add new item to items array
        setIngredients((oldList) => [...oldList, ingredient]);

        // Reset newItem back to original state
        setNewIngredient("");
    }

    /* Deletes an item based on the `item.id` key */
    function deleteItem(id:number) {
        const newArray = ingredients.filter((ingredient) => ingredient.ingredientId !== id);
        setIngredients(newArray);
    }

    // /* Edit an item text after creating it. */
    // function editItem(id, newText) {
    //     // Get the current item
    //     const currentItem = items.filter((item) => item.id === id);

    //     // Create a new item with same id
    //     const newItem = {
    //         id: currentItem.id,
    //         value: newText,
    //     };

    //     deleteItem(id);

    //     // Replace item in the item list
    //     setItems((oldList) => [...oldList, newItem]);
    //     setUpdatedText("");
    //     setShowEdit(-1);
    // }

    // Main part of app
    return (
        <div className="app">
            {/* 1. Header  */}
            <h1>What's in your fridge?</h1>
            <p>Add a new ingredient to your list!</p>

            <input
                type="text"
                placeholder="Add an ingredient..."
                value={newIngredient}
                onChange={(e) => setNewIngredient(e.target.value)}
            />

            {/* Add (button) */}
            <button onClick={() => addItem()}>Add</button>

            {/* 3. List of todos (unordered list) */}
            <ul>
                {ingredients.map((ingredient) => {
                    return (
                        <div>
                            <li key={ingredient.ingredientId} >
                                {ingredient.ingredientName}
                                <button
                                    className="delete-button"
                                    onClick={() => deleteItem(ingredient.ingredientId)}
                                >
                                    ❌
                                </button>
                            </li>
                        </div>
                    );
                })}
            </ul>
        </div>
    );
}

export default SearchByIngredient;
