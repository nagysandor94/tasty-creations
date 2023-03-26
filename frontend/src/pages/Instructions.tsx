import React, {useState} from 'react';
import '../Styling/Home.css';
import axios from "axios";
import {ExtendedIngredient, RecipeModel} from "../model/RecipeModel";



const Instructions = ({ name }: { name: string }) => {
    const data=name;

    return(
        <li>{data}</li>
    )
}

export default Instructions;
