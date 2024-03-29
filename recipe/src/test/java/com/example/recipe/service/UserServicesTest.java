package com.example.recipe.service;


import com.example.recipe.entity.User;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.implementation.UserServicesImpl;

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
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServicesTest {

    @Mock
    private UserRepository repository;
    @Mock
    private RecipesRepository recipesRepository;
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private UserServicesImpl services;

    private User user;
    private List<User> users;

    @Before("")
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void init(){
        user = createUser();
        users = createUserList(user);
    }

    @Test
    void addUserTest(){
        when(repository.save(user)).thenReturn(user);
        User testUser = services.addUser(user);
        assertEquals(user, testUser);

    }

    @Test
    void getUserByIdTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        User testUser = services.getUserById(anyLong());
        assertEquals(user, testUser);
        verify(repository).findById(anyLong());

    }

    @Test
    void getUserByIdTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> services.getUserById(anyLong()));
    }
    
    @Test
    void updateUserTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        User testUser = services.updateUser(anyLong(), user);
        assertEquals(user, testUser);
    }

    @Test
    void updateUserTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(javax.persistence.EntityNotFoundException.class,
                ()-> services.updateUser(anyLong(),user));
    }

    @Test
    void deleteUserTest(){
        when(repository.existsById(anyLong())).thenReturn(true);
        services.deleteUser(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteUserTestInvalid(){
        when(repository.existsById(null)).thenReturn(false);
        Assertions.assertThrows(IllegalStateException.class,
                ()-> services.deleteUser(anyLong()));
    }

    @Test
    void getUsersTest() throws Exception{
        when(repository.findAll()).thenReturn(users);
        assertEquals(users, services.getAllUsers());
    }

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("Test");
        return user;
    }
    
    public List<User> createUserList(User user){
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        return list;
    }

}
