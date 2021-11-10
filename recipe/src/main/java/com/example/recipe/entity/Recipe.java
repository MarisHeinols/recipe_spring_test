package com.example.recipe.entity;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
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
}
