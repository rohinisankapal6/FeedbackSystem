package com.yash.FMS.service;

import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.domain.Category;

import java.util.List;


public interface CategoryService {


    boolean createCategory(Category category);


    CategoryDTO getCategoryById(int categoryId);


    CategoryDTO getCategoryByName(String categoryName);


    List<CategoryDTO> getAllCategories();


    boolean updateCategory(Category category);


    boolean deleteCategory(int categoryId);
}
