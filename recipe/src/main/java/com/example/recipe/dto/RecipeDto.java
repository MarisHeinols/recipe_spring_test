package com.example.recipe.dto;

import java.util.List;

import com.example.recipe.entity.Comment;

import lombok.Data;

@Data
public class RecipeDto {

    private Long id;
    private String recipeName;
    private String recipeText;
    private Long userId;
    private List<Comment> comments;
}
