package se.backend.RecipeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListResponseSearchByName(
        List<ResponseSearchByName> results
) {
}
