package com.example.recipe.controller;

import com.example.recipe.entity.Comment;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.CommentServices;
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

@WebMvcTest(CommentController.class)
@ExtendWith(SpringExtension.class)
public class CommentControllerTest {

    public static String URL = "/comment";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CommentController controller;
    @MockBean
    private CommentServices services;
    @MockBean
    private RecipesRepository recipesRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void getCommentByIdTest() throws Exception{
        Comment comment = createComment();

        when(services.getCommentById(anyLong())).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/1")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getCommentByIdTestInvalid() throws Exception{
        Comment comment = createComment();

        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/null")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void getAllCommentsByUserIdTest() throws Exception{
        Comment comment = createComment();
        List<Comment> commentList = createCommentList(comment);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/byUser/1")
                        .content(asJsonString(commentList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteCommentTest() throws Exception{
        Comment comment = createComment();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/1")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void deleteRecipeTestInvalid() throws Exception{
        Comment comment = createComment();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/null")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void updateUserTest() throws Exception{
        Comment comment = createComment();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1?commentText=test")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }
    @Test
    public void updateUserTestInvalid() throws Exception{
        Comment comment = createComment();

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/null?commentText=test")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void createRecipeTest() throws Exception{
        Comment comment = createComment();

        when(services.createComment(comment)).thenReturn(comment);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(asJsonString(comment))
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
