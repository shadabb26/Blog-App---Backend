package com.app.blog.controller;

import com.app.blog.payload.ApiResponse;
import com.app.blog.payload.CommentDTO;
import com.app.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable int postId){
        CommentDTO comment = this.commentService.createComment(commentDTO,postId);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int id){
        this.commentService.deleteComment(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted!",true),HttpStatus.OK);

    }





}
