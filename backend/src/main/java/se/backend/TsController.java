package se.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.backend.RecipeObject.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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
    public ResponseEntity<ListResponseSearchByName> getRecipeByName(@RequestParam String query) throws IOException, InterruptedException, URISyntaxException {
        return ResponseEntity.ok(service.searchRecipeByName(query));
    }

    @GetMapping("/findbyingredients")
    public ResponseEntity<List<ResponseSearchByName>> getRecipeByIngredients(@RequestParam String query) throws IOException, InterruptedException, URISyntaxException {
        return ResponseEntity.ok(service.searchRecipeByIngredients(query));
    }

    @GetMapping("getrecipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) throws IOException, InterruptedException, URISyntaxException {
        return ResponseEntity.ok(service.getRecipeById(id));
    }

    @PostMapping("/addfavorite")
    public ResponseEntity<Object> addRecipeToFavorites(@RequestBody RandomRecipeDTO newRecipe){
        if(!recipeInFavorites(newRecipe.id()))
            service.addRecipeToFavorite(newRecipe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removefavorite/{recipeID}")
    public ResponseEntity<Object> removeFavorite(@PathVariable int recipeID){
        if (recipeInFavorites(recipeID))
            service.removeRecipe(recipeID);
    return ResponseEntity.noContent().build();
    }

    private boolean recipeInFavorites(Integer id) {
        return service.recipeInFavorites(id);
    }
}
