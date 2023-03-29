package se.backend;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.backend.RecipeObject.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TsService {
    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    FavoriteRecipeRepository repo;

    private String apiKeyI = System.getenv("API_KEY_I");
    private String apiKeyS = System.getenv("API_KEY_S");

    public RandomRecipeDTO getRandomRecipe() throws IOException, InterruptedException {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?tags=vegetarian%2Cdessert&number=1"))
//                    .header("X-RapidAPI-Key", apiKeyI)
//                    .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
//                    .method("GET", HttpRequest.BodyPublishers.noBody())
//                    .build();
//            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            String jsonBody=response.body();


//Hardcoded Json to prevent multiple calls to external API

        String jsonBody = """
                { "recipes": [ { "vegetarian": true, "vegan": false, "glutenFree": true, "dairyFree": false, "veryHealthy": false, "cheap": false, "veryPopular": false, "sustainable": false, "lowFodmap": false, "weightWatcherSmartPoints": 81, "gaps": "no", "preparationMinutes": -1, "cookingMinutes": -1, "aggregateLikes": 22, "healthScore": 13, "creditsText": "Foodista.com – The Cooking Encyclopedia Everyone Can Edit", "license": "CC BY 3.0", "sourceName": "Foodista", "pricePerServing": 538.58, "extendedIngredients": [ { "id": 1011077, "aisle": "Milk, Eggs, Other Dairy", "image": "milk.png", "consistency": "LIQUID", "name": "milk", "nameClean": "whole milk", "original": "1 1/4 cups whole milk", "originalName": "whole milk", "amount": 1.25, "unit": "cups", "meta": [ "whole" ], "measures": { "us": { "amount": 1.25, "unitShort": "cups", "unitLong": "cups" }, "metric": { "amount": 295.735, "unitShort": "ml", "unitLong": "milliliters" } } }, { "id": 1082047, "aisle": "Spices and Seasonings", "image": "salt.jpg", "consistency": "SOLID", "name": "kosher salt", "nameClean": "kosher salt", "original": "1/8 teaspoon kosher salt", "originalName": "kosher salt", "amount": 0.125, "unit": "teaspoon", "meta": [], "measures": { "us": { "amount": 0.125, "unitShort": "tsps", "unitLong": "teaspoons" }, "metric": { "amount": 0.125, "unitShort": "tsps", "unitLong": "teaspoons" } } }, { "id": 10719335, "aisle": "Baking", "image": "sugar-in-bowl.png", "consistency": "SOLID", "name": "granulated sugar", "nameClean": "granulated sugar", "original": "1/2 cup granulated sugar, divided", "originalName": "granulated sugar, divided", "amount": 0.5, "unit": "cup", "meta": [ "divided" ], "measures": { "us": { "amount": 0.5, "unitShort": "cups", "unitLong": "cups" }, "metric": { "amount": 118.294, "unitShort": "ml", "unitLong": "milliliters" } } }, { "id": 93622, "aisle": "Baking", "image": "vanilla.jpg", "consistency": "SOLID", "name": "vanilla bean", "nameClean": "vanilla bean", "original": "1 vanilla bean", "originalName": "vanilla bean", "amount": 1.0, "unit": "", "meta": [], "measures": { "us": { "amount": 1.0, "unitShort": "", "unitLong": "" }, "metric": { "amount": 1.0, "unitShort": "", "unitLong": "" } } }, { "id": 1125, "aisle": "Milk, Eggs, Other Dairy", "image": "egg-yolk.jpg", "consistency": "SOLID", "name": "egg yolks", "nameClean": "egg yolk", "original": "4 egg yolks", "originalName": "egg yolks", "amount": 4.0, "unit": "", "meta": [], "measures": { "us": { "amount": 4.0, "unitShort": "", "unitLong": "" }, "metric": { "amount": 4.0, "unitShort": "", "unitLong": "" } } }, { "id": 1053, "aisle": "Milk, Eggs, Other Dairy", "image": "fluid-cream.jpg", "consistency": "LIQUID", "name": "heavy cream", "nameClean": "cream", "original": "2 cups heavy cream", "originalName": "heavy cream", "amount": 2.0, "unit": "cups", "meta": [], "measures": { "us": { "amount": 2.0, "unitShort": "cups", "unitLong": "cups" }, "metric": { "amount": 473.176, "unitShort": "ml", "unitLong": "milliliters" } } }, { "id": 1145, "aisle": "Milk, Eggs, Other Dairy", "image": "butter-sliced.jpg", "consistency": "SOLID", "name": "butter", "nameClean": "unsalted butter", "original": "6 tablespoons unsalted butter", "originalName": "unsalted butter", "amount": 6.0, "unit": "tablespoons", "meta": [ "unsalted" ], "measures": { "us": { "amount": 6.0, "unitShort": "Tbsps", "unitLong": "Tbsps" }, "metric": { "amount": 6.0, "unitShort": "Tbsps", "unitLong": "Tbsps" } } }, { "id": 19334, "aisle": "Baking", "image": "light-brown-sugar.jpg", "consistency": "SOLID", "name": "brown sugar", "nameClean": "golden brown sugar", "original": "1/4 cup light brown sugar", "originalName": "light brown sugar", "amount": 0.25, "unit": "cup", "meta": [ "light" ], "measures": { "us": { "amount": 0.25, "unitShort": "cups", "unitLong": "cups" }, "metric": { "amount": 59.147, "unitShort": "ml", "unitLong": "milliliters" } } }, { "id": 9040, "aisle": "Produce", "image": "bananas.jpg", "consistency": "SOLID", "name": "bananas", "nameClean": "banana", "original": "2 bananas, chopped", "originalName": "bananas, chopped", "amount": 2.0, "unit": "", "meta": [ "chopped" ], "measures": { "us": { "amount": 2.0, "unitShort": "", "unitLong": "" }, "metric": { "amount": 2.0, "unitShort": "", "unitLong": "" } } }, { "id": 11114037, "aisle": "Alcoholic Beverages", "image": "rum-dark.jpg", "consistency": "LIQUID", "name": "rum", "nameClean": "rum", "original": "1 tablespoon rum, optional", "originalName": "rum, optional", "amount": 1.0, "unit": "tablespoon", "meta": [], "measures": { "us": { "amount": 1.0, "unitShort": "Tbsp", "unitLong": "Tbsp" }, "metric": { "amount": 1.0, "unitShort": "Tbsp", "unitLong": "Tbsp" } } } ], "id": 634237, "title": "Bananas Foster Ice Cream", "readyInMinutes": 45, "servings": 2, "sourceUrl": "https://www.foodista.com/recipe/TFJJTPM2/bananas-foster-ice-cream", "image": "https://spoonacular.com/recipeImages/634237-556x370.jpg", "imageType": "jpg", "summary": "You can never have too many dessert recipes, so give Bananas Foster Ice Cream a try. This recipe serves 2 and costs $5.39 per serving. One portion of this dish contains approximately <b>19g of protein</b>, <b>135g of fat</b>, and a total of <b>1743 calories</b>. <b>Summer</b> will be even more special with this recipe. This recipe from Foodista requires milk, kosher salt, butter, and heavy cream. A few people really liked this Cajun dish. 22 people have made this recipe and would make it again. It is a good option if you're following a <b>gluten free and lacto ovo vegetarian</b> diet. From preparation to the plate, this recipe takes roughly <b>45 minutes</b>. Taking all factors into account, this recipe <b>earns a spoonacular score of 57%</b>, which is solid. Users who liked this recipe also liked <a href=\\"https://spoonacular.com/recipes/bananas-foster-with-ice-cream-166685\\">Bananas Foster with Ice Cream</a>, <a href=\\"https://spoonacular.com/recipes/bananas-foster-ice-cream-570890\\">Bananas Foster Ice Cream</a>, and <a href=\\"https://spoonacular.com/recipes/vegan-bananas-foster-ice-cream-249237\\">Vegan Bananas Foster Ice Cream</a>.", "cuisines": [ "Creole", "Cajun" ], "dishTypes": [ "dessert" ], "diets": [ "gluten free", "lacto ovo vegetarian" ], "occasions": [ "summer" ], "instructions": "In a heavy bottomed saucepan, heat the milk, salt and 1/4 cup sugar over med-low heat until steaming but not boiling and the sugar is dissolved. Add the vanilla bean and vanilla bean caviar to the mixture. Cover and let steep for 1 hour.\\nIn a small saute pan, melt the butter, brown sugar and salt. Bring to a boil stirring constantly until the mixture turns a deep golden brown. Add the bananas and continue to saute the mixture for approximately 5 more minutes or until the bananas soften. Stir in the rum and remove from heat. Let the mixture cool to room temperature.\\nFill a large bowl with ice and water. Place a small bowl in the ice water and add the heavy cream. Top the small bowl with a wire strainer and set aside.\\nIn a small bowl, whisk together the egg yolks and remaining 1/4 cup sugar until light in color and ribbons begin to form.\\nReheat the milk mixture over med-low heat and slowly add to egg mixture whisking constantly so the eggs do not scramble. Return the milk/egg mixture to the pan and heat. Stir constantly with a heat resistant spatula being sure to scrape the sides and bottom until it begins to thicken (it will coat the back of the spatula).\\nStrain the milk mixture into the cream, remove the strainer and stir until combined. Cover with plastic wrap and chill in the refrigerator for 2 hours or overnight.\\nPour the mixture into the freezer can of an electric ice cream maker and freeze according to the manufacturers instructions. When the mixture reaches soft serve consistency, add the banana mixture and continue churning until combined.\\nTransfer the ice cream to a freezer safe container and freeze.", "analyzedInstructions": [ { "name": "", "steps": [ { "number": 1, "step": "In a heavy bottomed saucepan, heat the milk, salt and 1/4 cup sugar over med-low heat until steaming but not boiling and the sugar is dissolved.", "ingredients": [ { "id": 19335, "name": "sugar", "localizedName": "sugar", "image": "sugar-in-bowl.png" }, { "id": 1077, "name": "milk", "localizedName": "milk", "image": "milk.png" }, { "id": 2047, "name": "salt", "localizedName": "salt", "image": "salt.jpg" } ], "equipment": [ { "id": 404669, "name": "sauce pan", "localizedName": "sauce pan", "image": "sauce-pan.jpg" } ] }, { "number": 2, "step": "Add the vanilla bean and vanilla bean caviar to the mixture. Cover and let steep for 1 hour.", "ingredients": [ { "id": 93622, "name": "vanilla bean", "localizedName": "vanilla bean", "image": "vanilla.jpg" }, { "id": 15012, "name": "caviar", "localizedName": "caviar", "image": "caviar.png" } ], "equipment": [], "length": { "number": 60, "unit": "minutes" } }, { "number": 3, "step": "In a small saute pan, melt the butter, brown sugar and salt. Bring to a boil stirring constantly until the mixture turns a deep golden brown.", "ingredients": [ { "id": 19334, "name": "brown sugar", "localizedName": "brown sugar", "image": "dark-brown-sugar.png" }, { "id": 1001, "name": "butter", "localizedName": "butter", "image": "butter-sliced.jpg" }, { "id": 2047, "name": "salt", "localizedName": "salt", "image": "salt.jpg" } ], "equipment": [ { "id": 404645, "name": "frying pan", "localizedName": "frying pan", "image": "pan.png" } ] }, { "number": 4, "step": "Add the bananas and continue to saute the mixture for approximately 5 more minutes or until the bananas soften. Stir in the rum and remove from heat.", "ingredients": [ { "id": 9040, "name": "banana", "localizedName": "banana", "image": "bananas.jpg" }, { "id": 11114037, "name": "rum", "localizedName": "rum", "image": "rum-dark.jpg" } ], "equipment": [], "length": { "number": 5, "unit": "minutes" } }, { "number": 5, "step": "Let the mixture cool to room temperature.", "ingredients": [], "equipment": [] }, { "number": 6, "step": "Fill a large bowl with ice and water.", "ingredients": [ { "id": 14412, "name": "water", "localizedName": "water", "image": "water.png" }, { "id": 10014412, "name": "ice", "localizedName": "ice", "image": "ice-cubes.png" } ], "equipment": [ { "id": 404783, "name": "bowl", "localizedName": "bowl", "image": "bowl.jpg" } ] }, { "number": 7, "step": "Place a small bowl in the ice water and add the heavy cream. Top the small bowl with a wire strainer and set aside.", "ingredients": [ { "id": 1053, "name": "heavy cream", "localizedName": "heavy cream", "image": "fluid-cream.jpg" }, { "id": 14412, "name": "water", "localizedName": "water", "image": "water.png" } ], "equipment": [ { "id": 405600, "name": "sieve", "localizedName": "sieve", "image": "strainer.png" }, { "id": 404783, "name": "bowl", "localizedName": "bowl", "image": "bowl.jpg" } ] }, { "number": 8, "step": "In a small bowl, whisk together the egg yolks and remaining 1/4 cup sugar until light in color and ribbons begin to form.", "ingredients": [ { "id": 1125, "name": "egg yolk", "localizedName": "egg yolk", "image": "egg-yolk.jpg" }, { "id": 19335, "name": "sugar", "localizedName": "sugar", "image": "sugar-in-bowl.png" } ], "equipment": [ { "id": 404661, "name": "whisk", "localizedName": "whisk", "image": "whisk.png" }, { "id": 404783, "name": "bowl", "localizedName": "bowl", "image": "bowl.jpg" } ] }, { "number": 9, "step": "Reheat the milk mixture over med-low heat and slowly add to egg mixture whisking constantly so the eggs do not scramble. Return the milk/egg mixture to the pan and heat. Stir constantly with a heat resistant spatula being sure to scrape the sides and bottom until it begins to thicken (it will coat the back of the spatula).", "ingredients": [ { "id": 1123, "name": "egg", "localizedName": "egg", "image": "egg.png" }, { "id": 1077, "name": "milk", "localizedName": "milk", "image": "milk.png" } ], "equipment": [ { "id": 404661, "name": "whisk", "localizedName": "whisk", "image": "whisk.png" }, { "id": 404642, "name": "spatula", "localizedName": "spatula", "image": "spatula-or-turner.jpg" }, { "id": 404645, "name": "frying pan", "localizedName": "frying pan", "image": "pan.png" } ] }, { "number": 10, "step": "Strain the milk mixture into the cream, remove the strainer and stir until combined. Cover with plastic wrap and chill in the refrigerator for 2 hours or overnight.", "ingredients": [ { "id": 1053, "name": "cream", "localizedName": "cream", "image": "fluid-cream.jpg" }, { "id": 1077, "name": "milk", "localizedName": "milk", "image": "milk.png" }, { "id": 10018364, "name": "wrap", "localizedName": "wrap", "image": "flour-tortilla.jpg" } ], "equipment": [ { "id": 404730, "name": "plastic wrap", "localizedName": "plastic wrap", "image": "plastic-wrap.jpg" }, { "id": 405600, "name": "sieve", "localizedName": "sieve", "image": "strainer.png" } ], "length": { "number": 120, "unit": "minutes" } }, { "number": 11, "step": "Pour the mixture into the freezer can of an electric ice cream maker and freeze according to the manufacturers instructions. When the mixture reaches soft serve consistency, add the banana mixture and continue churning until combined.", "ingredients": [ { "id": 19095, "name": "ice cream", "localizedName": "ice cream", "image": "vanilla-ice-cream.png" }, { "id": 9040, "name": "banana", "localizedName": "banana", "image": "bananas.jpg" } ], "equipment": [ { "id": 404791, "name": "ice cream machine", "localizedName": "ice cream machine", "image": "ice-cream-machine.jpg" } ] }, { "number": 12, "step": "Transfer the ice cream to a freezer safe container and freeze.", "ingredients": [ { "id": 19095, "name": "ice cream", "localizedName": "ice cream", "image": "vanilla-ice-cream.png" } ], "equipment": [] } ] } ], "originalId": null, "spoonacularSourceUrl": "https://spoonacular.com/bananas-foster-ice-cream-634237" } ] }""";
        RandomRecipeObject randomRecipeObject = mapper.readValue(jsonBody, RandomRecipeObject.class);
        System.out.println(randomRecipeObject);
        return toDTO(randomRecipeObject.recipes.get(0));
    }

    public RandomRecipeDTO toDTO(Recipe recipe) {
        return new RandomRecipeDTO(recipe.id, recipe.title, recipe.extendedIngredients,
                                   recipe.instructions.replaceAll("<[^>]*>", ""), recipe.image,recipeInFavorites(recipe.id));
    }

    public boolean addRecipeToFavorite(RandomRecipeDTO newRecipe) {
        repo.AddFavorite(newRecipe.id(), newRecipe.title(), newRecipe.image());
        return true;
    }

    public boolean recipeInFavorites(Integer id) {
        return repo.recipeInFavoriets(id);
    }

    public ListResponseSearchByName searchRecipeByName(String query) throws IOException, InterruptedException, URISyntaxException {
        String apiKey = "9d5d4fbeee6a4046a0a0bda36a37fcec";

        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/complexSearch");
        uriBuilder.addParameter("apiKey", apiKey);
        uriBuilder.addParameter("number", "3");
        uriBuilder.addParameter("query", query);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

//
//        String jsonResponse = """
//                {
//                    "results": [
//                        {
//                            "id": "247730",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/247730-312x231.jpg"
//                        },
//                        {
//                            "id": "544052",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/544052-312x231.jpg"
//                        },
//                        {
//                            "id": "808433",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/808433-312x231.jpg"
//                        },
//                        {
//                            "id": "107361",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/107361-312x231.png"
//                        },
//                        {
//                            "id": "485445",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/485445-312x231.jpg"
//                        },
//                        {
//                            "id": "389775",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/389775-312x231.jpeg"
//                        },
//                        {
//                            "id": "326698",
//                            "title": "Lasagna",
//                            "image": "https://spoonacular.com/recipeImages/326698-312x231.jpeg"
//                        },
//                        {
//                            "id": "566186",
//                            "title": "Lasagna Dip",
//                            "image": "https://spoonacular.com/recipeImages/566186-312x231.jpg"
//                        },
//                        {
//                            "id": "627701",
//                            "title": "Lasagna Dip",
//                            "image": "https://spoonacular.com/recipeImages/627701-312x231.jpg"
//                        },
//                        {
//                            "id": "248684",
//                            "title": "Lasagna Dip",
//                            "image": "https://spoonacular.com/recipeImages/248684-312x231.jpg"
//                        }
//                    ]
//                }
//                """;
        HttpResponse<String> jsonString = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ListResponseSearchByName listResponseSearchByName = mapper.readValue(jsonString.body(), ListResponseSearchByName.class);
       // ListResponseSearchByName listResponseSearchByName = objectMapper.readValue(jsonResponse, ListResponseSearchByName.class);
        return listResponseSearchByName;

    }

    public Recipe getRecipeById(String id) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/information"))
//                .header("X-RapidAPI-Key", apiKeyS)
//                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        Recipe recipe = mapper.readValue(response.body(), Recipe.class);

//Hardcoded to prevent multiple calls to API
        String jsonResponse = """
                {
                    "extendedIngredients": [
                        {
                            "id": 1012,
                            "aisle": "Cheese",
                            "image": "cottage-cheese.jpg",
                            "name": "cottage cheese",
                            "amount": 2,
                            "unit": "cups",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 2,
                                    "unitShort": "cups",
                                    "unitLong": "cups"
                                },
                                "metric": {
                                    "amount": 473.176,
                                    "unitShort": "ml",
                                    "unitLong": "milliliters"
                                }
                            }
                        },
                        {
                            "id": 1123,
                            "aisle": "Milk, Eggs, Other Dairy",
                            "image": "egg.png",
                            "name": "eggs",
                            "amount": 2,
                            "unit": "",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 2,
                                    "unitShort": "",
                                    "unitLong": ""
                                },
                                "metric": {
                                    "amount": 2,
                                    "unitShort": "",
                                    "unitLong": ""
                                }
                            }
                        },
                        {
                            "id": 10023572,
                            "aisle": "Meat",
                            "image": "fresh-ground-beef.jpg",
                            "name": "ground beef",
                            "amount": 0.5,
                            "unit": "pound",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 0.5,
                                    "unitShort": "lb",
                                    "unitLong": "pounds"
                                },
                                "metric": {
                                    "amount": 226.796,
                                    "unitShort": "g",
                                    "unitLong": "grams"
                                }
                            }
                        },
                        {
                            "id": 7036,
                            "aisle": "Meat",
                            "image": "raw-pork-sausage.png",
                            "name": "sausage",
                            "amount": 0.5,
                            "unit": "pound",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 0.5,
                                    "unitShort": "lb",
                                    "unitLong": "pounds"
                                },
                                "metric": {
                                    "amount": 226.796,
                                    "unitShort": "g",
                                    "unitLong": "grams"
                                }
                            }
                        },
                        {
                            "id": 10620420,
                            "aisle": "Pasta and Rice",
                            "image": "lasagna-noodles.jpg",
                            "name": "lasagne noodles",
                            "amount": 8,
                            "unit": "ounces",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 8,
                                    "unitShort": "oz",
                                    "unitLong": "ounces"
                                },
                                "metric": {
                                    "amount": 226.796,
                                    "unitShort": "g",
                                    "unitLong": "grams"
                                }
                            }
                        },
                        {
                            "id": 10111549,
                            "aisle": "Pasta and Rice",
                            "image": "tomato-sauce-or-pasta-sauce.jpg",
                            "name": "batch marinara sauce",
                            "amount": 1,
                            "unit": "",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 1,
                                    "unitShort": "",
                                    "unitLong": ""
                                },
                                "metric": {
                                    "amount": 1,
                                    "unitShort": "",
                                    "unitLong": ""
                                }
                            }
                        },
                        {
                            "id": 1033,
                            "aisle": "Cheese",
                            "image": "parmesan.jpg",
                            "name": "parmigiano reggiano",
                            "amount": 1,
                            "unit": "cup",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 1,
                                    "unitShort": "cup",
                                    "unitLong": "cup"
                                },
                                "metric": {
                                    "amount": 236.588,
                                    "unitShort": "ml",
                                    "unitLong": "milliliters"
                                }
                            }
                        },
                        {
                            "id": 1001026,
                            "aisle": "Cheese",
                            "image": "shredded-cheese-white.jpg",
                            "name": "mozzarella cheese",
                            "amount": 3,
                            "unit": "cups",
                            "unitShort": null,
                            "unitLong": null,
                            "originalString": null,
                            "metaInformation": null,
                            "measures": {
                                "us": {
                                    "amount": 3,
                                    "unitShort": "cups",
                                    "unitLong": "cups"
                                },
                                "metric": {
                                    "amount": 709.764,
                                    "unitShort": "ml",
                                    "unitLong": "milliliters"
                                }
                            }
                        }
                    ],
                    "id": 247730,
                    "title": "Lasagna",
                    "readyInMinutes": 75,
                    "image": "https://spoonacular.com/recipeImages/247730-556x370.jpg",
                    "imageType": "jpg",
                    "instructions": "Brown the meat in a large sauce pan, drain off any excess grease and mix into the marinara sauce.Boil the noodles as directed on the package.Mix the eggs, cottage cheese and parmigiano reggiano in a large bowl.Spread 1/2 cup of the meat sauce over the bottom of a baking dish*.Place a layer of noodles on top.Place 1/2 of the cottage cheese mixture on top.Place 1/3 of the mozzarella on top.Place 1/3 of the meat sauce top.Place a layer of noodles on top.Place the remaining cottage cheese mixture on top.Place 1/3 of the mozzarella on top.Place 1/3 of the meat sauce top.Place a layer of noodles on top.Place the remaining meat sauce top.Place the remaining mozzarella and parmigiano reggiano on top.Bake in a preheated 350F oven until bubbling on the sides and golden brown on top, about 30-45 minutes."
                }
                """;
        Recipe recipe = mapper.readValue(jsonResponse, Recipe.class);
    return recipe;
    }


//    public Recipe getRecipeById(String id) throws IOException, InterruptedException, URISyntaxException {
//        String apiKey = "9d5d4fbeee6a4046a0a0bda36a37fcec";
//
//        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/" + id + "/information");
//        uriBuilder.addParameter("apiKey", apiKey);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(uriBuilder.build())
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        Recipe recipe = mapper.readValue(response.body(), Recipe.class);
//
//        return recipe;
//
//    }

    public void removeRecipe(int recipeID) {
            repo.removeRecipe(recipeID);
    }


    public List getFavorites() {
       List rS =new ArrayList<ResponseSearchByName>();
       repo.getFavorites().forEach(rS::add);
       return rS;
    }



    public List<ResponseSearchByName> searchRecipeByIngredients(String query) throws IOException, InterruptedException, URISyntaxException {
        String apiKey = "9d5d4fbeee6a4046a0a0bda36a37fcec";

        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/findByIngredients");
        uriBuilder.addParameter("apiKey", apiKey);
        uriBuilder.addParameter("number", "2");
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
