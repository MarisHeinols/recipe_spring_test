package com.example.recipe.service;

import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.RecipesRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeServices {

    public final RecipesRepository recipesRepository;

    public RecipeServices(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }


    public void addRecipe(Recipe recipe) {

    }
}
