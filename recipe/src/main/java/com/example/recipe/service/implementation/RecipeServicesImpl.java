package com.example.recipe.service.implementation;

import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.service.interfaces.RecipeServices;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RecipeServicesImpl implements RecipeServices {

    public final RecipesRepository recipesRepository;

    public RecipeServicesImpl(RecipesRepository recipesRepository) {

		super();
		this.recipesRepository = recipesRepository;
	}

    @Override
    public List<Recipe> getAllRecipes() {

        return recipesRepository.findAll();
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(Long id) {

        return recipesRepository.findByUserId(id);
    }

    @Override
    public Recipe getRecipeById(Long id) {
        
        return recipesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipesRepository.save(recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe recipeData) {

        Recipe recipe = recipesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(id.toString()));
		
		recipe.setRecipeName(recipeData.getRecipeName());
		recipe.setRecipeText(recipeData.getRecipeText());
		recipe.setComments(recipeData.getComments());
        recipe.setUser(recipeData.getUser());
        recipesRepository.save(recipe);

		return recipe;
    }

    @Override
    public void deleteRecipe(Long id) {
        
        boolean recipeExists = recipesRepository.existsById(id);
        if (!recipeExists){
            throw new IllegalStateException(
                    "Recipe with id " + id + " does not exist"
            );
        }
        recipesRepository.deleteById(id);
    }
}
