package com.example.recipe.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private Long userId;
    private Long recipeId;
    private String commentText;
    
}
