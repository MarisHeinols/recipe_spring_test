package com.example.recipe.service;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class RecipeServices {

    public final RecipesRepository recipesRepository;
    public final CommentRepository commentRepository;

    public RecipeServices(RecipesRepository recipesRepository, CommentRepository commentRepository) {
        this.recipesRepository = recipesRepository;
        this.commentRepository = commentRepository;
    }



    public Recipe addRecipe(Recipe recipe) {
        recipesRepository.save(recipe);
        return recipe;
    }

    public Recipe getRecipe(Long id) {
        return recipesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    public List<Recipe> getAllRecipesByUserId(Long id) {
        return recipesRepository.findByUserId(id);
    }

    public void deleteRecipe(Long id) {
        boolean recipeExists = recipesRepository.existsById(id);
        if (!recipeExists){
            throw new IllegalStateException(
                    "Recipe with id " + id + " does not exist"
            );
        }
        List<Comment> comments = commentRepository.findByUserId(id);
        comments.forEach(comment-> commentRepository.delete(comment));
        recipesRepository.deleteById(id);
    }
    @Transactional
    public Recipe updateRecipe(Long id, String name, String text) {
        Recipe recipe = recipesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));

        if(name != null && name.length() > 0 && !Objects.equals(recipe.getName(),name)){
            recipe.setName(name);
        }
        if(text != null && text.length() > 0 && !Objects.equals(recipe.getRecipeText(),text)){
            recipe.setRecipeText(text);
        }
        return recipe;
    }
}
