package com.example.recipe.service.implementation;

import com.example.recipe.entity.Comment;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.service.interfaces.CommentServices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentServicesImpl implements CommentServices {

    public final CommentRepository commentRepository;

    public CommentServicesImpl(CommentRepository commentRepository) {

		super();
		this.commentRepository = commentRepository;
	}

    public List<Comment> getAllCommentsByRecipeId(Long id) {

        return commentRepository.findByRecipeId(id);
    }

    public List<Comment> getAllCommentsByUserId(Long id) {

        return commentRepository.findByUserId(id);
    }

    public Comment getCommentById(Long id) {

        return commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    public Comment addComment(Comment comment) {

        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public Comment updateCommentById(Long id, Comment commentData) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        comment.setCommentText(commentData.getCommentText());
        comment.setRecipe(commentData.getRecipe());
        comment.setUser(commentData.getUser());
        commentRepository.save(comment);

        return comment;
    }

    public void deleteCommentById(Long id) {

        boolean commentExists = commentRepository.existsById(id);
        if (!commentExists){
            throw new IllegalStateException(
                    "Comment with id " + id + " does not exist"
            );
        }
        commentRepository.deleteById(id);
    }

}
