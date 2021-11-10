package com.example.recipe.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name=name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
