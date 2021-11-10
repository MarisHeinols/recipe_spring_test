package com.example.recipe.repository;

import com.example.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe,Long> {

    List<Recipe> findByUserId(Long userId);

}