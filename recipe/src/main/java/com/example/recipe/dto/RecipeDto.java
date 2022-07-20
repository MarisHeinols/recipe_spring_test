package com.example.recipe.dto;

import java.util.List;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.User;

import lombok.Data;

@Data
public class RecipeDto {

    private Long id;
    private String name;
    private String recipeText;
    private User user;
    private List<Comment> comments;
}
