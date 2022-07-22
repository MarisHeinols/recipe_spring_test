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
    @Autowired
    private CommentServices commentServices;

    public CommentController(CommentServices commentServices){

        super();
        this.commentServices = commentServices;
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto){

        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
		Comment comment = commentServices.addComment(commentRequest);
		CommentDto postResponse = modelMapper.map(comment, CommentDto.class);

		return new ResponseEntity<CommentDto>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id){

        Comment comment = commentServices.getCommentById(id);
        CommentDto commentResponse = modelMapper.map(comment, CommentDto.class);

        return ResponseEntity.ok().body(commentResponse);
    }

    @PutMapping(path= "{id}")
    public ResponseEntity<CommentDto>  updateCommentById(@PathVariable("id") Long id, @RequestBody CommentDto commentDto ){
        System.out.println(commentServices);
        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
		Comment comment = commentServices.updateCommentById(id, commentRequest);
        System.out.println("comment = "+ comment);
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
