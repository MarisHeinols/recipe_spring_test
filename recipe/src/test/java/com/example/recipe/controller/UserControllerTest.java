package com.example.recipe.controller;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;
import com.example.recipe.entity.User;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.service.UserServices;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControler.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    public static String URL = "/user";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserControler controller;
    @MockBean
    private UserServices services;
    @MockBean
    private RecipesRepository recipesRepository;
    @MockBean
    private CommentRepository commentRepository;


    @Test
    public void getUserTest() throws Exception{
        User user = createUser();

        when(services.getUserById(anyLong())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/1")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void getUserTestInvalid() throws Exception{
        User user = createUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/null")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(services, times(0)).getUserById(null);
    }
    @Test
    public void getAllUsersTest() throws Exception{
        User user = createUser();
        List<User> userList = createUserList(user);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/all")
                        .content(asJsonString(userList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteUserTest() throws Exception{
        User user = createUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void deleteUserTestInvalid() throws Exception{
        User user = createUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/null")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void updateUserTest() throws Exception{
        User user = createUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1?name=John")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void updateUserTestInvalid() throws Exception{
        User user = createUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/null?name=John")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void addUserTest() throws Exception{
        User user = createUser();

        when(services.addUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    public static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static User createUser(){
        List<Recipe> recipes = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("Test");

        user.setRecipes(recipes);
        user.setComments(comments);
        return user;
    }
    public List<User> createUserList(User user){
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        return list;
    }
}
