package com.app.blog.serviceImpl;

import com.app.blog.entity.Category;
import com.app.blog.entity.Post;
import com.app.blog.entity.User;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.PostDTO;
import com.app.blog.payload.PostResponse;
import com.app.blog.repository.CategoryRepo;
import com.app.blog.repository.PostRepo;
import com.app.blog.repository.UserRepo;
import com.app.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDTO createPost(PostDTO postDTO,int userId, int categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        Post post = this.modelMapper.map(postDTO,Post.class);
        post.setImageUrl("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        this.postRepo.save(post);
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        this.postRepo.save(post);
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public void deletePost(int id) {
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts = this.postRepo.findAll(pageable);
        List<Post> posts = pagePosts.getContent();
        List<PostDTO> postDTOS =posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;

    }

    @Override
    public PostDTO getPostById(int id) {
        Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);

        return posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByUser(int id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        return posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }
}
