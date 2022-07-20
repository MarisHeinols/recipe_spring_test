package com.example.recipe.dto;

import com.example.recipe.entity.Recipe;
import com.example.recipe.entity.User;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private User user;
    private Recipe recipe;
    private String commentText;
    
}
