package com.app.blog.service;

import com.app.blog.payload.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO,int postId);
    void deleteComment(int commentId);
}
