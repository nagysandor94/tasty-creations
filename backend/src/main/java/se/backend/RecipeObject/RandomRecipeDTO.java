package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public record RandomRecipeDTO(
        Integer id,
        String title,
        ArrayList<ExtendedIngredient> extendedIngredients,
        String instructions,
        String image,
        Boolean isInFav

){}
