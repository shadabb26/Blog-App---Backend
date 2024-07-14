package com.app.blog.payload;

import com.app.blog.entity.Post;

public class CommentDTO {
    private int id;
    private String content;
    public CommentDTO() {
    }

    public CommentDTO(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
