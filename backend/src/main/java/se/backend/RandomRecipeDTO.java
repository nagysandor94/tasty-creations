package se.backend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RandomRecipeDTO(
        @JsonProperty
        String id,
        String title,
        List<String> ingredients,
        List <InstructionDTO> instructions,
        String image

) {


}
