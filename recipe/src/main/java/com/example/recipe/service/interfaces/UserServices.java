package com.example.recipe.service.interfaces;

import java.util.List;

import com.example.recipe.entity.User;

public interface UserServices {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    User updateUser(Long id,User userData);
    void deleteUser(Long id);
}
