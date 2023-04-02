package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseSearchByName(
        @JsonProperty("id")
        Integer id,

        @JsonProperty("title")
        String title,
        @JsonProperty("image")
        String image
) {
}
