package se.backend.RecipeObject;

import java.util.ArrayList;


public record RecipeDTO(
        Integer id,
        String title,
        ArrayList<ExtendedIngredient> extendedIngredients,
        String instructions,
        String image,
        Boolean isInFav

){}
