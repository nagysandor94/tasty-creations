package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseSearchByIngredients(

        @JsonProperty("")
        List<ResponseSearchByName> results
) {
}
