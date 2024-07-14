package com.app.blog.payload;

import com.app.blog.entity.Category;
import com.app.blog.entity.Comment;
import com.app.blog.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private Date addedDate;
    private UserDTO user;
    private CategoryDTO category;

    private Set<CommentDTO> comments = new HashSet<>();

    public PostDTO() {
    }

    public PostDTO(int id, String title, String content, String imageUrl, Date addedDate, UserDTO user, CategoryDTO category, Set<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.addedDate = addedDate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
