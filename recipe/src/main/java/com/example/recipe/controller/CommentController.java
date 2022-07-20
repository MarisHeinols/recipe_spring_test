package com.example.recipe.controller;


import com.example.recipe.dto.CommentDto;
import com.example.recipe.entity.Comment;
import com.example.recipe.service.interfaces.CommentServices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    @Autowired
    private ModelMapper modelMapper;
    private CommentServices commentServices;

    public CommentController(CommentServices commentServices){
        super();
        this.commentServices = commentServices;
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment){
        commentServices.addComment(comment);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id){
        Comment comment = commentServices.getCommentById(id);
        CommentDto commentResponse = modelMapper.map(comment, CommentDto.class);
        return ResponseEntity.ok().body(commentResponse);
    }
    @PutMapping(path= "{id}")
    public ResponseEntity<CommentDto>  updateCommentById(@PathVariable("id") Long id,CommentDto commentDto ){
        Comment commentRequest = modelMapper.map(commentDto, Comment.class);

		Comment comment = commentServices.updateCommentById(id, commentRequest);

		CommentDto commentResponse = modelMapper.map(comment, CommentDto.class);

		return ResponseEntity.ok().body(commentResponse);
    }
    @DeleteMapping(path="{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id){
        commentServices.deleteCommentById(id);
		return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
    @GetMapping(path = "/byRecipe/{id}")
    public List<CommentDto> getAllCommentsByRecipeId(@PathVariable("id")Long id){
        return commentServices.getAllCommentsByRecipeId(id).stream().map(post -> modelMapper.map(post, CommentDto.class))
        .collect(Collectors.toList());
    }
    @GetMapping(path = "/byUser/{id}")
    public List<CommentDto> getAllCommentsByUserId(@PathVariable("id")Long id){
        return commentServices.getAllCommentsByUserId(id).stream().map(post -> modelMapper.map(post, CommentDto.class))
        .collect(Collectors.toList());
    }


}
