package com.example.recipe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name ="recipe_id")
    private Recipe recipe;
    @Column(name = "commentText")
    private String commentText;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
