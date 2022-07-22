package com.example.recipe.controller;


import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.implementation.RecipeServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
@ExtendWith(SpringExtension.class)
public class RecipeControllerTest {

    public static String URL = "/recipe";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RecipeController controller;
    @MockBean
    private RecipeServicesImpl services;
    @MockBean
    private RecipesRepository recipesRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private UserRepository userRepository;

    private Recipe recipe;
    private List<Recipe> recipes;

    @BeforeEach
    public void init(){
        recipe = createRecipe();
        recipes = createRecipeList(recipe);
    }

    @Test
    public void getRecipeByUserIdTest() throws Exception{

        when(services.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/1")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    
    @Test
    public void getRecipeByUserTestInvalid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/null")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getAllRecipesByUserIdTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/byUser/1")
                        .content(asJsonString(recipes))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteRecipeTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteRecipeTestInvalid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/null")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void updateRecipeTest() throws Exception{

        when(services.updateRecipe(anyLong(),any())).thenReturn(new Recipe());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void updateRecipeTestInvalid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/null")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void addRecipeTest() throws Exception{

        when(services.addRecipe(any())).thenReturn(new Recipe());

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    public static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Recipe createRecipe(){
        Recipe recipe = new Recipe();
        return recipe;
    }

    public List<Recipe> createRecipeList(Recipe recipe){
        List<Recipe> list = new ArrayList<>();

        list.add(recipe);
        list.add(recipe);
        return list;
    }
}
