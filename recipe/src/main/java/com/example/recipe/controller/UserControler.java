package com.example.recipe.controller;

import com.example.recipe.dto.UserDto;
import com.example.recipe.entity.User;
import com.example.recipe.service.interfaces.UserServices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserControler {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserServices userServices;

    public UserControler(UserServices userServices){
        super();
        this.userServices = userServices;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){

        User user = userServices.getUserById(id);
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping(path= "/all")
    public List<UserDto> getAllUsers(){

        return userServices.getAllUsers().stream().map(post -> modelMapper.map(post, UserDto.class))
				.collect(Collectors.toList());
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){

        userServices.deleteUser(id);

		return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto){

        User userRequest = modelMapper.map(userDto, User.class);
		User user = userServices.updateUser(id, userRequest);
		UserDto userResponse = modelMapper.map(user, UserDto.class);

		return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){

        User userRequest = modelMapper.map(userDto, User.class);
		User user = userServices.addUser(userRequest);
		UserDto postResponse = modelMapper.map(user, UserDto.class);

		return new ResponseEntity<UserDto>(postResponse, HttpStatus.CREATED);
    }


}
