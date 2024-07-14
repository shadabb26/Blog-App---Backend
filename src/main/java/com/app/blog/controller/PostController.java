package com.app.blog.controller;
import com.app.blog.config.AppConstants;
import com.app.blog.payload.ApiResponse;
import com.app.blog.payload.PostDTO;
import com.app.blog.payload.PostResponse;
import com.app.blog.service.FileService;
import com.app.blog.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable int userId,@PathVariable int categoryId){
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
    }

    @GetMapping("user/{id}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable int id){
        List<PostDTO> posts = this.postService.getPostsByUser(id);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("category/{id}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable int id){
        List<PostDTO> posts = this.postService.getPostsByCategory(id);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue =AppConstants.SORT_DIR,required = false) String sortDir
    ){
        PostResponse postResponse= this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id){
        PostDTO post = this.postService.getPostById(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int id){
        this.postService.deletePost(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted",true),HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable int id){
        PostDTO post = this.postService.updatePost(postDTO,id);
        return new ResponseEntity<PostDTO>(post,HttpStatus.OK);
    }

    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords){
        List<PostDTO> posts = this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
    }

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable int postId) throws IOException {
        PostDTO postDTO = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDTO.setImageUrl(fileName);
        PostDTO updatePost = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);

    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
