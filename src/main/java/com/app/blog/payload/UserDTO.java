package com.app.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Name should be greater than 4 characters!")
    private String name;
    @Email(message = "Enter Valid email address!")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be greater than 3 and less that 10 Characters!")
    private String password;
    @NotEmpty
    private String about;

    public UserDTO() {
    }

    public UserDTO(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

