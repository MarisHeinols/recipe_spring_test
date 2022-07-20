package com.example.recipe.controller;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.entity.Recipe;
import com.example.recipe.service.interfaces.RecipeServices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

    @Autowired
    private ModelMapper modelMapper;
    private RecipeServices recipeServices;

    public RecipeController(RecipeServices recipeServices){
        super();
        this.recipeServices = recipeServices;
    }

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe){ 
        recipeServices.addRecipe(recipe);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("id")Long id){
        Recipe recipe = recipeServices.getRecipeById(id);
        RecipeDto recipeResponse = modelMapper.map(recipe, RecipeDto.class);
        return ResponseEntity.ok().body(recipeResponse);

    }
    @GetMapping(path = "byUser/{id}")
    public List<RecipeDto> getAllRecipesByUserId(@PathVariable("id")Long id){
        return recipeServices.getAllRecipesByUserId(id).stream().map(post -> modelMapper.map(post, RecipeDto.class))
				.collect(Collectors.toList());
    }
    @GetMapping(path = "")
    public List<RecipeDto> getAllRecipes(){
        return recipeServices.getAllRecipes().stream().map(post -> modelMapper.map(post, RecipeDto.class))
        .collect(Collectors.toList());
    }

    @DeleteMapping(path= "{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id")Long id){
        recipeServices.deleteRecipe(id);
		return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable long id, @RequestBody RecipeDto recipeDto){
        Recipe userRequest = modelMapper.map(recipeDto, Recipe.class);

		Recipe recipe = recipeServices.updateRecipe(id, userRequest);

		RecipeDto recipeResponse = modelMapper.map(recipe, RecipeDto.class);

		return ResponseEntity.ok().body(recipeResponse);
    }

}
