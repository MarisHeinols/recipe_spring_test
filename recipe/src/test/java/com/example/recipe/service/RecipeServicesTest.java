package com.example.recipe.service;

import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.implementation.RecipeServicesImpl;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeServicesTest {
    @Mock
    private RecipesRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private RecipeServicesImpl services;

    private Recipe recipe;
    private List<Recipe> recipes;

    @Before("")
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void init(){
        recipe = createRecipe();
        recipes = createRecipeList(recipe);
    }

    @Test
    void addRecipeTest(){
        when(repository.save(recipe)).thenReturn(recipe);
        Recipe testRecipe = services.addRecipe(recipe);
        assertEquals(recipe, testRecipe);
    }

    @Test
    void addRecipeTestInvalid() throws Exception{
        when(repository.save(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ClassCastException.class,
                ()-> services.addRecipe(null));

    }

    @Test
    void getRecipeTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(recipe));
        Recipe testRecipe = services.getRecipeById(anyLong());
        assertEquals(recipe, testRecipe);
        verify(repository).findById(anyLong());

    }

    @Test
    void getRecipeTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> services.getRecipeById(anyLong()));
    }

    @Test
    void updateRecipeTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(recipe));
        Recipe testRecipe = services.updateRecipe(anyLong(), recipe);
        assertEquals(recipe, testRecipe);
    }

    @Test
    void updateRecipeTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(javax.persistence.EntityNotFoundException.class,
                ()-> services.updateRecipe(anyLong(),recipe));
    }

    @Test
    void deleteRecipeTest(){
        when(repository.existsById(anyLong())).thenReturn(true);
        services.deleteRecipe(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteRecipeTestInvalid(){
        when(repository.existsById(null)).thenReturn(false);
        Assertions.assertThrows(IllegalStateException.class,
                ()-> services.deleteRecipe(anyLong()));
    }

    @Test
    void getAllRecipesByUserIdTest() throws Exception{
        when(repository.findByUserId(anyLong())).thenReturn(recipes);
        assertEquals(recipes, services.getAllRecipesByUserId(anyLong()));
    }
    @Test
    void getAllRecipes() throws Exception{
        when(repository.findAll()).thenReturn(recipes);
        assertEquals(recipes, services.getAllRecipes());
    }

    public static Recipe createRecipe(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setRecipeName("Test");
        return recipe;
    }

    public List<Recipe> createRecipeList(Recipe recipe){
        List<Recipe> list = new ArrayList<>();
        list.add(recipe);
        list.add(recipe);
        return list;
    }
}
