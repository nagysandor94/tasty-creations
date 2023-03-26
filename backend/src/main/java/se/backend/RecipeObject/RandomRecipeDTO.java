package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.backend.InstructionDTO;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RandomRecipeDTO(
        @JsonProperty
        Integer id,
        String title,
        ArrayList<ExtendedIngredient> extendedIngredients,
        String instructions,
        String image

) {


}
