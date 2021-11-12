package com.example.recipe.service;

import com.example.recipe.entity.Comment;
import com.example.recipe.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServices {

    public final CommentRepository commentRepository;

    public CommentServices(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    public Comment updateCommentById(Long id, String commentText) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
        if(commentText != null && commentText.length() > 0 && !Objects.equals(comment.getCommentText(),commentText)){
            comment.setCommentText(commentText);
        }
        return  comment;
    }

    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
        commentRepository.delete(comment);
    }

    public List<Comment> getAllCommentsByRecipeId(Long id) {
        return commentRepository.findByRecipeId(id);
    }

    public List<Comment> getAllCommentsByUserId(Long id) {
        return commentRepository.findByUserId(id);
    }
}
