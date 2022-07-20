package com.example.recipe.service.interfaces;

import java.util.List;

import com.example.recipe.entity.Comment;

public interface CommentServices {
    List<Comment> getAllCommentsByRecipeId(Long id);
    List<Comment> getAllCommentsByUserId(Long id);
    Comment getCommentById(Long id);
    Comment addComment(Comment comment);
    Comment updateCommentById(Long id, Comment commentData);
    void deleteCommentById(Long id);

}
