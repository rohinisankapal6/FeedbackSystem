/**
 * This package contains the service layer implementations for the Feedback Management System (FMS).
 * Services orchestrate business logic, interact with DAOs (Data Access Objects),
 * and often handle data transformations (e.g., between domain objects and DTOs).
 */
package com.yash.FMS.service;

import com.yash.FMS.dao.UserDAO;
import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // More concise way to convert list

/**
 * Implementation of the UserService interface.
 * This class provides the business logic for user-related operations,
 * interacting with the UserDAO to persist and retrieve user data.
 * It handles operations like user creation, authentication, retrieval, update, and deletion.
 * It also converts between User domain objects and UserDTO data transfer objects.
 */
@Service // Marks this class as a Spring service component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO; // Data Access Object for user operations

    /**
     * Constructs the UserServiceImpl with the necessary UserDAO dependency.
     * Spring's @Autowired annotation handles the dependency injection.
     *
     * @param userDAO The DAO implementation for accessing user data.
     */
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Creates a new user in the system.
     * Delegates the creation operation to the UserDAO.
     * Note: Assumes password hashing is handled either here or in the DAO/Controller before calling this.
     *
     * @param user The User domain object containing details of the user to create.
     * @return true if the user was created successfully, false otherwise (e.g., username exists).
     */
    @Override
    public boolean createUser(User user) {
        // Consider adding password hashing logic here if not done elsewhere
        // e.g., user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.createUser(user);
    }

    /**
     * Authenticates a user based on username and password.
     * Retrieves the user by username from the DAO and compares the provided password
     * with the stored password.
     * Note: This implementation uses plain text password comparison.
     * **Security Warning:** Passwords should always be securely hashed and compared using a proper hashing mechanism (e.g., BCrypt).
     *
     * @param username The username provided for login.
     * @param password The password provided for login.
     * @return A UserDTO representing the authenticated user if credentials are valid, null otherwise.
     */
    @Override
    public UserDTO authenticateUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        // !! SECURITY RISK !! - Plain text password comparison. Use hashing.
        // Example with hashing: if (user != null && passwordEncoder.matches(password, user.getPassword())) { ... }
        if (user != null && user.getPassword().equals(password)) {
            return convertToDTO(user); // Convert domain object to DTO for transfer
        }
        return null; // Authentication failed
    }

    /**
     * Retrieves a user by their unique ID.
     * Delegates the retrieval operation to the UserDAO and converts the result to a DTO.
     *
     * @param userId The ID of the user to retrieve.
     * @return A UserDTO representing the found user, or null if no user exists with that ID.
     */
    @Override
    public UserDTO getUserById(int userId) {
        User user = userDAO.getUserById(userId);
        return user == null ? null : convertToDTO(user); // Convert if found, else return null
    }

    /**
     * Retrieves a user by their username.
     * Delegates the retrieval operation to the UserDAO and converts the result to a DTO.
     *
     * @param username The username of the user to retrieve.
     * @return A UserDTO representing the found user, or null if no user exists with that username.
     */
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        return user == null ? null : convertToDTO(user); // Convert if found, else return null
    }

    /**
     * Retrieves a list of all users registered in the system.
     * Fetches all User domain objects from the DAO and converts each one to a UserDTO.
     *
     * @return A List of UserDTO objects representing all users. Returns an empty list if no users exist.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        // Convert the list of User domain objects to a list of UserDTOs
        // Using stream API for a more concise conversion:
        return users.stream()
                .map(this::convertToDTO) // Apply conversion method to each user
                .collect(Collectors.toList()); // Collect results into a new list
        /* // Original loop-based conversion:
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(convertToDTO(user));
        }
        return userDTOs;
        */
    }

    /**
     * Updates the details of an existing user.
     * Delegates the update operation to the UserDAO.
     * Note: Ensure sensitive data like passwords are handled correctly (e.g., re-hashing if changed).
     *
     * @param user The User domain object containing the updated details (must include the user ID).
     * @return true if the user was updated successfully, false otherwise (e.g., user not found).
     */
    @Override
    public boolean updateUser(User user) {
        // Consider logic here if password can be updated (check if it's changed and hash if needed)
        return userDAO.updateUser(user);
    }

    /**
     * Deletes a user from the system based on their ID.
     * Delegates the deletion operation to the UserDAO.
     *
     * @param userId The ID of the user to delete.
     * @return true if the user was deleted successfully, false otherwise (e.g., user not found).
     */
    @Override
    public boolean deleteUser(int userId) {
        // Consider adding logic to handle related data (e.g., anonymize feedback) before deletion if necessary
        return userDAO.deleteUser(userId);
    }

    /**
     * Private helper method to convert a User domain object into a UserDTO.
     * This is used to transfer user data to the presentation layer without exposing domain objects directly
     * and potentially omitting sensitive fields like passwords.
     *
     * @param user The User domain object to convert.
     * @return The corresponding UserDTO object.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        // Note: Password is intentionally NOT included in the DTO
        return userDTO;
    }

    // Optional: Add a method to convert DTO back to User if needed for updates/creates
    // private User convertToEntity(UserDTO userDTO) { ... }
}