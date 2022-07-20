package com.example.recipe.dto;

import java.util.List;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private List<Recipe> recipes;
    private List<Comment> comments;
}
