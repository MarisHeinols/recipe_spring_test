package com.example.recipe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String recipeText;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @JsonManagedReference
    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    public Recipe() {
    }

    public Recipe(String name, String recipeText) {
        this.name = name;
        this.recipeText = recipeText;
    }

    public Recipe(Long id, String name, String recipeText, User user, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.recipeText = recipeText;
        this.user = user;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
