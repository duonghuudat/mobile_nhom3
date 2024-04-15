package com.example.cookingguideapp.Domain.Model;

import com.example.cookingguideapp.Room.Entity.RecipeEntity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    String id;
    String title;
    String description;
    String image;
    String servings;
    int time;
    List<String> tags;
    List<Ingredient> ingredients;
    List<Step> steps;

    public Recipe() {
    }

    public Recipe(String id, String title, String description, String image, String servings, int time, List<String> tags, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.servings = servings;
        this.time = time;
        this.tags = tags;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", servings='" + servings + '\'' +
                ", time=" + time +
                ", tags=" + tags +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }

    public RecipeEntity toRecipeEntity() {
        return new RecipeEntity(this.id, this.title, this.description, this.getImage(), this.servings, this.time, this.tags, this.ingredients, this.steps);
    }


    public List<RecipeEntity> toRecipeEntityList(List<Recipe> list) {
        List<RecipeEntity> recipeEntities = new ArrayList<>();
        for (Recipe recipe : list) {
            recipeEntities.add(recipe.toRecipeEntity());
        }
        return recipeEntities;
    }


}
