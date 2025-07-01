package com.yash.FMS.dao;

import com.yash.FMS.domain.Category;

import java.util.List;


public interface CategoryDAO {


    boolean createCategory(Category category);


    Category getCategoryById(int categoryId);


    Category getCategoryByName(String categoryName);


    List<Category> getAllCategories();


    boolean updateCategory(Category category);


    boolean deleteCategory(int categoryId);
}
