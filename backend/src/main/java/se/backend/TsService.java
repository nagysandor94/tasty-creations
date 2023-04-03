package se.backend;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.backend.RecipeObject.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TsService {

    ObjectMapper mapper;

    FavoriteRecipeRepository repo;

    public TsService (@Autowired FavoriteRecipeRepository repo, ObjectMapper mapper) {
        this.mapper = mapper;
        this.repo = repo;
    }

    private String apiKeyS = System.getenv("API_KEY_S");

    public RecipeDTO getRandomRecipe() throws IOException, InterruptedException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/random");
        uriBuilder.addParameter("apiKey", apiKeyS);
        uriBuilder.addParameter("number", "1");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> jsonBody = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        RandomRecipeObject randomRecipeObject = mapper.readValue(jsonBody.body(), RandomRecipeObject.class);
        return toDTO(randomRecipeObject.recipes.get(0));
    }

    public RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(recipe.id, recipe.title, recipe.extendedIngredients,
                recipe.instructions.replaceAll("<[^>]*>", ""),
                recipe.image,recipeInFavorites(recipe.id),
                recipe.summary.replaceAll("<[^>]*>", ""), recipe.servings);
    }

    public boolean addRecipeToFavorite(RecipeDTO newRecipe) {
        repo.AddFavorite(newRecipe.id(), newRecipe.title(), newRecipe.image());
        return true;
    }

    public boolean recipeInFavorites(Integer id) {
        return repo.recipeInFavoriets(id);
    }

    public ListResponseSearchByName searchRecipeByName(String query) throws IOException, InterruptedException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/complexSearch");
        uriBuilder.addParameter("apiKey", apiKeyS);
        uriBuilder.addParameter("number", "3");
        uriBuilder.addParameter("query", query);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> jsonString = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ListResponseSearchByName listResponseSearchByName = mapper.readValue(jsonString.body(), ListResponseSearchByName.class);
        return listResponseSearchByName;

    }


    public RecipeDTO getRecipeById(String id) throws IOException, InterruptedException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/" + id + "/information");
        uriBuilder.addParameter("apiKey", apiKeyS);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Recipe recipe = mapper.readValue(response.body(), Recipe.class);
        recipe.setInstructions(recipe.instructions.replaceAll("<[^>]*>", ""));
        return toDTO(recipe);

    }

    public void removeRecipe(int recipeID) {
            repo.removeRecipe(recipeID);
    }


    public List getFavorites() {
       List rS =new ArrayList<ResponseSearchByName>();
       repo.getFavorites().forEach(rS::add);
       return rS;
    }

    public List<ResponseSearchByName> searchRecipeByIngredients(String query) throws IOException, InterruptedException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/findByIngredients");
        uriBuilder.addParameter("apiKey", apiKeyS);
        uriBuilder.addParameter("number", "3");
        uriBuilder.addParameter("ingredients", query);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> jsonString = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        List<ResponseSearchByName> responseSearchByNameList = mapper.readValue(jsonString.body(), new TypeReference<List<ResponseSearchByName>>(){});
        return responseSearchByNameList;
    }
}
