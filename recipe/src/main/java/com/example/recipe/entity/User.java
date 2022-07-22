package com.example.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name")
    private String userName;

    @OneToMany(targetEntity = Comment.class, mappedBy = "recipe",fetch = FetchType.LAZY)
    private List<Recipe> recipes;

    @OneToMany(targetEntity = Comment.class, mappedBy = "recipe",fetch = FetchType.LAZY)
    private List<Comment> comments;
}
