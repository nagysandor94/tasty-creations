package se.backend.RecipeObject;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RandomRecipeObject{
        public ArrayList<Recipe> getRecipes() {
                return recipes;
        }

        @JsonProperty("recipes")
        public ArrayList<Recipe> recipes;
}
