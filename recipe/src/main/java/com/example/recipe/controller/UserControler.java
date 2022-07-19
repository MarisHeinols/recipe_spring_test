package com.example.recipe.controller;

import com.example.recipe.entity.User;
import com.example.recipe.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {


    @Autowired
    UserServices userServices;

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable("id") Long id){
        return userServices.getUserById(id);
    }

    @GetMapping(path= "/all")
    public List<User> getAllUsers(){
        return userServices.getUsers();
    }
    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userServices.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") Long id,@RequestParam(required = false)String name){
        userServices.updateUser(id,name);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userServices.addUser(user);
    }


}
