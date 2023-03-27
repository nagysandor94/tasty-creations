package se.backend.RecipeObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="favorite_recipes")
public class FavoriteRecipe {
    @Id
    @Column(name="id",nullable = false)
    private long id;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="image",nullable = false)
    private String image;

    public FavoriteRecipe(long id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public FavoriteRecipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
