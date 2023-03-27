package se.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.backend.RecipeObject.RandomRecipeDTO;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TsController {
    @Autowired
    TsService service;

    @GetMapping("/random")
    public ResponseEntity<RandomRecipeDTO> getRandomRecipe() throws IOException, InterruptedException {
        return  ResponseEntity.ok(service.getRandomRecipe());
    }
    @PostMapping("/addfavorite")
    public ResponseEntity<Object> addRecipeToFavorites(@RequestBody RandomRecipeDTO newRecipe){
        if(!recipeInFavorites(newRecipe.id()))
            service.addRecipeToFavorite(newRecipe);
        return ResponseEntity.ok().build();
    }

    private boolean recipeInFavorites(Integer id) {
        return service.recipeInFavorites(id);
    }
}
