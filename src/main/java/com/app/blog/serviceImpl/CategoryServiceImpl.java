package com.app.blog.serviceImpl;

import com.app.blog.entity.Category;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.CategoryDTO;
import com.app.blog.repository.CategoryRepo;
import com.app.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO creteCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO,Category.class);
        this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDTO getCategory(int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream().map((category -> this.modelMapper.map(category,CategoryDTO.class))).toList();
    }
}
