package com.example.recipe.service.interfaces;

import java.util.List;

import com.example.recipe.entity.Recipe;

public interface RecipeServices {
    List<Recipe> getAllRecipes();
    List<Recipe> getAllRecipesByUserId(Long id);
    Recipe getRecipeById(Long id);
    Recipe addRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipeData);
    void deleteRecipe(Long id);

}
