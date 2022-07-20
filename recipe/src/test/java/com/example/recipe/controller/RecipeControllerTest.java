package com.example.recipe.controller;


import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.implementation.RecipeServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void getRecipeByUserIdTest() throws Exception{
        Recipe recipe = createRecipe();

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
        Recipe recipe = createRecipe();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/null")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void getAllRecipesByUserIdTest() throws Exception{
        Recipe recipe = createRecipe();
        List<Recipe> recipeList = createRecipeList(recipe);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/byUser/1")
                        .content(asJsonString(recipeList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteRecipeTest() throws Exception{
        Recipe recipe = createRecipe();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void deleteRecipeTestInvalid() throws Exception{
        Recipe recipe = createRecipe();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/null")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void updateUserTest() throws Exception{
        Recipe recipe = createRecipe();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1?name=test&text=test")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void updateUserTestInvalid() throws Exception{
        Recipe recipe = createRecipe();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/null?name=test&text=test")
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void addRecipeTest() throws Exception{
        Recipe recipe = createRecipe();

        when(services.addRecipe(recipe)).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(recipe))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

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
        recipe.setId(1L);
        recipe.setName("Test");
        recipe.setRecipeText("Test recipe");
        return recipe;
    }
    public List<Recipe> createRecipeList(Recipe recipe){
        List<Recipe> list = new ArrayList<>();
        list.add(recipe);
        list.add(recipe);
        return list;
    }
}
