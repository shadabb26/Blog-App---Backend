﻿
# Blog Application Backend

This is the backend service for a blog application, developed using Spring Boot and MySQL. It provides a RESTful API for managing users, posts, categories, and comments.

## Prerequisites

- Java 11 or later
- Maven 3.6.3 or later
- MySQL database

## Getting Started

### Clone the repository

```bash
git clone <repository-url>
cd blog-application-backend
```

### Set up MySQL Database

1. Create a database named `blog`.
2. Update the `application.properties` file with your database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

Here is a list of available API endpoints:

### Users

- **GET /api/users**: Retrieve all users
- **POST /api/users**: Add a new user
- **GET /api/users/{id}**: Retrieve a user by ID
- **PUT /api/users/{id}**: Update a user
- **DELETE /api/users/{id}**: Delete a user

### Categories

- **POST /api/categories**: Add a new category
- **GET /api/categories**: Retrieve all categories
- **GET /api/categories/{id}**: Retrieve a category by ID
- **PUT /api/categories/{id}**: Update a category
- **DELETE /api/categories/{id}**: Delete a category

### Posts

- **POST /api/user/{userId}/category/{categoryId}/posts**: Add a new post
- **GET /api/posts**: Retrieve all posts with pagination and sorting
  - Query Parameters:
    - `pageNumber`: Page number (default: 0)
    - `pageSize`: Number of posts per page (default: 6)
    - `sortBy`: Field to sort by (default: id)
    - `sortDir`: Sort direction (asc or desc, default: asc)
- **GET /api/user/{id}/posts**: Retrieve all post by user 
- **GET /api/category/{id}/posts**: Retrieve all posts by category
- **GET /api/posts/{id}**: Retrieve post by id
- **PUT /api/posts/{id}**: Update a post
- **DELETE /api/posts/{id}**: Delete a post
- **GET /api/posts/category/{categoryId}**: Retrieve posts by category
- **GET /api/posts/search/{keywords}**: Search posts by keyword (Title)
- **POST /api/post/image/upload/{postId}**: Upload post image
- **GET /api/post/image/{imageName}**: get post image

### Comments

- **POST /api/post/{postId}/comments**: Add a new comment to a post
- **DELETE /api/comments/{id}**: Delete comment

## Example Usage

To retrieve all posts with pagination and sorting, you can use the following GET request in Postman:

```http
GET http://localhost:8080/api/posts?pageNumber=0&pageSize=6&sortBy=id&sortDir=asc
```

## Developed By

Blog App Backend is developed by [Shadabb](https://github.com/shadabb26)
