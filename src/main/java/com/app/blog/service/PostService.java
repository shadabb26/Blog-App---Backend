package com.app.blog.service;
import com.app.blog.entity.Post;
import com.app.blog.payload.PostDTO;
import com.app.blog.payload.PostResponse;

import java.util.List;
public interface PostService {
    PostDTO createPost(PostDTO postDTO,int userId,int categoryId);
    PostDTO updatePost(PostDTO postDTO,int postId);
    void deletePost(int id);
    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir);
    PostDTO getPostById(int id);
    List<PostDTO> getPostsByCategory(int categoryId);
    List<PostDTO> getPostsByUser(int id);
    List<PostDTO> searchPost(String keyword);
}
