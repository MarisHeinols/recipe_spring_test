package com.example.recipe.service;

import com.example.recipe.entity.Comment;
import com.example.recipe.entity.Recipe;
import com.example.recipe.entity.User;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipesRepository;
import com.example.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final RecipesRepository recipesRepository;
    private final CommentRepository commentRepository;


    @Autowired
    public UserServices(UserRepository userRepository, RecipesRepository recipesRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.recipesRepository = recipesRepository;
        this.commentRepository = commentRepository;
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    @Transactional
    public void updateUser(Long id,String name){
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));

        if(name != null && name.length() > 0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }

    }

    public void deleteUser(Long id){
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new IllegalStateException(
                    "User with id " + id + " does not exist"
            );
        }
        List<Comment> comments = commentRepository.findByUserId(id);
        comments.forEach(comment-> commentRepository.delete(comment));
        List<Recipe> recipes = recipesRepository.findByUserId(id);
        recipes.forEach(recipe -> recipesRepository.delete(recipe));

        userRepository.deleteById(id);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
