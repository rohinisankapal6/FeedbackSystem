/**
 * This package contains the service layer implementations for the Feedback Management System (FMS).
 * Services orchestrate business logic, interact with DAOs (Data Access Objects),
 * and often handle data transformations (e.g., between domain objects and DTOs).
 */
package com.yash.FMS.service;

import com.yash.FMS.dao.CategoryDAO;
import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // For more concise list conversion

/**
 * Implementation of the CategoryService interface.
 * This class provides the business logic for managing feedback categories,
 * interacting with the CategoryDAO to persist and retrieve category data.
 * It handles operations like category creation, retrieval (by ID, by name, all), update, and deletion.
 * It also converts between Category domain objects and CategoryDTO data transfer objects.
 */
@Service // Marks this class as a Spring service component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO; // Data Access Object for category operations

    /**
     * Constructs the CategoryServiceImpl with the necessary CategoryDAO dependency.
     * Spring's @Autowired annotation handles the dependency injection.
     *
     * @param categoryDAO The DAO implementation for accessing category data.
     */
    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Creates a new feedback category in the system.
     * Delegates the creation operation to the CategoryDAO.
     *
     * @param category The Category domain object containing the details of the category to create.
     * @return true if the category was created successfully, false otherwise (e.g., category name exists).
     */
    @Override
    public boolean createCategory(Category category) {
        // Consider adding validation here (e.g., check if name already exists) before calling DAO
        return categoryDAO.createCategory(category);
    }

    /**
     * Retrieves a specific feedback category by its unique ID.
     * Fetches the Category domain object from the DAO and converts it to a CategoryDTO.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return A CategoryDTO representing the found category, or null if no category exists with that ID.
     */
    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        Category category = categoryDAO.getCategoryById(categoryId);
        return category == null ? null : convertToDTO(category); // Convert if found, else null
    }

    /**
     * Retrieves a specific feedback category by its name.
     * Fetches the Category domain object from the DAO and converts it to a CategoryDTO.
     * Category names are expected to be unique.
     *
     * @param categoryName The name of the category to retrieve.
     * @return A CategoryDTO representing the found category, or null if no category exists with that name.
     */
    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryDAO.getCategoryByName(categoryName);
        return category == null ? null : convertToDTO(category); // Convert if found, else null
    }

    /**
     * Retrieves a list of all feedback categories available in the system.
     * Fetches all Category domain objects from the DAO and converts each one to a CategoryDTO.
     *
     * @return A List of CategoryDTO objects representing all categories. Returns an empty list if no categories exist.
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        // Convert the list of Category domain objects to a list of CategoryDTOs using streams
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        /* // Original loop-based conversion:
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(convertToDTO(category));
        }
        return categoryDTOs;
        */
    }

    /**
     * Updates the details (usually the name) of an existing feedback category.
     * Delegates the update operation to the CategoryDAO.
     *
     * @param category The Category domain object containing the updated details (must include the category ID).
     * @return true if the category was updated successfully, false otherwise (e.g., category not found or name conflict).
     */
    @Override
    public boolean updateCategory(Category category) {
        // Consider adding validation here (e.g., check for name uniqueness if changed) before calling DAO
        return categoryDAO.updateCategory(category);
    }

    /**
     * Deletes a feedback category from the system based on its ID.
     * Delegates the deletion operation to the CategoryDAO.
     * Note: Consider implications of deleting a category that is currently assigned to feedback entries.
     * The DAO or service might need logic to handle this (e.g., prevent deletion, reassign feedback, set category to null).
     *
     * @param categoryId The ID of the category to delete.
     * @return true if the category was deleted successfully, false otherwise (e.g., category not found or deletion prevented).
     */
    @Override
    public boolean deleteCategory(int categoryId) {
        // Consider adding logic here or in DAO to handle feedback associated with this category
        return categoryDAO.deleteCategory(categoryId);
    }

    /**
     * Private helper method to convert a Category domain object into a CategoryDTO.
     * This is used to transfer category data (ID and name) to the presentation layer
     * or other services without exposing domain objects directly.
     *
     * @param category The Category domain object to convert.
     * @return The corresponding CategoryDTO object.
     */
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }

    // Optional: Add a method to convert DTO back to Category if needed for updates/creates
    // private Category convertToEntity(CategoryDTO categoryDTO) { ... }
}