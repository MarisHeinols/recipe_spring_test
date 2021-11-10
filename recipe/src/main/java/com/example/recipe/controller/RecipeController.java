package com.example.recipe.controller;

import com.example.recipe.entity.Recipe;
import com.example.recipe.service.RecipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RecipeController {

    @Autowired
    RecipeServices recipeServices;

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe){ recipeServices.addRecipe(recipe);
    }
}
