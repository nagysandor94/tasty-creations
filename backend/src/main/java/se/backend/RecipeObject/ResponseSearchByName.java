package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseSearchByName(
        String id,
        String title,
        String image
) {
}
