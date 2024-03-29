package com.example.recipe.service;

import com.example.recipe.entity.Comment;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.implementation.CommentServicesImpl;

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
public class CommentServicesTest {
    @Mock
    private CommentRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RecipesRepository recipesRepository;
    @InjectMocks
    private CommentServicesImpl services;

    private Comment comment;
    private List<Comment> comments;

    @Before("")
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @BeforeEach
    public void init(){
        comment = createComment();
        comments = createCommentList(comment);
    }

    @Test
    void createCommentTest(){
        when(repository.save(comment)).thenReturn(comment);
        Comment testComment = services.addComment(comment);
        assertEquals(comment, testComment);
        

    }

    @Test
    void createCommentTestInvalid() throws Exception{
        when(repository.save(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(ClassCastException.class,
                ()-> services.addComment(null));

    }

    @Test
    void getCommentByIdTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));
        Comment testComment = services.getCommentById(anyLong());
        assertEquals(comment, testComment);
        verify(repository).findById(anyLong());
    }

    @Test
    void getCommentByIdTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> services.getCommentById(anyLong()));
    }

    @Test
    void updateCommentByIdTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));
        Comment testComment = services.updateCommentById(anyLong(), comment);
        assertEquals(comment, testComment);

    }

    @Test
    void updateCommentByIdTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(javax.persistence.EntityNotFoundException.class,
                ()-> services.updateCommentById(anyLong(),comment));
    }

    @Test
    void deleteCommentByIdTest(){
        when(repository.existsById(anyLong())).thenReturn(true);
        services.deleteCommentById(anyLong());
        verify(repository).deleteById(anyLong());
        
    }

    @Test
    void deleteCommentByIdTestInvalid(){
        when(repository.existsById(null)).thenReturn(false);
        Assertions.assertThrows(IllegalStateException.class,
                ()-> services.deleteCommentById(anyLong()));
    }

    @Test
    void getAllCommentsByUserIdTest() throws Exception{
        when(repository.findByUserId(anyLong())).thenReturn(comments);
        assertEquals(comments, services.getAllCommentsByUserId(anyLong()));
    }

    @Test
    void getAllCommentsByRecipeIdTest() throws Exception{
        when(repository.findByRecipeId(anyLong())).thenReturn(comments);
        assertEquals(comments, services.getAllCommentsByRecipeId(anyLong()));

    }

    public static Comment createComment(){
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("Test");
        return comment;
    }
    
    public List<Comment> createCommentList(Comment comment){
        List<Comment> list = new ArrayList<>();
        list.add(comment);
        list.add(comment);
        return list;
    }
}
