package com.example.recipe.controller;

import com.example.recipe.entity.Recipe;
import com.example.recipe.service.RecipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

    @Autowired
    RecipeServices recipeServices;

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe){ recipeServices.addRecipe(recipe);}

    @GetMapping(path = "{id}")
    public Recipe getRecipe(@PathVariable("id")Long id){
        return recipeServices.getRecipe(id);

    }
    @GetMapping(path = "byUser/{id}")
    public List<Recipe> getAllRecipesByUserId(@PathVariable("id")Long id){

        return recipeServices.getAllRecipesByUserId(id);
    }
    @GetMapping(path = "")
    public List<Recipe> getAllRecipes(){

        return recipeServices.getAllRecipes();
    }

    @DeleteMapping(path= "{id}")
    public void deleteRecipe(@PathVariable("id")Long id){
         recipeServices.deleteRecipe(id);
    }

    @PutMapping(path = "{id}")
    public Recipe updateRecipe(@PathVariable("id")Long id,String name, String text){
        return recipeServices.updateRecipe(id,name,text);
    }

}
