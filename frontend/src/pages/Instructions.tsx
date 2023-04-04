import React, {useState} from 'react';
import '../Styling/Home.css';
import '../Styling/Instructions.css'
import axios from "axios";
import {ExtendedIngredient, RecipeModel} from "../model/RecipeModel";



const Instructions = ({ name }: { name: string }) => {
    let check =checkEmpty(name);
        function checkEmpty(props:string)
        {
            if(props=="")
                return false;
            return true;
        }
    return(
        <div>
            {check && <li className="instructions">{name}</li>}
        </div>)
}

export default Instructions;
