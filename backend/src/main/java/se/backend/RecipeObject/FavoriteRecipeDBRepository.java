package se.backend.RecipeObject;
import org.springframework.data.repository.CrudRepository;


public interface FavoriteRecipeDBRepository extends CrudRepository<FavoriteRecipe,Long>{
}