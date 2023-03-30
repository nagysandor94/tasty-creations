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

    public RandomRecipeDTO getRandomRecipe() throws IOException, InterruptedException, URISyntaxException {

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
        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/complexSearch");
        uriBuilder.addParameter("apiKey", apiKeyS);
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

//    public Recipe getRecipeById(String id) throws IOException, InterruptedException {
////        HttpRequest request = HttpRequest.newBuilder()
////                .uri(URI.create("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/information"))
////                .header("X-RapidAPI-Key", apiKeyS)
////                .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
////                .method("GET", HttpRequest.BodyPublishers.noBody())
////                .build();
////        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
////        Recipe recipe = mapper.readValue(response.body(), Recipe.class);
//
////Hardcoded to prevent multiple calls to API
//        String jsonResponse = """
//                {
//                    "extendedIngredients": [
//                        {
//                            "id": 1012,
//                            "aisle": "Cheese",
//                            "image": "cottage-cheese.jpg",
//                            "name": "cottage cheese",
//                            "amount": 2,
//                            "unit": "cups",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 2,
//                                    "unitShort": "cups",
//                                    "unitLong": "cups"
//                                },
//                                "metric": {
//                                    "amount": 473.176,
//                                    "unitShort": "ml",
//                                    "unitLong": "milliliters"
//                                }
//                            }
//                        },
//                        {
//                            "id": 1123,
//                            "aisle": "Milk, Eggs, Other Dairy",
//                            "image": "egg.png",
//                            "name": "eggs",
//                            "amount": 2,
//                            "unit": "",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 2,
//                                    "unitShort": "",
//                                    "unitLong": ""
//                                },
//                                "metric": {
//                                    "amount": 2,
//                                    "unitShort": "",
//                                    "unitLong": ""
//                                }
//                            }
//                        },
//                        {
//                            "id": 10023572,
//                            "aisle": "Meat",
//                            "image": "fresh-ground-beef.jpg",
//                            "name": "ground beef",
//                            "amount": 0.5,
//                            "unit": "pound",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 0.5,
//                                    "unitShort": "lb",
//                                    "unitLong": "pounds"
//                                },
//                                "metric": {
//                                    "amount": 226.796,
//                                    "unitShort": "g",
//                                    "unitLong": "grams"
//                                }
//                            }
//                        },
//                        {
//                            "id": 7036,
//                            "aisle": "Meat",
//                            "image": "raw-pork-sausage.png",
//                            "name": "sausage",
//                            "amount": 0.5,
//                            "unit": "pound",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 0.5,
//                                    "unitShort": "lb",
//                                    "unitLong": "pounds"
//                                },
//                                "metric": {
//                                    "amount": 226.796,
//                                    "unitShort": "g",
//                                    "unitLong": "grams"
//                                }
//                            }
//                        },
//                        {
//                            "id": 10620420,
//                            "aisle": "Pasta and Rice",
//                            "image": "lasagna-noodles.jpg",
//                            "name": "lasagne noodles",
//                            "amount": 8,
//                            "unit": "ounces",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 8,
//                                    "unitShort": "oz",
//                                    "unitLong": "ounces"
//                                },
//                                "metric": {
//                                    "amount": 226.796,
//                                    "unitShort": "g",
//                                    "unitLong": "grams"
//                                }
//                            }
//                        },
//                        {
//                            "id": 10111549,
//                            "aisle": "Pasta and Rice",
//                            "image": "tomato-sauce-or-pasta-sauce.jpg",
//                            "name": "batch marinara sauce",
//                            "amount": 1,
//                            "unit": "",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 1,
//                                    "unitShort": "",
//                                    "unitLong": ""
//                                },
//                                "metric": {
//                                    "amount": 1,
//                                    "unitShort": "",
//                                    "unitLong": ""
//                                }
//                            }
//                        },
//                        {
//                            "id": 1033,
//                            "aisle": "Cheese",
//                            "image": "parmesan.jpg",
//                            "name": "parmigiano reggiano",
//                            "amount": 1,
//                            "unit": "cup",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 1,
//                                    "unitShort": "cup",
//                                    "unitLong": "cup"
//                                },
//                                "metric": {
//                                    "amount": 236.588,
//                                    "unitShort": "ml",
//                                    "unitLong": "milliliters"
//                                }
//                            }
//                        },
//                        {
//                            "id": 1001026,
//                            "aisle": "Cheese",
//                            "image": "shredded-cheese-white.jpg",
//                            "name": "mozzarella cheese",
//                            "amount": 3,
//                            "unit": "cups",
//                            "unitShort": null,
//                            "unitLong": null,
//                            "originalString": null,
//                            "metaInformation": null,
//                            "measures": {
//                                "us": {
//                                    "amount": 3,
//                                    "unitShort": "cups",
//                                    "unitLong": "cups"
//                                },
//                                "metric": {
//                                    "amount": 709.764,
//                                    "unitShort": "ml",
//                                    "unitLong": "milliliters"
//                                }
//                            }
//                        }
//                    ],
//                    "id": 247730,
//                    "title": "Lasagna",
//                    "readyInMinutes": 75,
//                    "image": "https://spoonacular.com/recipeImages/247730-556x370.jpg",
//                    "imageType": "jpg",
//                    "instructions": "Brown the meat in a large sauce pan, drain off any excess grease and mix into the marinara sauce.Boil the noodles as directed on the package.Mix the eggs, cottage cheese and parmigiano reggiano in a large bowl.Spread 1/2 cup of the meat sauce over the bottom of a baking dish*.Place a layer of noodles on top.Place 1/2 of the cottage cheese mixture on top.Place 1/3 of the mozzarella on top.Place 1/3 of the meat sauce top.Place a layer of noodles on top.Place the remaining cottage cheese mixture on top.Place 1/3 of the mozzarella on top.Place 1/3 of the meat sauce top.Place a layer of noodles on top.Place the remaining meat sauce top.Place the remaining mozzarella and parmigiano reggiano on top.Bake in a preheated 350F oven until bubbling on the sides and golden brown on top, about 30-45 minutes."
//                }
//                """;
//        Recipe recipe = mapper.readValue(jsonResponse, Recipe.class);
//    return recipe;
//    }


    public Recipe getRecipeById(String id) throws IOException, InterruptedException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("https://api.spoonacular.com/recipes/" + id + "/information");
        uriBuilder.addParameter("apiKey", apiKeyS);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriBuilder.build())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Recipe recipe = mapper.readValue(response.body(), Recipe.class);

        return recipe;

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
