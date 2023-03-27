package se.backend.Repository;
import org.springframework.data.repository.CrudRepository;
import se.backend.RecipeObject.FavoriteRecipe;

public interface FavoriteRecipeDBRepository extends CrudRepository<FavoriteRecipe,Long>{
}