package com.example.recipe.entity;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="recipe_id")
    private Recipe recipe;
    private String commentText;

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(Long id, User user, Recipe recipe, String commentText) {
        this.id = id;
        this.user = user;
        this.recipe = recipe;
        this.commentText = commentText;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getCommentText() {
        return commentText;
    }
}
