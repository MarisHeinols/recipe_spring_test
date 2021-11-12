package com.example.recipe.controller;


import com.example.recipe.entity.Comment;
import com.example.recipe.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    CommentServices commentServices;

    @PostMapping
    public Comment createComment(@RequestBody Comment comment){
        return commentServices.createComment(comment);
    }

    @GetMapping(path = "{id}")
    public Comment getCommentById(@PathVariable("id") Long id){
        return commentServices.getCommentById(id);
    }
    @PutMapping(path= "{id}")
    public Comment updateCommentById(@PathVariable("id") Long id,String commentText){
        return commentServices.updateCommentById(id,commentText);
    }
    @DeleteMapping(path="{id}")
    public void deleteCommentById(@PathVariable("id") Long id){
        commentServices.deleteCommentById(id);
    }
    @GetMapping(path = "/byRecipe/{id}")
    public List<Comment> getAllCommentsByRecipeId(@PathVariable("id")Long id){
        return commentServices.getAllCommentsByRecipeId(id);
    }
    @GetMapping(path = "/byUser/{id}")
    public List<Comment> getAllCommentsByUserId(@PathVariable("id")Long id){
        return commentServices.getAllCommentsByUserId(id);
    }


}
