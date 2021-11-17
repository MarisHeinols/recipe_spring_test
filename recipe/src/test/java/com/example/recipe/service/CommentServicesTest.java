package com.example.recipe.service;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    private CommentServices services;

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

    }
    @Test
    void createCommentTestInvalid() throws Exception{
        when(repository.save(null)).thenReturn(null);

    }
    @Test
    void getCommentByIdTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

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
    }
    @Test
    void updateCommentByIdTestInvalid() throws Exception{
        when(repository.findById(null)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(InvalidUseOfMatchersException.class,
                ()-> services.updateCommentById(anyLong(),anyString()));
    }
    @Test
    void deleteCommentByIdTest(){
        when(repository.existsById(anyLong())).thenReturn(true);
    }
    @Test
    void deleteCommentByIdTestInvalid(){
        when(repository.existsById(null)).thenReturn(false);
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> services.deleteCommentById(anyLong()));
    }
    @Test
    void getAllCommentsByUserIdTest() throws Exception{
        when(repository.findByUserId(anyLong())).thenReturn(comments);
    }
    @Test
    void getAllCommentsByRecipeIdTest() throws Exception{
        when(repository.findByRecipeId(anyLong())).thenReturn(comments);
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
