package se.backend.RecipeObject;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRecipeRepository {

    private FavoriteRecipeDBRepository dbRepo;

    public FavoriteRecipeRepository(FavoriteRecipeDBRepository dbRepo) {
        this.dbRepo = dbRepo;
    }

    public FavoriteRecipe AddFavorite(Integer id, String title, String image) {
        FavoriteRecipe newFavRec = new FavoriteRecipe(id,title,image);
        return dbRepo.save(newFavRec);
    }

    public boolean recipeInFavoriets(Integer id) {
        return dbRepo.existsById(Long.valueOf(id));
    }

    public void removeRecipe(int recipeID) {
        dbRepo.deleteById((long) recipeID);
    }


    public Iterable<FavoriteRecipe> getFavorites() {
        return dbRepo.findAll();
    }
}
