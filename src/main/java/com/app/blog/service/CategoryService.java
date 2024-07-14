package com.app.blog.service;

import com.app.blog.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO creteCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,int id);
    void deleteCategory(int id);
    CategoryDTO getCategory(int id);
    List<CategoryDTO> getAllCategories();
}
