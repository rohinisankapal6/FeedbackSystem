/**
 * This package contains the service layer implementations for the Feedback Management System (FMS).
 * Services orchestrate business logic, interact with DAOs (Data Access Objects),
 * and often handle data transformations (e.g., between domain objects and DTOs).
 */
package com.yash.FMS.service;

import com.yash.FMS.dao.AdminDAO;
import com.yash.FMS.dto.AdminDTO;
import com.yash.FMS.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AdminService interface.
 * This class provides the business logic for administrator-related operations, primarily authentication.
 * It interacts with the AdminDAO to retrieve admin data and verifies credentials.
 * It converts the Admin domain object to an AdminDTO upon successful authentication.
 */
@Service // Marks this class as a Spring service component
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO; // Data Access Object for admin operations

    /**
     * Constructs the AdminServiceImpl with the necessary AdminDAO dependency.
     * Spring's @Autowired annotation handles the dependency injection.
     *
     * @param adminDAO The DAO implementation for accessing administrator data.
     */
    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    /**
     * Authenticates an administrator based on username and password.
     * Retrieves the admin by username from the DAO and compares the provided password
     * with the stored password.
     * Note: This implementation uses plain text password comparison.
     * **Security Warning:** Passwords should always be securely hashed and compared using a proper hashing mechanism (e.g., BCrypt).
     *
     * @param username The username provided for admin login.
     * @param password The password provided for admin login.
     * @return An AdminDTO representing the authenticated administrator (containing ID and username)
     *         if credentials are valid, null otherwise.
     */
    @Override
    public AdminDTO authenticateAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        // !! SECURITY RISK !! - Plain text password comparison. Use hashing.
        // Example with hashing: if (admin != null && passwordEncoder.matches(password, admin.getPassword())) { ... }
        if (admin != null && admin.getPassword().equals(password)) {
            // Convert domain object to DTO for transfer to controller/session
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setAdminId(admin.getAdminId());
            adminDTO.setUsername(admin.getUsername());
            // Note: Password is intentionally NOT included in the DTO
            return adminDTO;
        }
        return null; // Authentication failed
    }

    // Potential future methods: createAdmin, updateAdminPassword, getAdminById etc. would go here.
}