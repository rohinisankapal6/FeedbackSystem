package com.yash.FMS.dao;

import com.yash.FMS.domain.User;
import com.yash.FMS.rm.UserRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link UserDAO} using Spring's {@link JdbcTemplate}.
 * Handles database operations for {@link User} entities.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the DAO with the required {@link JdbcTemplate}.
     *
     * @param jdbcTemplate The {@link JdbcTemplate} for database access.
     */
    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new user record in the database.
     *
     * @param user The {@link User} object containing the data for the new user.
     * @return {@code true} if the user was created successfully, {@code false} otherwise.
     */
    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName());
        return result > 0;
    }

    /**
     * Retrieves a {@link User} by their unique identifier.
     *
     * @param userId The ID of the user to retrieve.
     * @return The found {@link User} object, or {@code null} if not found.
     */
    @Override
    public User getUserById(int userId) {
        String sql = "SELECT userId, username, password, email, full_name FROM users WHERE userId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserRM());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a {@link User} by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The found {@link User} object, or {@code null} if not found.
     */
    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT userId, username, password, email, full_name FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRM());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A {@link List} of all {@link User} objects, or an empty list if none exist.
     */
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT userId, username, password, email, full_name FROM users";
        return jdbcTemplate.query(sql, new UserRM());
    }

    /**
     * Updates an existing user record in the database.
     *
     * @param user The {@link User} object containing the updated data (identified by userId).
     * @return {@code true} if the user was updated successfully, {@code false} otherwise.
     */
    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, full_name = ? WHERE userId = ?";
        int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), user.getUserId());
        return result > 0;
    }

    /**
     * Deletes a user record from the database using their ID.
     * Also deletes associated feedback records for the user.
     *
     * @param userId The ID of the user to delete.
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise.
     */
    @Override
    public boolean deleteUser(int userId) {
        // First, delete associated feedback to maintain referential integrity
        String deleteFeedbackSql = "DELETE FROM feedback WHERE userId = ?";
        jdbcTemplate.update(deleteFeedbackSql, userId);

        // Then, delete the user
        String sql = "DELETE FROM users WHERE userId = ?";
        int result = jdbcTemplate.update(sql, userId);
        return result > 0;
    }
}