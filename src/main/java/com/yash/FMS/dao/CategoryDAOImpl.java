package com.yash.FMS.dao;

import com.yash.FMS.domain.Category;
import com.yash.FMS.rm.CategoryRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link CategoryDAO} using Spring's {@link JdbcTemplate}.
 * Handles database operations for {@link Category} entities.
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the DAO with the required {@link JdbcTemplate}.
     *
     * @param jdbcTemplate The {@link JdbcTemplate} for database access.
     */
    @Autowired
    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new category record in the database.
     *
     * @param category The {@link Category} object containing the data for the new category.
     * @return {@code true} if the category was created successfully, {@code false} otherwise.
     */
    @Override
    public boolean createCategory(Category category) {
        String sql = "INSERT INTO categories (categoryName) VALUES (?)";
        int result = jdbcTemplate.update(sql, category.getCategoryName());
        return result > 0;
    }

    /**
     * Retrieves a {@link Category} by its unique identifier.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The found {@link Category} object, or {@code null} if not found.
     */
    @Override
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT categoryId, categoryName FROM categories WHERE categoryId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{categoryId}, new CategoryRM());
        } catch (EmptyResultDataAccessException e) {
            return null; // Category not found
        }
    }

    /**
     * Retrieves a {@link Category} by its name.
     *
     * @param categoryName The name of the category to retrieve.
     * @return The found {@link Category} object, or {@code null} if not found.
     */
    @Override
    public Category getCategoryByName(String categoryName) {
        String sql = "SELECT categoryId, categoryName FROM categories WHERE categoryName = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{categoryName}, new CategoryRM());
        } catch (EmptyResultDataAccessException e) {
            return null; // Category not found
        }
    }

    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A {@link List} of all {@link Category} objects, or an empty list if none exist.
     */
    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT categoryId, categoryName FROM categories";
        return jdbcTemplate.query(sql, new CategoryRM());
    }

    /**
     * Updates an existing category record in the database.
     *
     * @param category The {@link Category} object containing the updated data (identified by categoryId).
     * @return {@code true} if the category was updated successfully, {@code false} otherwise.
     */
    @Override
    public boolean updateCategory(Category category) {
        String sql = "UPDATE categories SET categoryName = ? WHERE categoryId = ?";
        int result = jdbcTemplate.update(sql, category.getCategoryName(), category.getCategoryId());
        return result > 0;
    }

    /**
     * Deletes a category record from the database using its ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return {@code true} if the category was deleted successfully, {@code false} otherwise.
     */
    @Override
    public boolean deleteCategory(int categoryId) {
        // Corrected SQL: Added WHERE clause
        String sql = "DELETE FROM categories WHERE categoryId = ?";
        int result = jdbcTemplate.update(sql, categoryId);
        return result > 0;
    }
}