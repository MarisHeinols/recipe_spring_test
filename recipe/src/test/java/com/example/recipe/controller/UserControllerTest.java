package com.example.recipe.controller;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;
import com.example.recipe.entity.User;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.service.implementation.UserServicesImpl;
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
    private UserServicesImpl services;
    @MockBean
    private RecipesRepository recipesRepository;
    @MockBean
    private CommentRepository commentRepository;

    private User user;
    private List<User> users;

    @BeforeEach
    public void init(){
        user = createUser();
        users = createUserList(user);
    }

    @Test
    public void getUserTest() throws Exception{

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

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/all")
                        .content(asJsonString(users))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteUserTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteUserTestInvalid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/null")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void updateUserTest() throws Exception{

        when(services.updateUser(anyLong(),any())).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void updateUserTestInvalid() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/null?name=John")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void addUserTest() throws Exception{

        when(services.addUser(any())).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(user))
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

    public static User createUser(){
        User user = new User();
        return user;
    }
    public List<User> createUserList(User user){
        List<User> list = new ArrayList<>();
        
        list.add(user);
        list.add(user);
        return list;
    }
}
