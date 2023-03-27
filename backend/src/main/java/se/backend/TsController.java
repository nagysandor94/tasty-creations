package se.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.backend.RecipeObject.ListResponseSearchByName;
import se.backend.RecipeObject.RandomRecipeDTO;
import se.backend.RecipeObject.Recipe;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TsController {
    @Autowired
    TsService service;

    @GetMapping("/random")
    public ResponseEntity<RandomRecipeDTO> getRandomRecipe() throws IOException, InterruptedException {
        return  ResponseEntity.ok(service.getRandomRecipe());
    }


    @GetMapping("/searchrecipebyname")
    public ResponseEntity<ListResponseSearchByName> getRecipeByName(@RequestParam String query) throws IOException, InterruptedException {
        return ResponseEntity.ok(service.searchRecipeByName(query));
    }

    @GetMapping("getrecipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) throws IOException, InterruptedException {
        return ResponseEntity.ok(service.getRecipeById(id));
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
